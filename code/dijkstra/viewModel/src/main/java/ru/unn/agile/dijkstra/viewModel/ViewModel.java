package ru.unn.agile.dijkstra.viewModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.*;
import ru.unn.agile.dijkstra.model.Edge;
import ru.unn.agile.dijkstra.model.Graph;
import ru.unn.agile.dijkstra.model.Vertex;

import java.io.IOException;
import java.util.List;


public class ViewModel {

    private final StringProperty matrix = new SimpleStringProperty();
    private final StringProperty startVertex = new SimpleStringProperty();
    private final StringProperty finishVertex = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final ObjectMapper mapper = new ObjectMapper();

    public ViewModel() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        result.set("");
        matrix.set("");
        startVertex.set("");
        finishVertex.set("");
        status.set("Click Calculate Button after populating data");
    }

    public void calculate() {
        try {
            Graph graph = graphInit();
            Vertex startVertex = parseVertex(startVertexProperty());
            Vertex finishVertex = parseVertex(finishVertexProperty());

            int weight = graph.dijkstra(startVertex, finishVertex);

            result.set(String.valueOf(weight));
            status.set(new Status(StatusType.SUCCESS).toString());

        } catch (IllegalArgumentException e) {
            status.set(new Status(StatusType.BAD_REQUEST, e.getMessage()).toString());
        }
    }

    public Vertex parseVertex(final StringProperty value) {
        try {
            return new Vertex(Integer.parseInt(value.getValue()));
        } catch (NumberFormatException e) {
            status.setValue(new Status(StatusType.BAD_REQUEST,
                    String.format("Invalid Input Value: %s", value.getValue()))
                    .toString());
        }
        return null;
    }

    public Graph graphInit() {
        try {
            List<Edge> edges = mapper.readValue(matrix.get(),
                mapper.getTypeFactory().constructCollectionType(List.class, Edge.class));

            Graph graph = new Graph(edges);
            status.set(new Status(StatusType.SUCCESS).toString());

            return graph;

        } catch (IOException e) {
            status.set(new Status(StatusType.BAD_REQUEST, "Invalid Input Json").toString());
        } catch (IllegalArgumentException e) {
            status.set(new Status(StatusType.BAD_REQUEST, e.getMessage()).toString());
        }
        return null;
    }

    public StringProperty matrixProperty() {
        return matrix;
    }

    public StringProperty startVertexProperty() {
        return startVertex;
    }

    public StringProperty finishVertexProperty() {
        return finishVertex;
    }

    public final String getResult() {
        return result.get();
    }

    public final String getStatus() {
        return status.get();
    }
}
