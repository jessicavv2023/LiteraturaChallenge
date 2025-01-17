package com.aluracurso.LiteraturaChallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * ------------------------------------------------------
 * CLASE ConvierteDatos
 * Encargada de parsear JSON a objetos
 * ------------------------------------------------------
 */
@Service
public class ConvierteDatos {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T obtnerDatos(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
