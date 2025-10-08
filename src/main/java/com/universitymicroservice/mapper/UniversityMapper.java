package com.universitymicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.universitymicroservice.entity.University;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.dto.request.UniversityRequestDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UniversityMapper {
    University toEntity(UniversityRequestDTO dto);

    UniversityResponseDTO toResponseDTO(University university);
}