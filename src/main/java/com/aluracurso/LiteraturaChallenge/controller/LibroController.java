package com.aluracurso.LiteraturaChallenge.controller;

import com.aluracurso.LiteraturaChallenge.model.LibroEntity;
import com.aluracurso.LiteraturaChallenge.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Endpoint que lista todos los libros en la BD.
     * GET: /api/libros
     */
    @GetMapping
    public List<LibroEntity> listarLibros() {
        return libroService.listarLibros();
    }

    /**
     * Endpoint que busca libro(s) por título parcial o exacto.
     * GET: /api/libros/buscar?titulo=Lo que sea
     */
    @GetMapping("/buscar")
    public List<LibroEntity> buscarPorTitulo(@RequestParam("titulo") String titulo) {
        return libroService.buscarLibroPorTitulo(titulo);
    }

    /**
     * Endpoint que lista libros por idioma
     * GET: /api/libros/idioma?lang=es
     */
    @GetMapping("/idioma")
    public List<LibroEntity> buscarPorIdioma(@RequestParam("lang") String lang) {
        return libroService.buscarLibrosPorIdioma(lang);
    }

    /**
     * Ejemplo de obtener un libro por su ID
     * GET: /api/libros/10
     */
    @GetMapping("/{id}")
    public LibroEntity libroPorId(@PathVariable Long id) {
        return libroService.buscarLibroPorId(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID " + id));
    }
}
