package com.aluracurso.LiteraturaChallenge.repository;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------
 * IMPLEMENTACIÓN EN MEMORIA AutorRepositoryImpl
 * ------------------------------------------------------
 */
@Repository
public class AutorRepositoryImp implements AutorRepository {

    private static long SEQUENCE = 1;
    private final List<AutorEntity> data = new ArrayList<>();

    @Override
    public List<AutorEntity> findAll() {
        return data;
    }

    @Override
    public List<AutorEntity> findForYear(int year) {
        List<AutorEntity> vivos = new ArrayList<>();
        for (AutorEntity autor : data) {
            int nacimiento = (autor.getFechaNacimiento() != null)
                    ? autor.getFechaNacimiento().getYear()
                    : Integer.MIN_VALUE;
            int fallecimiento = (autor.getFechaFallecimiento() != null)
                    ? autor.getFechaFallecimiento().getYear()
                    : Integer.MAX_VALUE;

            if (nacimiento <= year && year <= fallecimiento) {
                vivos.add(autor);
            }
        }
        return vivos;
    }

    @Override
    public AutorEntity save(AutorEntity autor) {
        if (autor.getId() == null) {
            autor.setId(SEQUENCE++);
            data.add(autor);
        } else {
            // Update no implementado en este ejemplo
        }
        return autor;
    }
}
