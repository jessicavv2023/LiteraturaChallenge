package com.aluracurso.LiteraturaChallenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String lenguaje;
    private int descargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorEntity autor;

    public LibroEntity(LibroEntity libroEntity) {
    }

    public LibroEntity(String titulo, String lenguaje, int descargas, AutorEntity autor) {
        this.titulo = titulo;
        this.lenguaje = lenguaje;
        this.descargas = descargas;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public int getDescargas() {
        return descargas;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }
}
