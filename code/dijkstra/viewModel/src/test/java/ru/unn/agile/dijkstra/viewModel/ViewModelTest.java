package ru.unn.agile.dijkstra.viewModel;

import org.junit.Test;
import ru.unn.agile.dijkstra.model.Vertex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {

    public  String getResourceAsString(final String path) {
        try {
            URL url = getClass().getResource(path);
            return new String(Files.readAllBytes(Paths.get(url.toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void validateSimpleMatrix() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("SimpleGraph.json"));

        viewModel.graphInit();

        assertEquals("Success", viewModel.getStatus());
    }

    @Test
    public void validateNonNumericMatrix() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue("qq");

        viewModel.graphInit();

        assertEquals("Invalid Input Json", viewModel.getStatus());
    }

    @Test
    public void validateEmptyNumericMatrix() {
        ViewModel viewModel = new ViewModel();

        viewModel.graphInit();

        assertEquals("Invalid Input Json", viewModel.getStatus());
    }

    @Test
    public void validateGraphWithNegativeEdgesMatrix() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("JsonWithNegativeEdge.json"));

        viewModel.graphInit();

        assertEquals("Matrix can't have negative weight", viewModel.getStatus());
    }

    @Test
    public void validateGraphWithoutEdges() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("JsonWithoutEdges.json"));

        viewModel.graphInit();

        assertEquals("Graph don't have any edges", viewModel.getStatus());
    }

    @Test
    public void validateGraphWithoutWeightInEdge() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString(
                "JsonWithoutMandatoryParameterInEdge.json"));

        viewModel.graphInit();

        assertEquals("Vertex can't be null", viewModel.getStatus());
    }

    @Test
    public void validateStartVertex() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("SimpleGraph.json"));
        viewModel.startVertexProperty().setValue("1");

        Vertex vertex = viewModel.parseVertex(viewModel.startVertexProperty());

        assertEquals(1, vertex.getId());
    }

    @Test
    public void validateInvalidStartVertex() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("SimpleGraph.json"));
        viewModel.startVertexProperty().setValue("asd");

        viewModel.parseVertex(viewModel.startVertexProperty());

        assertEquals("Invalid Input Value: asd", viewModel.getStatus());
    }

    @Test
    public void canCalculateGraph() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("SimpleGraph.json"));
        viewModel.startVertexProperty().setValue("1");
        viewModel.finishVertexProperty().setValue("3");

        viewModel.calculate();

        assertEquals("15", viewModel.getResult());
    }

    @Test
    public void canCalculateGraphByVertexOutOfGraph() {
        ViewModel viewModel = new ViewModel();
        viewModel.matrixProperty().setValue(getResourceAsString("SimpleGraph.json"));
        viewModel.startVertexProperty().setValue("1");
        viewModel.finishVertexProperty().setValue("6");

        viewModel.calculate();

        assertEquals("Vertex id = 6 not contains in graph", viewModel.getStatus());
    }
}
