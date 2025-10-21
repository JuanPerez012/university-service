package com.universitymicroservice.service;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.PaginatedResponse;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.entity.University;
import com.universitymicroservice.enums.RoundingModeType;
import com.universitymicroservice.exception.UniversityNotFoundException;
import com.universitymicroservice.mapper.UniversityMapper;
import com.universitymicroservice.repository.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversityServiceTest {

    @Mock private UniversityRepository repository;
    @Mock private UniversityMapper mapper;

    @InjectMocks private com.universitymicroservice.service.UniversityService service;

    private UniversityRequestDTO req;
    private University entity;
    private UniversityResponseDTO resp;

    private static BigDecimal bd(String v) { return new BigDecimal(v); }

    @BeforeEach
    void setUp() {
        req = new UniversityRequestDTO(
                "CODE", "NAME", "http://logo",
                bd("0.00"), bd("5.00"), bd("3.00"),
                RoundingModeType.HALF_UP, 3, bd("33.33")
        );

        entity = new University();
        entity.setId(1L);
        entity.setCode("CODE");
        entity.setName("NAME");
        entity.setLogoUrl("http://logo");
        entity.setMinScore(bd("0.00"));
        entity.setMaxScore(bd("5.00"));
        entity.setPassingScore(bd("3.00"));
        entity.setRoundingMode(RoundingModeType.HALF_UP);
        entity.setCutsCount(3);
        entity.setCutWeightPercent(bd("33.33"));

        resp = new UniversityResponseDTO(
                1L, "CODE", "NAME", "http://logo",
                bd("0.00"), bd("5.00"), bd("3.00"),
                RoundingModeType.HALF_UP, 3, bd("33.33")
        );
    }

    @Test
    void create_ok() {
        when(mapper.toEntity(req)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        UniversityResponseDTO out = service.create(req);

        assertNotNull(out);
        assertEquals("CODE", out.code());
        verify(mapper).toEntity(req);
        verify(repository).save(entity);
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getById_ok() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        UniversityResponseDTO out = service.getById(1L);

        assertEquals(1L, out.id());
        verify(repository).findById(1L);
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getById_notFound_throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UniversityNotFoundException.class, () -> service.getById(999L));
        verify(repository).findById(999L);
        verifyNoInteractions(mapper);
    }

    @Test
    void getAll_ok() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        List<UniversityResponseDTO> out = service.getAll();

        assertEquals(1, out.size());
        assertEquals("NAME", out.get(0).name());
        verify(repository).findAll();
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getAll_empty_returnsEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        List<UniversityResponseDTO> out = service.getAll();

        assertNotNull(out);
        assertTrue(out.isEmpty());
        verify(repository).findAll();
        verifyNoInteractions(mapper);
    }

    @Test
    void update_ok() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        University updated = new University();
        updated.setCode("CODE");
        updated.setName("NAME");

        when(mapper.toEntity(req)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);
        when(mapper.toResponseDTO(updated)).thenReturn(resp);

        UniversityResponseDTO out = service.update(1L, req);
        assertEquals("CODE", out.code());

        verify(repository).findById(1L);
        verify(mapper).toEntity(req);
        verify(repository).save(updated);
        verify(mapper).toResponseDTO(updated);

        ArgumentCaptor<University> cap = ArgumentCaptor.forClass(University.class);
        verify(repository).save(cap.capture());
        assertEquals(1L, cap.getValue().getId());
    }

    @Test
    void update_notFound_throws() {
        when(repository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(UniversityNotFoundException.class, () -> service.update(123L, req));
        verify(repository).findById(123L);
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void delete_ok() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete_notFound_throws() {
        when(repository.existsById(999L)).thenReturn(false);

        assertThrows(UniversityNotFoundException.class, () -> service.delete(999L));
        verify(repository).existsById(999L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void getAllPaginated_firstPage_noPrevNoNext() {
        Pageable expected = PageRequest.of(0, 10, Sort.by("id").ascending());
        Page<University> page = new PageImpl<>(List.of(entity), expected, 1L);

        when(repository.findAll(any(Pageable.class))).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        PaginatedResponse<UniversityResponseDTO> out = service.getAllPaginated(0, 10);

        assertEquals(1, out.getTotalElements());
        assertEquals(1, out.getTotalPages());
        assertFalse(out.isHasNext());
        assertFalse(out.isHasPrevious());

        ArgumentCaptor<Pageable> cap = ArgumentCaptor.forClass(Pageable.class);
        verify(repository).findAll(cap.capture());
        assertEquals(0, cap.getValue().getPageNumber());
        assertEquals(10, cap.getValue().getPageSize());
    }

    @Test
    void getAllPaginated_middlePage_hasPrevAndNext() {
        Pageable used = PageRequest.of(1, 2, Sort.by("id").ascending());
        Page<University> page = new PageImpl<>(List.of(entity, entity), used, 5L);

        when(repository.findAll(any(Pageable.class))).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        PaginatedResponse<UniversityResponseDTO> out = service.getAllPaginated(1, 2);

        assertEquals(5, out.getTotalElements());
        assertEquals(3, out.getTotalPages());
        assertTrue(out.isHasPrevious());
        assertTrue(out.isHasNext());
    }
}
