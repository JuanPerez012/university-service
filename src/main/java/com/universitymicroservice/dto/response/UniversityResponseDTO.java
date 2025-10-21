package com.universitymicroservice.dto.response;

import com.universitymicroservice.enums.RoundingModeType;

import java.math.BigDecimal;

public record UniversityResponseDTO(
        Long id,
        String code,
        String name,
        String logoUrl,
        BigDecimal minScore,
        BigDecimal maxScore,
        BigDecimal passingScore,
        RoundingModeType roundingMode,
        Integer cutsCount,
        BigDecimal cutWeightPercent
) {}
