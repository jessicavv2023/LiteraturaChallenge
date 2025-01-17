package com.aluracurso.LiteraturaChallenge.repository;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;


import java.util.List;

/**
 * ------------------------------------------------------
 * REPOSITORIO AutorRepository (interfaz)
 * ------------------------------------------------------
 */
public interface AutorRepository {
    List<AutorEntity> findAll();
    List<AutorEntity> findForYear(int year);
    AutorEntity save(AutorEntity autor);
}
