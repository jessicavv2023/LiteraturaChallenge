//package com.aluracurso.LiteraturaChallenge.controller;
//
//import com.aluracurso.LiteraturaChallenge.model.AutorEntity;
//import com.aluracurso.LiteraturaChallenge.service.AutorService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/autores")
//public class AutorController {
//
//    private final AutorService autorService;
//
//    public AutorController(AutorService autorService) {
//        this.autorService = autorService;
//    }
//
//    /**
//     * GET: /api/autores
//     * Lista todos los autores registrados
//     */
//    @GetMapping
//    public List<AutorEntity> listarAutores() {
//        return autorService.listarAutores();
//    }
//
//    /**
//     * GET: /api/autores/vivos?anio=1900
//     * Lista autores que estaban vivos en un año dado
//     */
//    @GetMapping("/vivos")
//    public List<AutorEntity> listarAutoresVivosEnAnio(@RequestParam("anio") int anio) {
//        return autorService.autoresVivosEnAnio(anio);
//    }
//}
