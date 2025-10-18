package com.universitymicroservice.service;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.PaginatedResponse;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.entity.University;
import com.universitymicroservice.repository.UniversityRepository;
import com.universitymicroservice.service.impl.IUniversityService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.universitymicroservice.exception.UniversityNotFoundException;
import com.universitymicroservice.mapper.UniversityMapper;

import java.util.List;

@Service
@Validated
public class UniversityService implements IUniversityService {

    private final UniversityRepository repository;
    private final UniversityMapper universityMapper;

    public UniversityService(UniversityRepository repository, UniversityMapper universityMapper) {
        this.repository = repository;
        this.universityMapper = universityMapper;
    }

    @Override
    public UniversityResponseDTO create(UniversityRequestDTO universityRequestDTO) {
        University university = universityMapper.toEntity(universityRequestDTO);
        University saved = repository.save(university);
        return universityMapper.toResponseDTO(saved);
    }

    @Override
    public UniversityResponseDTO getById(Long id) {
        University university = repository.findById(id)
                .orElseThrow(UniversityNotFoundException::new);
        return universityMapper.toResponseDTO(university);
    }

    @Override
    public List<UniversityResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(universityMapper::toResponseDTO)
                .toList();
    }

    @Override
    public UniversityResponseDTO update(Long id, UniversityRequestDTO dto) {
        University existing = repository.findById(id)
                .orElseThrow(UniversityNotFoundException::new);

        University updated = universityMapper.toEntity(dto);
        updated.setId(existing.getId());

        University saved = repository.save(updated);
        return universityMapper.toResponseDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UniversityNotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<UniversityResponseDTO> getAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<University> universityPage = repository.findAll(pageable);

        List<UniversityResponseDTO> universities = universityPage
                .getContent()
                .stream()
                .map(universityMapper::toResponseDTO)
                .toList();

        return new PaginatedResponse<>(
                universities,
                universityPage.getNumber(),
                universityPage.getTotalPages(),
                universityPage.getTotalElements(),
                universityPage.getSize(),
                universityPage.hasNext(),
                universityPage.hasPrevious()
        );
    }
}
