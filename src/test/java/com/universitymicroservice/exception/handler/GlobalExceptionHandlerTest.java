package com.universitymicroservice.exception.handler;

import com.universitymicroservice.exception.UniversityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleUniversityNotFound_returns404Body() {
        ResponseEntity<Map<String,Object>> resp =
                handler.handleUniversityNotFound(new UniversityNotFoundException());
        assertEquals(404, resp.getStatusCode().value());
        assertTrue(resp.getBody().get("message").toString().contains("Universidad no encontrada"));
    }

    @Test
    void handleGeneric_returns500Body() {
        ResponseEntity<Map<String,Object>> resp =
                handler.handleGeneric(new RuntimeException("boom"));
        assertEquals(500, resp.getStatusCode().value());
        assertTrue(resp.getBody().get("message").toString().contains("boom"));
    }
}
