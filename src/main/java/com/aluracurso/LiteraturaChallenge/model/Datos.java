package com.aluracurso.LiteraturaChallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        List<DatosLibro> results
) {
}
