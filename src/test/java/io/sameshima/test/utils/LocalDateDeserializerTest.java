package io.sameshima.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.sameshima.hgbrasil.utils.LocalDateDeserializer;

class LocalDateDeserializerTest {

    @InjectMocks
    private LocalDateDeserializer deserializer;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeserialize() throws IOException {
        String dateAsString = "2023-01-01";
        LocalDate expectedDate = LocalDate.parse(dateAsString, formatter);

        when(jsonParser.getText()).thenReturn(dateAsString);

        LocalDate result = deserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(expectedDate, result);
    }
}