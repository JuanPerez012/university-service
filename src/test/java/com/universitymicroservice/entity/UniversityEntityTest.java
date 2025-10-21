package com.universitymicroservice.entity;

import com.universitymicroservice.enums.RoundingModeType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UniversityEntityTest {

    @Test
    void gettersAndSetters_coverAllFields() {
        University u = new University();

        u.setId(10L);
        u.setCode("CODE");
        u.setName("NAME");
        u.setLogoUrl("http://logo");
        u.setMinScore(new BigDecimal("0.00"));
        u.setMaxScore(new BigDecimal("5.00"));
        u.setPassingScore(new BigDecimal("3.00"));
        u.setRoundingMode(RoundingModeType.HALF_UP);
        u.setCutsCount(3);
        u.setCutWeightPercent(new BigDecimal("33.33"));

        assertEquals(10L, u.getId());
        assertEquals("CODE", u.getCode());
        assertEquals("NAME", u.getName());
        assertEquals("http://logo", u.getLogoUrl());
        assertEquals(new BigDecimal("0.00"), u.getMinScore());
        assertEquals(new BigDecimal("5.00"), u.getMaxScore());
        assertEquals(new BigDecimal("3.00"), u.getPassingScore());
        assertEquals(RoundingModeType.HALF_UP, u.getRoundingMode());
        assertEquals(3, u.getCutsCount());
        assertEquals(new BigDecimal("33.33"), u.getCutWeightPercent());
    }
}
