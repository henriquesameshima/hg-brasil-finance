package io.sameshima.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.sameshima.hgbrasil.utils.LocalDateTimeDeserializer;

class LocalDateTimeDeserializerTest {

    @InjectMocks
    private LocalDateTimeDeserializer deserializer;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeserialize() throws IOException {
        String dateAsString = "20231013123000";
        LocalDateTime expectedDate = LocalDateTime.parse(dateAsString, formatter);

        when(jsonParser.getText()).thenReturn(dateAsString);

        LocalDateTime result = deserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(expectedDate, result);
    }
}