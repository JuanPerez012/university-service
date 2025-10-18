package com.universitymicroservice.controller;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.PaginatedResponse;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.service.impl.IUniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.universitymicroservice.utils.ApiPaths.UNIVERSITIES;

@RestController
@RequestMapping(UNIVERSITIES)
public class UniversityController {

    private final IUniversityService service;

    public UniversityController(IUniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UniversityResponseDTO> create(@RequestBody UniversityRequestDTO dto) {
        UniversityResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> getById(@PathVariable Long id) {
        UniversityResponseDTO university = service.getById(id);
        return ResponseEntity.ok(university);
    }

    @GetMapping
    public ResponseEntity<List<UniversityResponseDTO>> getAll() {
        List<UniversityResponseDTO> universitys = service.getAll();
        return ResponseEntity.ok(universitys);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> update(@PathVariable Long id, @RequestBody UniversityRequestDTO dto) {
        UniversityResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<UniversityResponseDTO>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PaginatedResponse<UniversityResponseDTO> response = service.getAllPaginated(page, size);
        return ResponseEntity.ok(response);
    }
}