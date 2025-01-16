package com.aluracurso.LiteraturaChallenge.service;

public interface IConvertirDatos {
    <T> T obtnerDatos(String json, Class<T> clase);
}
