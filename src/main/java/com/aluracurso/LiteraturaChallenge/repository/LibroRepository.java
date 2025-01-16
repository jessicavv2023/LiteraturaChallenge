package com.aluracurso.LiteraturaChallenge.repository;

import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    // Por si quisieras buscar libros por título (contenga algo)
    List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);

    // Por si quisieras buscar libros por lenguaje
    List<LibroEntity> findByLenguajeIgnoreCase(String lenguaje);

}
