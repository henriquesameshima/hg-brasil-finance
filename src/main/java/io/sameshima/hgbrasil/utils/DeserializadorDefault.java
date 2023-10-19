package io.sameshima.hgbrasil.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.sameshima.hgbrasil.service.dto.ErrorResponse;

public class DeserializadorDefault<T> extends JsonDeserializer<Object> {

	private final Class<T> defaultClass;

	public DeserializadorDefault(Class<T> defaultClass) {
		this.defaultClass = defaultClass;
	}

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		if (node.has("error") && node.get("error").asBoolean()) {
			return oc.treeToValue(node, ErrorResponse.class);
		} else if (node.isArray()) {
			JavaType type = ctxt.getTypeFactory().constructCollectionType(List.class, defaultClass);
			JsonParser newJp = node.traverse(oc);
			return oc.readValue(newJp, type);
		} else {
			return oc.treeToValue(node, defaultClass);
		}
	}
}