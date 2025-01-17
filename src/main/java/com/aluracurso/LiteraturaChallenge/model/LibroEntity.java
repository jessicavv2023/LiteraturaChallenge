package com.aluracurso.LiteraturaChallenge.model;

import com.aluracurso.LiteraturaChallenge.dto.AutorGuten;

import java.time.LocalDate;

public class LibroEntity {

    private Long id;
    private String titulo;
    private String lenguaje;
    private int descargas;
    private AutorEntity autor;

    // Constructor vacío
    public LibroEntity() {
    }

    // Constructor que mapea un Resultado (record) de la API Gutendex
    public LibroEntity(Resultado resultado) {
        // Asignar título (record => uso de resultado.title())
        this.titulo = resultado.title();

        // Asignar idioma principal
        if (resultado.languages() != null && !resultado.languages().isEmpty()) {
            this.lenguaje = resultado.languages().get(0);
        }

        // Asignar descargas
        this.descargas = resultado.download_count();

        // Tomar el primer autor (si existe)
        if (resultado.authors() != null && !resultado.authors().isEmpty()) {
            AutorGuten autorGuten = resultado.authors().get(0);
            // Como AutorGuten es una clase, usamos .getName(), etc.

            AutorEntity autorEntity = new AutorEntity();
            autorEntity.setNombre(autorGuten.getName());
            if (autorGuten.getBirth_year() != null) {
                autorEntity.setFechaNacimiento(LocalDate.of(autorGuten.getBirth_year(), 1, 1));
            }
            if (autorGuten.getDeath_year() != null) {
                autorEntity.setFechaFallecimiento(LocalDate.of(autorGuten.getDeath_year(), 1, 1));
            }

            // Vincular libro y autor
            this.autor = autorEntity;
            autorEntity.getLibros().add(this);
        }
    }

    // Getters y Setters ...
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getDescargas() {
        return descargas;
    }
    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public AutorEntity getAutor() {
        return autor;
    }
    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }
}
