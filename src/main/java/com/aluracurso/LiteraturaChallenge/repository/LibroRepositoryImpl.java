package com.aluracurso.LiteraturaChallenge.repository;


import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------
 * IMPLEMENTACIÓN EN MEMORIA LibroRepositoryImpl
 * ------------------------------------------------------
 */
@Repository
public class LibroRepositoryImpl implements LibroRepository {

    private static long SEQUENCE = 1;
    private final List<LibroEntity> data = new ArrayList<>();

    @Override
    public List<LibroEntity> findAll() {
        return data;
    }

    @Override
    public List<LibroEntity> findForLanguage(String language) {
        List<LibroEntity> encontrados = new ArrayList<>();
        for (LibroEntity libro : data) {
            if (libro.getLenguaje() != null
                    && libro.getLenguaje().equalsIgnoreCase(language)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    @Override
    public LibroEntity save(LibroEntity libro) {
        if (libro.getId() == null) {
            libro.setId(SEQUENCE++);
            data.add(libro);
        } else {
            // Update no implementado en este ejemplo
        }

        // Guardar/actualizar su autor
        if (libro.getAutor() != null) {
            // En un caso real, deberíamos persistir o actualizar
            // el autor en AutorRepository. Aquí simplificado.
        }

        return libro;
    }
}
