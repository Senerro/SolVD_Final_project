package com.solvd.service.json.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.common.exceptions.JsonParserException;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class JsonParserService {
    private static final String RESULT_FILE_PATH = "src/main/resources/application_result.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> void writeJsonFileToResources(T object) {
        try {
            OBJECT_MAPPER.writeValue(new File(RESULT_FILE_PATH), object);
        } catch (IOException e) {
            throw new JsonParserException("Error writing a file to path: " + RESULT_FILE_PATH + ", " + e);
        }
    }
}
