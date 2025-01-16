package com.aluracurso.LiteraturaChallenge.service;

import com.aluracurso.LiteraturaChallenge.model.AutorEntity;
import com.aluracurso.LiteraturaChallenge.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorEntity> listarAutores() {
        return autorRepository.findAll();
    }

    /**
     * Filtra autores vivos en un año determinado (ej: anio = 1900).
     * Autor "vivo" en 1900 si:
     *  - anioNacimiento <= 1900
     *  - (anioFallecimiento es null o > 1900)
     */
    public List<AutorEntity> autoresVivosEnAnio(int anio) {
        return autorRepository.findAll().stream()
                .filter(autor -> autor.getAnioNacimiento() != null && autor.getAnioNacimiento() <= anio)
                .filter(autor -> autor.getAnioFallecimiento() == null || autor.getAnioFallecimiento() > anio)
                .collect(Collectors.toList());
    }
}
