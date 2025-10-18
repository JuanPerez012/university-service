package com.universitymicroservice.dto.response;

import java.math.BigDecimal;

public record UniversityResponseDTO(
        Long id,
        String code,
        String name,
        String status,
        String logoUrl,
        BigDecimal minScore,
        BigDecimal maxScore,
        BigDecimal passingScore,
        String roundingMode,
        Integer cutsCount,
        BigDecimal cutWeightPercent
) {}
