package com.aluracurso.LiteraturaChallenge.repository;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    List<AutorEntity> findForYear(int year);
}
