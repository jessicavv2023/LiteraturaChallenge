package com.aluracurso.LiteraturaChallenge.repository;



import com.aluracurso.LiteraturaChallenge.model.LibroEntity;

import java.util.List;

/**
 * ------------------------------------------------------
 * REPOSITORIO LibroRepository (interfaz)
 * ------------------------------------------------------
 */
public interface LibroRepository {
    List<LibroEntity> findAll();
    List<LibroEntity> findForLanguage(String language);
    LibroEntity save(LibroEntity libro);
}
