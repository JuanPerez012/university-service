package com.universitymicroservice.service.impl;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.UniversityResponseDTO;

import java.util.List;

public interface IUniversityService {
    UniversityResponseDTO create(UniversityRequestDTO universityRequestDTO);
    UniversityResponseDTO getById(Long id);
    List<UniversityResponseDTO> getAll();
    UniversityResponseDTO update(Long id, UniversityRequestDTO universityRequestDTO);
    void delete(Long id);
}
