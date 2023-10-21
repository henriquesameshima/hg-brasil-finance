package io.sameshima.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.sameshima.hgbrasil.service.dto.ErrorResponse;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockInfo;
import io.sameshima.hgbrasil.utils.DeserializadorDefault;

class DeserializadorDefaultTest {

	private DeserializadorDefault<StockInfo> deserializer;

	@Mock
	private DeserializationContext mockContext;

	@Mock
	private TypeFactory mockTypeFactory;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		deserializer = new DeserializadorDefault<>(StockInfo.class);
	}

	@Test
	void testDeserializeErrorResponse() throws Exception {
		String json = "{\"error\": true, \"message\": \"Some error\"}";

		Object result = deserialize(json);

		assertTrue(result instanceof ErrorResponse);
		assertEquals("Some error", ((ErrorResponse) result).getMessage());
	}

	@Test
	@SuppressWarnings("unchecked")
	void testDeserializeList() throws Exception {
		JavaType javaType = TypeFactory.defaultInstance().constructCollectionType(List.class, StockInfo.class);

		CollectionType collectionType = CollectionType.construct(List.class, null, null, null, javaType);

		when(mockContext.getTypeFactory()).thenReturn(mockTypeFactory);
		when(mockTypeFactory.constructCollectionType(List.class, StockInfo.class)).thenReturn(collectionType);

		String json = "[{\"kind\": \"value1\"}, {\"kind\": \"value2\"}]";

		ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) deserialize(json);

		assertTrue(result instanceof List);
		assertEquals(2, result.size());
		assertEquals("value1", result.get(0).get("kind"));
	}

	@Test
	void testDeserializeObject() throws Exception {
		String json = "{\"kind\": \"value\"}";

		Object result = deserialize(json);

		assertTrue(result instanceof StockInfo);
		assertEquals("value", ((StockInfo) result).getKind());
	}

	private Object deserialize(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser jp = new JsonFactory().createParser(json);
		jp.setCodec(mapper);
		return deserializer.deserialize(jp, mockContext);
	}

}