package com.aluracurso.LiteraturaChallenge.model;

import com.aluracurso.LiteraturaChallenge.model.LibroEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------
 * ENTIDAD AutorEntity (POJO en memoria)
 * ------------------------------------------------------
 */
public class AutorEntity {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    private List<LibroEntity> libros = new ArrayList<>();

    public AutorEntity() {
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDate getFechaFallecimiento() { return fechaFallecimiento; }
    public void setFechaFallecimiento(LocalDate fechaFallecimiento) { this.fechaFallecimiento = fechaFallecimiento; }

    public List<LibroEntity> getLibros() { return libros; }
    public void setLibros(List<LibroEntity> libros) { this.libros = libros; }
}
