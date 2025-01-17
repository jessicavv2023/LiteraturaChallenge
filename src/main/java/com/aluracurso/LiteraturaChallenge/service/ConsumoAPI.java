package com.aluracurso.LiteraturaChallenge.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * ------------------------------------------------------
 * CLASE ConsumoAPI
 * Encargada de hacer la petición HTTP a la API
 * ------------------------------------------------------
 */
@Service
public class ConsumoAPI {

    public String obtenerDatos(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
