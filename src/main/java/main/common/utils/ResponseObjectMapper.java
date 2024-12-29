package main.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.common.ui.Response;

public class ResponseObjectMapper {

    private ResponseObjectMapper() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Response toResponseObject(String response) {
        try {
            return objectMapper.readValue(response, Response.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toStringResponse(Response<?> response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return null;
        }
    }
}
