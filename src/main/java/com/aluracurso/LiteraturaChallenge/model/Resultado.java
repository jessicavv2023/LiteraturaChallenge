package com.aluracurso.LiteraturaChallenge.model;

import com.aluracurso.LiteraturaChallenge.dto.AutorGuten;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Resultado(
        @JsonAlias("id") int id,
        @JsonAlias("title") String title,
        List<AutorGuten> authors,
        List<String> languages,
        @JsonAlias("download_count") int download_count
) {}
