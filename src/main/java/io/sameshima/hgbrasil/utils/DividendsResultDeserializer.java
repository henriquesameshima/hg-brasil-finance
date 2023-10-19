package io.sameshima.hgbrasil.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsInfo;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsResult;

public class DividendsResultDeserializer extends JsonDeserializer<DividendsResult> {

	private static final ObjectMapper localMapper = new ObjectMapper();

	@Override
	public DividendsResult deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		final JsonNode node = localMapper.readTree(jp);

		JavaType type = localMapper.getTypeFactory().constructCollectionType(List.class, DividendsInfo.class);
		List<DividendsInfo> dividendsLists = localMapper.convertValue(node, type);
		DividendsResult dividendsResult = new DividendsResult();
		dividendsResult.setDividendsList(dividendsLists);
		return dividendsResult;
	}
}
