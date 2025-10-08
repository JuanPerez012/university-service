package com.universitymicroservice.mapper;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.entity.University;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-08T18:37:35-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UniversityMapperImpl implements UniversityMapper {

    @Override
    public University toEntity(UniversityRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        University university = new University();

        university.setCode( dto.code() );
        university.setName( dto.name() );
        university.setStatus( dto.status() );
        university.setLogoUrl( dto.logoUrl() );
        university.setMinScore( dto.minScore() );
        university.setMaxScore( dto.maxScore() );
        university.setPassingScore( dto.passingScore() );
        university.setRoundingMode( dto.roundingMode() );
        university.setCutsCount( dto.cutsCount() );
        university.setCutWeightPercent( dto.cutWeightPercent() );

        return university;
    }

    @Override
    public UniversityResponseDTO toResponseDTO(University university) {
        if ( university == null ) {
            return null;
        }

        Long id = null;
        String code = null;
        String name = null;
        String status = null;
        String logoUrl = null;
        BigDecimal minScore = null;
        BigDecimal maxScore = null;
        BigDecimal passingScore = null;
        String roundingMode = null;
        Integer cutsCount = null;
        BigDecimal cutWeightPercent = null;

        id = university.getId();
        code = university.getCode();
        name = university.getName();
        status = university.getStatus();
        logoUrl = university.getLogoUrl();
        minScore = university.getMinScore();
        maxScore = university.getMaxScore();
        passingScore = university.getPassingScore();
        roundingMode = university.getRoundingMode();
        cutsCount = university.getCutsCount();
        cutWeightPercent = university.getCutWeightPercent();

        UniversityResponseDTO universityResponseDTO = new UniversityResponseDTO( id, code, name, status, logoUrl, minScore, maxScore, passingScore, roundingMode, cutsCount, cutWeightPercent );

        return universityResponseDTO;
    }
}
