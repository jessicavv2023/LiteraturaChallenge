package com.aluracurso.LiteraturaChallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ------------------------------------------------------
 * DTO AutorGuten
 * Representa la información de un autor (API Gutendex)
 * ------------------------------------------------------
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public class AutorGuten {
    private String name;
    private Integer birth_year;
    private Integer death_year;

    public String name() { return name; }
    public Integer birth_year() { return birth_year; }
    public Integer death_year() { return death_year; }

    @Override
    public String toString() {
        return "AutorGuten{" +
                "name='" + name + '\'' +
                ", birth_year=" + birth_year +
                ", death_year=" + death_year +
                '}';
    }
}


