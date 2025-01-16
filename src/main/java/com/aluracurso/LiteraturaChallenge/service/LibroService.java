package com.aluracurso.LiteraturaChallenge.service;

import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import com.aluracurso.LiteraturaChallenge.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<LibroEntity> listarLibros() {
        return libroRepository.findAll();
    }

    public Optional<LibroEntity> buscarLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public List<LibroEntity> buscarLibroPorTitulo(String titulo) {
        // Usamos el query method que definimos en la interfaz (findByTituloContainingIgnoreCase)
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<LibroEntity> buscarLibrosPorIdioma(String idioma) {
        return libroRepository.findByLenguajeIgnoreCase(idioma);
    }

    // guardar libros nuevos en la BD:
    public LibroEntity guardarLibro(LibroEntity libro) {
        return libroRepository.save(libro);
    }
}
