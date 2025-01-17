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
        // Si no tiene ID, se lo asignamos
        if (libro.getId() == null) {
            libro.setId(SEQUENCE++);
        }
        // Agregamos el libro a la lista en memoria
        data.add(libro);

        // (O si quisieras un "update" cuando ID no es null,
        // tendrías que buscar y reemplazar en 'data'.
        // Para este ejemplo, nos basta con "añadir".)

        return libro;
    }
}
