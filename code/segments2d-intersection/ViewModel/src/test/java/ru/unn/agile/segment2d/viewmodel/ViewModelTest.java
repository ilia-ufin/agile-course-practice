package ru.unn.agile.segment2d.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void createViewModel() {
        viewModel = new ViewModel();
    }

    @After
    public void deleteViewModel() {
        viewModel = null;
    }

    @Test
    public void initViewModelWithDefaultValues() {
        assertEquals("", viewModel.getFirstSegmentFirstPointCoordX());
        assertEquals("", viewModel.getFirstSegmentFirstPointCoordY());
        assertEquals("", viewModel.getFirstSegmentSecondPointCoordX());
        assertEquals("", viewModel.getFirstSegmentSecondPointCoordY());
        assertEquals("", viewModel.getSecondSegmentFirstPointCoordX());
        assertEquals("", viewModel.getSecondSegmentFirstPointCoordY());
        assertEquals("", viewModel.getSecondSegmentSecondPointCoordX());
        assertEquals("", viewModel.getSecondSegmentSecondPointCoordY());
        assertEquals("", viewModel.getFirstSegmentStatus());
        assertEquals("", viewModel.getSecondSegmentStatus());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void viewModelPropertiesAreInitialized() {
        assertNotEquals(null, viewModel.firstSegmentFirstPointCoordXProperty());
        assertNotEquals(null, viewModel.firstSegmentFirstPointCoordYProperty());
        assertNotEquals(null, viewModel.firstSegmentSecondPointCoordXProperty());
        assertNotEquals(null, viewModel.firstSegmentSecondPointCoordYProperty());
        assertNotEquals(null, viewModel.secondSegmentFirstPointCoordXProperty());
        assertNotEquals(null, viewModel.secondSegmentFirstPointCoordYProperty());
        assertNotEquals(null, viewModel.secondSegmentSecondPointCoordXProperty());
        assertNotEquals(null, viewModel.secondSegmentSecondPointCoordYProperty());
        assertNotEquals(null, viewModel.firstSegmentStatusProperty());
        assertNotEquals(null, viewModel.secondSegmentStatusProperty());
        assertNotEquals(null, viewModel.resultProperty());
    }

    @Test
    public void canAllCoordFieldsBeSetted() {
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("1");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("1");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");

        assertEquals("1", viewModel.getFirstSegmentFirstPointCoordX());
        assertEquals("1", viewModel.getFirstSegmentFirstPointCoordY());
        assertEquals("1", viewModel.getFirstSegmentSecondPointCoordX());
        assertEquals("1", viewModel.getFirstSegmentSecondPointCoordY());
        assertEquals("1", viewModel.getSecondSegmentFirstPointCoordX());
        assertEquals("1", viewModel.getSecondSegmentFirstPointCoordY());
        assertEquals("1", viewModel.getSecondSegmentSecondPointCoordX());
        assertEquals("1", viewModel.getSecondSegmentSecondPointCoordY());
    }

    @Test
    public void canPrintRightStatusForInvalidFirstSegmentCoordsInput() {
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("d");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is incorrect!", viewModel.getFirstSegmentStatus());
    }

    @Test
    public void canPrintRightStatusForInvalidSecondSegmentCoordsInput() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("d");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is incorrect!", viewModel.getSecondSegmentStatus());
    }

    @Test
    public void canPrintRightStatusForInvalidFirstSegment() {
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("1");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is incorrect! both Point2D can not be the same",
                viewModel.getFirstSegmentStatus());
    }

    @Test
    public void canPrintRightStatusForInvalidSecondSegment() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("1");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is incorrect! both Point2D can not be the same",
                viewModel.getSecondSegmentStatus());
    }

    @Test
    public void canPrintRightStatusForValidFirstSegment() {
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("2");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is correct!", viewModel.getFirstSegmentStatus());
    }

    @Test
    public void canPrintRightStatusForValidSecondSegment() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("2");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segment is correct!", viewModel.getSecondSegmentStatus());
    }

    @Test
    public void canPrintRightResultForInvalidSegments() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("1");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("2");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Can't resolve intersection! Check segments.", viewModel.getResult());
    }

    @Test
    public void canPrintRightResultForNotIntersectedSegments() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("2");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");
        viewModel.setFirstSegmentFirstPointCoordX("1");
        viewModel.setFirstSegmentFirstPointCoordY("2");
        viewModel.setFirstSegmentSecondPointCoordX("1");
        viewModel.setFirstSegmentSecondPointCoordY("1");

        viewModel.checkIntersection();

        assertEquals("Segments don't intersect!", viewModel.getResult());
    }

    @Test
    public void canPrintRightResultForIntersectedSegments() {
        viewModel.setSecondSegmentFirstPointCoordX("1");
        viewModel.setSecondSegmentFirstPointCoordY("2");
        viewModel.setSecondSegmentSecondPointCoordX("1");
        viewModel.setSecondSegmentSecondPointCoordY("1");
        viewModel.setFirstSegmentFirstPointCoordX("0");
        viewModel.setFirstSegmentFirstPointCoordY("1.5");
        viewModel.setFirstSegmentSecondPointCoordX("2");
        viewModel.setFirstSegmentSecondPointCoordY("1.5");

        viewModel.checkIntersection();

        assertEquals("Segments intersect in point (1.0, 1.5)", viewModel.getResult());
    }
}
