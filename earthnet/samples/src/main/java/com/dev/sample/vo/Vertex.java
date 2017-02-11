package com.dev.sample.vo;

/**
 * Created by AjayMenon on 2/10/2017.
 */
public class Vertex<T> {

    private T name;

    private T value;

    private Vertex<T> [] adjacentVertices;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Vertex<T>[] getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(Vertex<T>[] adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }
}
