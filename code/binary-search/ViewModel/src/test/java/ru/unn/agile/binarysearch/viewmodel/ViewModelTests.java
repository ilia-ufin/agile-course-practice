package ru.unn.agile.binarysearch.viewmodel;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {

    @Test
    public void canInitializeArrayValue() {
        ViewModel viewModel = new ViewModel();

        assertEquals("", viewModel.getArrayInputProperty());
    }

    @Test
    public void canInitializeElementValue() {
        ViewModel viewModel = new ViewModel();

        assertEquals("", viewModel.getElementInputProperty());
    }

    @Test
    public void canInitializeStatus() {
        ViewModel viewModel = new ViewModel();

        assertEquals("", viewModel.getStatusProperty());
    }

    @Test
    public void canSetArrayValue() {
        ViewModel viewModel = new ViewModel();
        int[] expected = new int[] {1, 2, 3};

        viewModel.setArrayInputProperty("1,2,3");

        assertArrayEquals(expected, viewModel.getBinarySearchArray());
    }

    @Test
    public void canSetElementValue() {
        ViewModel viewModel = new ViewModel();
        String expected = "2";

        viewModel.setElementInputProperty("2");

        assertEquals(expected, viewModel.getElementInputProperty());
    }

    @Test
    public void canFindExistingKey() {
        ViewModel viewModel = new ViewModel();
        String expected = "Found key, index 1";

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("2");
        viewModel.search();

        assertEquals(expected, viewModel.getResultProperty());
    }

    @Test
    public void isSearchEnabledWithCorrectInput() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("2");

        assertEquals(false, viewModel.isSearchDisabled());
    }

    @Test
    public void isStatusSetToBadArrayFormatWithIncorrectArrayInput() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1yh,p2,_0w3");
        viewModel.setElementInputProperty("2");

        assertEquals(Status.BAD_ARRAY_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isStatusSetToBadElementFormatWithIncorrectElementInput() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("h");

        assertEquals(Status.BAD_ELEMENT_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isSearchEnabledWithEmptyInput() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("");
        viewModel.setElementInputProperty("");

        assertEquals(true, viewModel.isSearchDisabled());
    }

    @Test
    public void isStatusSetToBadArraySortWithUnsortedArray() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("3,2,1");
        viewModel.setElementInputProperty("1");

        assertEquals(Status.BAD_ARRAY_SORT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void isSearchSkippedWhenDisabled() {
        ViewModel viewModel = new ViewModel();

        viewModel.search();

        assertEquals("", viewModel.getResultProperty());
    }

    @Test
    public void canSearchFindMissingKey() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("4");
        viewModel.search();

        assertEquals("Key not found", viewModel.getResultProperty());
    }

    @Test
    public void canGetArrayInputProperty() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1,2,3");

        assertEquals(viewModel.getArrayInputProperty(), viewModel.arrayInputProperty().get());
    }

    @Test
    public void canGetElementInputProperty() {
        ViewModel viewModel = new ViewModel();

        viewModel.setElementInputProperty("1");

        assertEquals(viewModel.getElementInputProperty(), viewModel.elementInputProperty().get());
    }

    @Test
    public void canGetStatusProperty() {
        ViewModel viewModel = new ViewModel();

        viewModel.setElementInputProperty("bad string");

        assertEquals(viewModel.getStatusProperty(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetResultProperty() {
        ViewModel viewModel = new ViewModel();

        viewModel.setArrayInputProperty("1,2,3");
        viewModel.setElementInputProperty("3");
        viewModel.search();

        assertEquals(viewModel.getResultProperty(), viewModel.resultProperty().get());
    }
}
