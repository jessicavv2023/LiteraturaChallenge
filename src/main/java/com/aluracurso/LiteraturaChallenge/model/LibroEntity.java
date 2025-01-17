package com.aluracurso.LiteraturaChallenge.model;



import com.aluracurso.LiteraturaChallenge.dto.AutorGuten;


import java.time.LocalDate;

/**
 * ------------------------------------------------------
 * ENTIDAD LibroEntity (POJO en memoria)
 * ------------------------------------------------------
 */
public class LibroEntity {
    private Long id;
    private String titulo;
    private String lenguaje;
    private int descargas;
    private AutorEntity autor;

    public LibroEntity() {
    }

    /**
     * Constructor que mapea un Resultado (de la API) a nuestra entidad
     */
    public LibroEntity(Resultado resultado) {
        this.titulo = resultado.title();
        if (resultado.languages() != null && !resultado.languages().isEmpty()) {
            this.lenguaje = resultado.languages().get(0);
        }
        this.descargas = resultado.download_count();

        // Tomamos el primer autor, si existe
        if (resultado.authors() != null && !resultado.authors().isEmpty()) {
            AutorGuten autorGuten = resultado.authors().get(0);
            AutorEntity autorEntity = new AutorEntity();
            autorEntity.setNombre(autorGuten.name());

            if (autorGuten.birth_year() != null) {
                autorEntity.setFechaNacimiento(LocalDate.of(autorGuten.birth_year(), 1, 1));
            }
            if (autorGuten.death_year() != null) {
                autorEntity.setFechaFallecimiento(LocalDate.of(autorGuten.death_year(), 1, 1));
            }
            // Vinculamos ambos
            this.autor = autorEntity;
            autorEntity.getLibros().add(this);
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getLenguaje() { return lenguaje; }
    public void setLenguaje(String lenguaje) { this.lenguaje = lenguaje; }

    public int getDescargas() { return descargas; }
    public void setDescargas(int descargas) { this.descargas = descargas; }

    public AutorEntity getAutor() { return autor; }
    public void setAutor(AutorEntity autor) { this.autor = autor; }
}
