package com.universitymicroservice;

import com.universitymicroservice.controller.UniversityController;
import com.universitymicroservice.exception.handler.GlobalExceptionHandler;
import com.universitymicroservice.mapper.UniversityMapper;
import com.universitymicroservice.repository.UniversityRepository;
import com.universitymicroservice.service.UniversityService;
import com.universitymicroservice.service.impl.IUniversityService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
                "org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class UniversitymicroserviceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @MockitoBean
    private UniversityRepository universityRepository;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }

    @DisplayName("Todos los beans clave deben estar presentes en el contexto")
    @ParameterizedTest(name = "Bean presente por tipo: {0}")
    @MethodSource("beanTypes")
    void beansShouldBePresentByType(Class<?> type) {
        Object bean = assertDoesNotThrow(
                () -> context.getBean(type),
                () -> "No se pudo resolver bean de tipo: " + type.getName()
        );
        assertNotNull(bean, "El bean no debería ser nulo");
        assertTrue(type.isInstance(bean) || type.isAssignableFrom(bean.getClass()),
                "El bean obtenido no es del tipo esperado (puede ser un proxy)");
    }

    static Stream<Class<?>> beanTypes() {
        return Stream.of(
                UniversityController.class,
                IUniversityService.class,
                UniversityService.class,
                UniversityMapper.class,
                UniversityRepository.class,
                GlobalExceptionHandler.class
        );

    }
}
