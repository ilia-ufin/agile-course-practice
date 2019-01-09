package ru.unn.agile.binarysearch.viewmodel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private ILogger logger;

    public void createViewModelWithLogger(final ILogger logger) {
        viewModel = new ViewModel();
        viewModel.setLogger(logger);
    }

    @Before
    public void  setUp() throws IOException {
        logger = new FakeLogger();
        this.createViewModelWithLogger(logger);
    }

    @Test
    public void canInitializeArrayValue() {
        assertEquals("", viewModel.getArrayInputProperty());
    }

    @Test
    public void canInitializeElementValue() {
       assertEquals("", viewModel.getElementInputProperty());
    }

    @Test
    public void canInitializeStatus() {
        assertEquals("", viewModel.getStatusProperty());
    }

    @Test
    public void canSetArrayValue() {
        int[] expected = new int[] {1, 2, 3};

        viewModel.setArrayInputProperty("1,2,3");

        assertArrayEquals(expected, viewModel.getBinarySearchArray());
    }

    @Test
    public void canSetElementsValue() {
        String expected = "2";

        viewModel.setElementInputProperty("2");

        assertEquals(expected, viewModel.getElementInputProperty());
    }

    @Test
    public void canFindExistingKey() {
        String expected = "Found key, index 1";

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("2");
        viewModel.search();

        assertEquals(expected, viewModel.getResultProperty());
    }

    @Test
    public void isSearchEnabledWithCorrectInput() {
        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("2");

        assertEquals(false, viewModel.isSearchDisabled());
    }

    @Test
    public void isStatusSetToBadArrayFormatWithIncorrectArrayInput() {
        viewModel.setArrayInputProperty("1yh,p2,_0w3");
        viewModel.setElementInputProperty("2");

        assertEquals(Status.BAD_ARRAY_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isStatusSetToBadElementFormatWithIncorrectElementInput() {
        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("h");

        assertEquals(Status.BAD_ELEMENT_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isSearchEnabledWithEmptyInput() {
        viewModel.setArrayInputProperty("");
        viewModel.setElementInputProperty("");

        assertEquals(true, viewModel.isSearchDisabled());
    }

    @Test
    public void isStatusSetToBadArraySortWithUnsortedArray() {
        viewModel.setArrayInputProperty("3,2,1");
        viewModel.setElementInputProperty("1");

        assertEquals(Status.BAD_ARRAY_SORT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isSearchSkippedWhenDisabled() {
        viewModel.setArrayInputProperty("");
        viewModel.setElementInputProperty("");
        viewModel.search();

        assertEquals("", viewModel.getResultProperty());
    }

    @Test
    public void canSearchFindMissingKey() {
        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("4");
        viewModel.search();

        assertEquals("Key not found", viewModel.getResultProperty());
    }

    @Test
    public void canGetArrayInputProperty() {
        viewModel.setArrayInputProperty("1,2,3");

        assertEquals(viewModel.getArrayInputProperty(), viewModel.arrayInputProperty().get());
    }

    @Test
    public void canGetElementInputProperty() {
        viewModel.setElementInputProperty("1");

        assertEquals(viewModel.getElementInputProperty(), viewModel.elementInputProperty().get());
    }

    @Test
    public void canGetStatusProperty() {
        viewModel.setElementInputProperty("bad string");

        assertEquals(viewModel.getStatusProperty(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetResultProperty() {
        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("3");
        viewModel.search();

        assertEquals(viewModel.getResultProperty(), viewModel.resultProperty().get());
    }
}
