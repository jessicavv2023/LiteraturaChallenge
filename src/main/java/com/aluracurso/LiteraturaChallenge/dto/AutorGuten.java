package com.aluracurso.LiteraturaChallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorGuten {

    private String name;
    private Integer birth_year;
    private Integer death_year;

    // Constructor vacío (para Jackson)
    public AutorGuten() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    // Setters (si los necesitas)
    public void setName(String name) {
        this.name = name;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        return "AutorGuten{" +
                "name='" + name + '\'' +
                ", birth_year=" + birth_year +
                ", death_year=" + death_year +
                '}';
    }
}

