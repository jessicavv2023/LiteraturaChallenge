package com.aluracurso.LiteraturaChallenge.repository;

import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findForLanguage(String language);
}
