package com.universitymicroservice.controller;

import com.universitymicroservice.dto.request.UniversityRequestDTO;
import com.universitymicroservice.dto.response.PaginatedResponse;
import com.universitymicroservice.dto.response.UniversityResponseDTO;
import com.universitymicroservice.enums.RoundingModeType;
import com.universitymicroservice.service.impl.IUniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UniversityController.class)
class UniversityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private IUniversityService service;

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    private static UniversityResponseDTO sample(Long id, String code, String name) {
        return new UniversityResponseDTO(
                id,
                code,
                name,
                "http://logo",
                bd("0.00"),
                bd("5.00"),
                bd("3.00"),
                RoundingModeType.HALF_UP,
                3,
                bd("33.33")
        );
    }

    @Test
    void getById_ok() throws Exception {
        when(service.getById(1L)).thenReturn(sample(1L, "UNI", "My Uni"));

        mvc.perform(get("/api/v1/universities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("UNI"))
                .andExpect(jsonPath("$.name").value("My Uni"));
    }

    @Test
    void getAll_ok() throws Exception {
        when(service.getAll()).thenReturn(List.of(sample(2L, "U2", "Second")));

        mvc.perform(get("/api/v1/universities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].code").value("U2"));
    }

    @Test
    void create_ok() throws Exception {
        when(service.create(any(UniversityRequestDTO.class)))
                .thenReturn(sample(3L, "NEW", "Created"));

        String body = """
                {
                  "code":"NEW",
                  "name":"Created",
                  "logoUrl":"http://logo",
                  "minScore": 0.00,
                  "maxScore": 5.00,
                  "passingScore": 3.00,
                  "roundingMode": "HALF_UP",
                  "cutsCount": 3,
                  "cutWeightPercent": 33.33
                }
                """;

        mvc.perform(post("/api/v1/universities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.code").value("NEW"));
    }

    @Test
    void update_ok() throws Exception {
        when(service.update(eq(4L), any(UniversityRequestDTO.class)))
                .thenReturn(sample(4L, "UPD", "Updated"));

        String body = """
                {
                  "code":"UPD",
                  "name":"Updated",
                  "logoUrl":"http://logo",
                  "minScore": 0.00,
                  "maxScore": 5.00,
                  "passingScore": 3.50,
                  "roundingMode": "HALF_UP",
                  "cutsCount": 4,
                  "cutWeightPercent": 25.00
                }
                """;

        mvc.perform(put("/api/v1/universities/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void delete_noContent() throws Exception {
        mvc.perform(delete("/api/v1/universities/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllPaginated_ok() throws Exception {
        PaginatedResponse<UniversityResponseDTO> page = new PaginatedResponse<>(
                List.of(sample(6L, "PAG", "Paged")), 0, 1, 1, 10, false, false
        );
        when(service.getAllPaginated(0,10)).thenReturn(page);

        mvc.perform(get("/api/v1/universities/paginated?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].code").value("PAG"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
