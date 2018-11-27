package ru.unn.agile.dijkstra.model;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private static final int INF = Integer.MAX_VALUE;
    private List<Vertex> vertexes;
    private List<Edge> edges;
    private Map<Vertex, Way> ways;

    public Graph(final List<Edge> edges) {
        List<Vertex> vertexes = new LinkedList<>();
        for (Edge edge:edges) {
            vertexes.add(edge.getVertexLeft());
            vertexes.add(edge.getVertexRight());
        }
        validateGraph(edges);

        this.vertexes = vertexes.stream().distinct().collect(Collectors.toList());
        this.edges = edges;
        this.ways = initWays(vertexes);
    }

    public Graph(final int[][] matrix) {
        validateMatrix(matrix);
        List<Vertex> vertices = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            vertices.add(new Vertex(i));
        }
        List<Edge> edges = initEdges(matrix, vertices);
        validateGraph(edges);

        this.vertexes = vertices;
        this.edges = edges;
        this.ways = initWays(vertices);
    }

    private void validateGraph(final List<Edge> edges) {
        if (edges.isEmpty()) {
            throw new IllegalArgumentException("Graph don't have any edges");
        }
        for (Edge edge : edges) {
            if (edge.getVertexLeft() == null || edge.getVertexRight() == null) {
                throw new IllegalArgumentException("Vertex can't be null");
            }
            if (edge.getWeight() < 0) {
                throw new IllegalArgumentException("Matrix can't have negative weight");
            }
        }
    }

    private Map<Vertex, Way> initWays(final List<Vertex> vertices) {
        Map<Vertex, Way> result = new HashMap<Vertex, Way>();
        for (Vertex vertex: vertices) {
            Way way = new Way(vertices, INF);
            result.put(vertex, way);
        }
        return result;
    }

    private List<Edge> initEdges(final int[][] matrix, final List<Vertex> vertices) {
        List<Edge> edges = new LinkedList<>();
        for (Vertex vertexI : vertices) {
            for (Vertex vertexJ : vertices) {
                int weight = matrix[vertexI.getId()][vertexJ.getId()];
                if (weight != 0) {
                    edges.add(new Edge(vertexI, vertexJ, weight));
                }
            }
        }
        return edges;
    }

    private void validateMatrix(final int[][] matrix) {
        int numberOfVertex = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            if (numberOfVertex != matrix[i].length) {
                throw new IllegalArgumentException("Matrix must be square");
            }
        }
    }

    public int getEdgesSize() {
        return edges.size();
    }

    public int dijkstra(final Vertex startVertex, final Vertex endVertex) {
        isVertexInGraph(startVertex);
        isVertexInGraph(endVertex);

        ways.get(startVertex).setWeight(0);
        Set<Vertex> settledVertex = new HashSet<>();
        List<Vertex> unsettledVertex = new ArrayList<>();
        unsettledVertex.add(startVertex);

        while (!unsettledVertex.isEmpty()) {
            Vertex currentVertex = getLowestDistanceVertex(unsettledVertex, ways);
            unsettledVertex.remove(currentVertex);
            for (Edge edge: edges) {
                checkCondition(edge, currentVertex, settledVertex, unsettledVertex);
            }
            settledVertex.add(currentVertex);
        }
        return ways.get(endVertex).getWeight();
    }

    private void checkCondition(final Edge edge, final Vertex currentVertex,
                                final Set<Vertex> settledVertex, final List<Vertex> unsetlVertex) {
        if (edge.getVertexLeft().equals(currentVertex)
                && (!settledVertex.contains(edge.getVertexRight()))) {
            calculateMinimumDistance(edge, ways);
            if (!unsetlVertex.contains(edge.getVertexRight())) {
                unsetlVertex.add(edge.getVertexRight());
            }
        }
    }

    private Vertex getLowestDistanceVertex(final List<Vertex> unsettledVertex,
                                           final Map<Vertex, Way> ways) {
        int lowestDistance = INF;
        Vertex lowestDistanceVertex = null;
        for (Vertex vertex : unsettledVertex) {
            if (ways.get(vertex).getWeight() < lowestDistance) {
                lowestDistance = ways.get(vertex).getWeight();
                lowestDistanceVertex = vertex;
            }
        }
        return lowestDistanceVertex;
    }

    private void calculateMinimumDistance(final Edge edge, final Map<Vertex, Way> ways) {
        Vertex leftVertexOfEdge = edge.getVertexLeft();
        Vertex rightVertexOfEdge = edge.getVertexRight();
        ways.get(leftVertexOfEdge).upDateWay(edge, ways.get(rightVertexOfEdge));
    }

    private void isVertexInGraph(final Vertex vertex) {
        if (!vertexes.contains(vertex)) {
            throw new IllegalArgumentException(
                    String.format("Vertex %s not contains in graph", vertex)
            );
        }
    }
}
