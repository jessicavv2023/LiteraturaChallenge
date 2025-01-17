package com.aluracurso.LiteraturaChallenge.model;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
