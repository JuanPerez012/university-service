package com.universitymicroservice.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiPathsTest {
    @Test
    void universitiesPath_ok() {
        assertEquals("/api/v1/universities", ApiPaths.UNIVERSITIES);
    }
}