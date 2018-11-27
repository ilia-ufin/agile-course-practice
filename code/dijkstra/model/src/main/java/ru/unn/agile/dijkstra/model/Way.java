package ru.unn.agile.dijkstra.model;

import java.util.List;

public class Way {
    private List<Vertex> vertexes;
    private int weight;

    public Way(final List<Vertex> vertexes, final int weight) {
        this.vertexes = vertexes;
        this.weight = weight;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
        this.weight = weight;
    }

    private void addVertex(final Vertex vertex) {
        vertexes.add(vertex);
    }

    public void upDateWay(final Edge edge, final Way wayToRightVertex) {
        int actualWeight = weight + edge.getWeight();
        if (actualWeight < wayToRightVertex.getWeight()) {
            wayToRightVertex.setWeight(actualWeight);
            wayToRightVertex.addVertex(edge.getVertexLeft());
        }
    }

}
