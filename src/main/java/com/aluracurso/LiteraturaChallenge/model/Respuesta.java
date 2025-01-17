package com.aluracurso.LiteraturaChallenge.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Respuesta {

    private int count;
    private String next;
    private String previous;
    private List<Resultado> results;  // <-- este campo es importante

    public Respuesta() {
    }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String getNext() { return next; }
    public void setNext(String next) { this.next = next; }

    public String getPrevious() { return previous; }
    public void setPrevious(String previous) { this.previous = previous; }

    public List<Resultado> getResults() { return results; }
    public void setResults(List<Resultado> results) { this.results = results; }

    @Override
    public String toString() {
        return "Respuesta{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
