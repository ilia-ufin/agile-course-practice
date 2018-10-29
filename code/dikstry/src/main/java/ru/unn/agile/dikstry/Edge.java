package ru.unn.agile.dikstry;

public class Edge {
    private int weight;
    private Vertex vertexLeft;
    private Vertex vertexRight;

    public Edge(final Vertex vertex1, final Vertex vertex2, final int weight) {
        this.vertexLeft = vertex1;
        this.vertexRight = vertex2;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

   /* public void setWeight(final double weight) {
        this.weight = weight;
    }*/

    public Vertex getVertexLeft() {
        return vertexLeft;
    }

    public Vertex getVertexRight() {
        return vertexRight;
    }
}
