package com.universitymicroservice.dto.request;

import com.universitymicroservice.enums.RoundingModeType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UniversityRequestDTO(

        @NotBlank @Size(max = 30)
        String code,

        @NotBlank @Size(max = 150)
        String name,

        @Size(max = 255)
        String logoUrl,

        @NotNull
        @DecimalMin("0.00")
        @DecimalMax("100.00")
        BigDecimal minScore,

        @NotNull
        @DecimalMin("0.00")
        @DecimalMax("100.00")
        BigDecimal maxScore,

        @NotNull
        @DecimalMin("0.00")
        @DecimalMax("100.00")
        BigDecimal passingScore,

        @NotBlank @Size(max = 15)
        RoundingModeType roundingMode,

        @NotNull @Min(1)
        Integer cutsCount,

        @NotNull
        @DecimalMin("0.00") @DecimalMax("100.00")
        BigDecimal cutWeightPercent
) {}
