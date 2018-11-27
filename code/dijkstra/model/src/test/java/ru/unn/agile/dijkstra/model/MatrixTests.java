package ru.unn.agile.dijkstra.model;

import org.junit.Test;


import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixTests {
    private static final double DELTA = 0.01;
    private static final int INF = Integer.MAX_VALUE;

    @Test
    public void canInitVertex() {
        Vertex vertex = new Vertex(1);

        int idVertex = vertex.getId();

        assertEquals(1, idVertex);
    }

    @Test
    public void canInitVertexWithOtherId() {
        Vertex vertex = new Vertex(2);

        int idVertex = vertex.getId();

        assertEquals(2, idVertex);
    }

    @Test
    public void canInitEdgeWithOtherId() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);

        Edge edge = new Edge(vertex1, vertex2, 1);

        assertEquals(1, edge.getWeight(), DELTA);
    }

    @Test
    public void canWayAddVertexAfterUpdate() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(8);
        Vertex vertex3 = new Vertex(4);
        Edge edge = new Edge(vertex2, vertex3, 4);
        List<Vertex> vertices1 = new LinkedList<>();
        List<Vertex> vertices2 = new LinkedList<>();
        vertices1.add(vertex1);
        vertices1.add(vertex2);
        vertices2.add(vertex3);
        Way way = new Way(vertices1, 4);
        Way way1 = new Way(vertices2, INF);


        way.upDateWay(edge, way1);


        assertEquals(2, way1.getVertexes().size());
    }

    @Test
    public void canWayAddWeightToNewVertex() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        List<Vertex> vertices = new LinkedList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        Way way = new Way(vertices, INF);

        way.setWeight(7);

        assertEquals(7, way.getWeight());
    }

    @Test
    public void canWayUpdateNewWeightToRightVertexRight() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        List<Vertex> vertices1 = new LinkedList<>();
        List<Vertex> vertices2 = new LinkedList<>();
        vertices1.add(vertex1);
        vertices1.add(vertex2);
        vertices2.add(vertex2);
        vertices2.add(vertex3);
        Edge edge = new Edge(vertex1, vertex2, 1);
        Way way = new Way(vertices1, 5);
        Way way1 = new Way(vertices2, 7);

        way.upDateWay(edge, way1);

        assertEquals(6, way1.getWeight());
    }

    @Test
    public void canEdgeGetVertexLeft() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Edge edge = new Edge(vertex1, vertex2, 1);

        int vertexId = edge.getVertexLeft().getId();

        assertEquals(1, vertexId);
    }

    @Test
    public void canEdgeGetVertexRight() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Edge edge = new Edge(vertex1, vertex2, 1);

        int idRightVertex = edge.getVertexRight().getId();

        assertEquals(2, idRightVertex, DELTA);
    }

    @Test
    public void canInitGraph() {
        int[][] matrix = {
                {
                        0, 2
                },
                {
                        3, 0
                }
        };
        Graph graph = new Graph(matrix);

        int edgesSize = graph.getEdgesSize();

        assertEquals(2, edgesSize);
    }

    @Test
    public void canInitGraphWithThreeEdges() {
        int[][] matrix = {
                {
                        0, 2, 5
                },
                {
                        0, 0, 1
                },
                {
                        0, 2, 0
                }
        };
        Graph graph = new Graph(matrix);

        int edgesSize = graph.getEdgesSize();

        assertEquals(4, edgesSize);
    }

    @Test
    public void canGraphValidateMatrix() {
        int[][] matrix = {
                {
                        0, 2
                },
                {
                        0, 0, 1
                },
                {
                        0, 2, 0
                }
        };


        try {
            new Graph(matrix);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Matrix must be square"));
        }
    }

    @Test
    public void isGraphCheckNegativeWeight() {
        int[][] matrix = {
                {
                        0, 4, -2
                },
                {
                        -1, 0, 1
                },
                {
                        0, 1, 0
                }
        };


        try {
            new Graph(matrix);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Matrix can't have negative weight"));
        }
    }

    @Test
    public void shouldReturnErrorIfGraphIsEmpty() {
        int[][] matrix = {
                {
                        0, 0, 0
                },
                {
                        0, 0, 0
                },
                {
                        0, 0, 0
                }
        };
        try {
            new Graph(matrix);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Graph don't have any edges"));
        }
    }

    @Test
    public void isDistanceToStartingVertexCalculatedCorrectly() {
        int[][] matrix = {
                {
                        0, 2, 0
                },
                {
                        0, 0, 0
                },
                {
                        1, 0, 0
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(0);
        Vertex vertexOfEnd = new Vertex(0);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(0, minDistance);
    }

    @Test
    public void isDistanceOnStartToFirstVertexCalculatesCorrectly() {
        int[][] matrix = {
                {
                        0, 7, 9
                },
                {
                        10, 0, 0
                },
                {
                        11, 0, 0
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(0);
        Vertex vertexOfEnd = new Vertex(1);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(7, minDistance);
    }

    @Test
    public void isLowestDistanceWithOneVertexInDistance() {
        int[][] matrix = {
                {
                        0, 1, 3
                },
                {
                        3, 0, 10
                },
                {
                        9, 0, 10
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(0);
        Vertex vertexOfEnd = new Vertex(2);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(3, minDistance);
    }

    @Test
    public void isLowestDistanceWithTwoVertexInWay() {
        int[][] matrix = {
                {
                        0, 4, 2
                },
                {
                        1, 0, 0
                },
                {
                        0, 1, 0
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(0);
        Vertex vertexOfEnd = new Vertex(1);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(3, minDistance);
    }

    @Test
    public void isLowestDistanceWithThreeVertexInWay() {
        int[][] matrix = {
                {
                        0, 1, 4
                },
                {
                        1, 0, 1
                },
                {
                        0, 1, 0
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(0);
        Vertex vertexOfEnd = new Vertex(2);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(2, minDistance);
    }

    @Test
    public void isLowestDistanceThenStartedVertexNotZero() {
        int[][] matrix = {
                {
                        0, 1, 4
                },
                {
                        1, 0, 10
                },
                {
                        0, 1, 0
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(1);
        Vertex vertexOfEnd = new Vertex(2);

        int minDistance = graph.dijkstra(vertexOfStart, vertexOfEnd);

        assertEquals(5, minDistance);
    }

    @Test
    public void isRightDistanceInGraphWithForeVertex() {
        int[][] matrix = {
                {
                        0, 1, 4, 1
                },
                {
                        1, 0, 10, 0
                },
                {
                        0, 1, 0, 6
                },
                {
                        2, 1, 0, 13
                }
        };
        Graph graph = new Graph(matrix);
        Vertex vertexOfStart = new Vertex(1);
        Vertex vertexOfEnd = new Vertex(3);
        assertEquals(2, graph.dijkstra(vertexOfStart, vertexOfEnd));
    }

    @Test
    public void canInitGraphByEdges() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(8);
        Vertex vertex3 = new Vertex(4);

        Edge edge1 = new Edge(vertex1, vertex2, 4);
        Edge edge2 = new Edge(vertex2, vertex3, 5);

        List<Edge> edges = new LinkedList<Edge>();
        edges.add(edge1);
        edges.add(edge2);

        Graph graph = new Graph(edges);
        assertEquals(2, graph.getEdgesSize());
    }

    @Test
    public void canCalculateGraphInitiatedByEdges() {
        Vertex vertex1 = new Vertex(3);
        Vertex vertex2 = new Vertex(10);
        Vertex vertex3 = new Vertex(6);

        Edge edge1 = new Edge(vertex1, vertex2, 10);
        Edge edge2 = new Edge(vertex2, vertex3, 5);

        List<Edge> edges = new LinkedList<Edge>();
        edges.add(edge1);
        edges.add(edge2);

        Graph graph = new Graph(edges);

        assertEquals(15, graph.dijkstra(vertex1, vertex3));
    }

    @Test
    public void canCalculateGraphWhenVertexOutOfGraph() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);

        Edge edge1 = new Edge(vertex1, vertex2, 2);
        Edge edge2 = new Edge(vertex2, vertex3, 4);

        List<Edge> edges = new LinkedList<Edge>();
        edges.add(edge1);
        edges.add(edge2);

        Graph graph = new Graph(edges);
        try {
            Vertex vertexOutOfGraph = new Vertex(4);
            graph.dijkstra(vertexOutOfGraph, vertex3);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Vertex id = 4 not contains in graph"));
        }
    }

    @Test
    public void canCalculateGraphByVertexInGraph() {
        Vertex vertex1 = new Vertex(3);
        Vertex vertex2 = new Vertex(4);
        Vertex vertex3 = new Vertex(5);

        Edge edge1 = new Edge(vertex1, vertex2, 1);
        Edge edge2 = new Edge(vertex2, vertex3, 2);

        List<Edge> edges = new LinkedList<Edge>();
        edges.add(edge1);
        edges.add(edge2);

        Graph graph = new Graph(edges);

        Vertex vertexInGraph = new Vertex(3);
        assertEquals(3, graph.dijkstra(vertexInGraph, vertex3));
    }
}
