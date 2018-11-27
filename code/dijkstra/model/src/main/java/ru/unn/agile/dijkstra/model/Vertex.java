package ru.unn.agile.dijkstra.model;

public class Vertex {
    private int id;

    public Vertex(final int id) {
        this.id = id;
    }

    public Vertex() {
       id = 0;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Vertex)) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return getId() == vertex.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
}
