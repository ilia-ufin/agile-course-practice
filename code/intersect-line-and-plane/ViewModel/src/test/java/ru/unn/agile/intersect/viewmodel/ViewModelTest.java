package ru.unn.agile.intersect.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTest {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void createViewModel() {
        FakeTextLogger logger = new FakeTextLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void deleteViewModel() {
        viewModel = null;
    }

    @Test
    public void initViewModelWithDefaultValues() {
        assertTrue(isCoordinatesEmpty());
    }

    @Test
    public void initStatusesWithDefaultValues() {
        String expectedMessage = "Waiting for input";

        assertEquals(expectedMessage, viewModel.getPlaneStatus());
        assertEquals(expectedMessage, viewModel.getLineStatus());
    }

    @Test
    public void checkIfCoordinateAndResultPropertiesAreInitialized() {
        assertNotEquals(null, viewModel.coordXFirstPlanePointProperty());
        assertNotEquals(null, viewModel.coordYFirstPlanePointProperty());
        assertNotEquals(null, viewModel.coordZFirstPlanePointProperty());
        assertNotEquals(null, viewModel.coordXSecondPlanePointProperty());
        assertNotEquals(null, viewModel.coordYSecondPlanePointProperty());
        assertNotEquals(null, viewModel.coordZSecondPlanePointProperty());
        assertNotEquals(null, viewModel.coordXThirdPlanePointProperty());
        assertNotEquals(null, viewModel.coordYThirdPlanePointProperty());
        assertNotEquals(null, viewModel.coordZThirdPlanePointProperty());
        assertNotEquals(null, viewModel.coordXFirstLinePointProperty());
        assertNotEquals(null, viewModel.coordYFirstLinePointProperty());
        assertNotEquals(null, viewModel.coordZFirstLinePointProperty());
        assertNotEquals(null, viewModel.coordXSecondLinePointProperty());
        assertNotEquals(null, viewModel.coordYSecondLinePointProperty());
        assertNotEquals(null, viewModel.coordZSecondLinePointProperty());
        assertNotEquals(null, viewModel.resultProperty());
    }

    @Test
    public void checkIfStatusPropertiesAreInitialized() {
        String expectedMessage = "Waiting for input";

        assertEquals(expectedMessage, viewModel.planeStatusProperty().get());
        assertEquals(expectedMessage, viewModel.lineStatusProperty().get());
    }

    @Test
    public void canSetPlaneCoordinates() {
        viewModel.setCoordXFirstPlanePoint("0.0");
        viewModel.setCoordYFirstPlanePoint("0.0");
        viewModel.setCoordZFirstPlanePoint("0.0");
        viewModel.setCoordXSecondPlanePoint("0.0");
        viewModel.setCoordYSecondPlanePoint("0.0");
        viewModel.setCoordZSecondPlanePoint("0.0");
        viewModel.setCoordXThirdPlanePoint("0.0");
        viewModel.setCoordYThirdPlanePoint("0.0");
        viewModel.setCoordZThirdPlanePoint("0.0");
        assertEquals("0.0", viewModel.getCoordXFirstPlanePoint());
        assertEquals("0.0", viewModel.getCoordYFirstPlanePoint());
        assertEquals("0.0", viewModel.getCoordZFirstPlanePoint());
        assertEquals("0.0", viewModel.getCoordXSecondPlanePoint());
        assertEquals("0.0", viewModel.getCoordYSecondPlanePoint());
        assertEquals("0.0", viewModel.getCoordZSecondPlanePoint());
        assertEquals("0.0", viewModel.getCoordXThirdPlanePoint());
        assertEquals("0.0", viewModel.getCoordYThirdPlanePoint());
        assertEquals("0.0", viewModel.getCoordZThirdPlanePoint());
    }

    @Test
    public void canSetLineCoordinates() {
        viewModel.setCoordXFirstLinePoint("0.0");
        viewModel.setCoordYFirstLinePoint("0.0");
        viewModel.setCoordZFirstLinePoint("0.0");
        viewModel.setCoordXSecondLinePoint("0.0");
        viewModel.setCoordYSecondLinePoint("0.0");
        viewModel.setCoordZSecondLinePoint("0.0");

        assertEquals("0.0", viewModel.getCoordXFirstLinePoint());
        assertEquals("0.0", viewModel.getCoordYFirstLinePoint());
        assertEquals("0.0", viewModel.getCoordZFirstLinePoint());
        assertEquals("0.0", viewModel.getCoordXSecondLinePoint());
        assertEquals("0.0", viewModel.getCoordYSecondLinePoint());
        assertEquals("0.0", viewModel.getCoordZSecondLinePoint());
    }

    @Test
    public void canCreatePlane() {
        createValidPlane();

        assertEquals("Correct input", viewModel.getPlaneStatus());
    }

    @Test
    public void canCreateLine() {
        createValidLine();

        assertEquals("Correct input", viewModel.getLineStatus());
    }

    @Test
    public void canNotAddNullValueForPlaneCoordinates() {
        viewModel.setCoordXFirstPlanePoint("");
        viewModel.setCoordYFirstPlanePoint("2.0");
        viewModel.setCoordZFirstPlanePoint("1.0");
        viewModel.setCoordXSecondPlanePoint("2.0");
        viewModel.setCoordYSecondPlanePoint("-1.0");
        viewModel.setCoordZSecondPlanePoint("2.0");
        viewModel.setCoordXThirdPlanePoint("-1.0");
        viewModel.setCoordYThirdPlanePoint("0.0");
        viewModel.setCoordZThirdPlanePoint("1.0");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals("Input error: empty string", viewModel.getPlaneStatus());
    }

    @Test
    public void canNotAddNullValueForLineCoordinates() {
        viewModel.setCoordXFirstPlanePoint("3.0");
        viewModel.setCoordYFirstPlanePoint("-3.0");
        viewModel.setCoordZFirstPlanePoint("7.0");
        viewModel.setCoordXSecondPlanePoint("8.0");
        viewModel.setCoordYSecondPlanePoint("5.0");
        viewModel.setCoordZSecondPlanePoint("2.0");
        viewModel.setCoordXThirdPlanePoint("17.0");
        viewModel.setCoordYThirdPlanePoint("6.0");
        viewModel.setCoordZThirdPlanePoint("-4.0");
        viewModel.setCoordXFirstLinePoint("");
        viewModel.setCoordYFirstLinePoint("2.0");
        viewModel.setCoordZFirstLinePoint("3.0");
        viewModel.setCoordXSecondLinePoint("2.0");
        viewModel.setCoordYSecondLinePoint("3.0");
        viewModel.setCoordZSecondLinePoint("4.0");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals("Input error: empty string", viewModel.getLineStatus());
    }

    @Test
    public void canNotAddInvalidValueForPlaneCoordinates() {
        viewModel.setCoordXFirstPlanePoint("aa");
        viewModel.setCoordYFirstPlanePoint("2.0");
        viewModel.setCoordZFirstPlanePoint("1.0");
        viewModel.setCoordXSecondPlanePoint("2.0");
        viewModel.setCoordYSecondPlanePoint("1.0");
        viewModel.setCoordZSecondPlanePoint("2.0");
        viewModel.setCoordXThirdPlanePoint("1.0");
        viewModel.setCoordYThirdPlanePoint("0.0");
        viewModel.setCoordZThirdPlanePoint("1.0");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals("Input error: for input string: \"aa\"", viewModel.getPlaneStatus());
    }

    @Test
    public void canNotAddInvalidValueForLineCoordinates() {
        viewModel.setCoordXFirstPlanePoint("-1.0");
        viewModel.setCoordYFirstPlanePoint("5.0");
        viewModel.setCoordZFirstPlanePoint("3.0");
        viewModel.setCoordXSecondPlanePoint("2.0");
        viewModel.setCoordYSecondPlanePoint("7.0");
        viewModel.setCoordZSecondPlanePoint("8.0");
        viewModel.setCoordXThirdPlanePoint("0.0");
        viewModel.setCoordYThirdPlanePoint("-1.0");
        viewModel.setCoordZThirdPlanePoint("1.0");
        viewModel.setCoordXFirstLinePoint("bb");
        viewModel.setCoordYFirstLinePoint("2.0");
        viewModel.setCoordZFirstLinePoint("3.0");
        viewModel.setCoordXSecondLinePoint("2.0");
        viewModel.setCoordYSecondLinePoint("3.0");
        viewModel.setCoordZSecondLinePoint("dd");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals("Input error: for input string: \"bb\"", viewModel.getLineStatus());
    }

    @Test
    public void canNotAddIdenticalPointCoordinatesForPlane() {
        String expectedMessage = "Input error: points must not have identical coordinates";

        viewModel.setCoordXFirstPlanePoint("1.0");
        viewModel.setCoordYFirstPlanePoint("2.0");
        viewModel.setCoordZFirstPlanePoint("3.0");
        viewModel.setCoordXSecondPlanePoint("1.0");
        viewModel.setCoordYSecondPlanePoint("2.0");
        viewModel.setCoordZSecondPlanePoint("3.0");
        viewModel.setCoordXThirdPlanePoint("1.0");
        viewModel.setCoordYThirdPlanePoint("2.0");
        viewModel.setCoordZThirdPlanePoint("3.0");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals(expectedMessage, viewModel.getPlaneStatus());
    }

    @Test
    public void canNotAddIdenticalPointCoordinatesForLine() {
        String expectedMessage = "Input error: points must not have identical coordinates";
        viewModel.setCoordXFirstPlanePoint("4.0");
        viewModel.setCoordYFirstPlanePoint("3.0");
        viewModel.setCoordZFirstPlanePoint("6.0");
        viewModel.setCoordXSecondPlanePoint("-3.0");
        viewModel.setCoordYSecondPlanePoint("-1.0");
        viewModel.setCoordZSecondPlanePoint("5.0");
        viewModel.setCoordXThirdPlanePoint("1.0");
        viewModel.setCoordYThirdPlanePoint("1.0");
        viewModel.setCoordZThirdPlanePoint("2.0");
        viewModel.setCoordXFirstLinePoint("1.0");
        viewModel.setCoordYFirstLinePoint("2.0");
        viewModel.setCoordZFirstLinePoint("3.0");
        viewModel.setCoordXSecondLinePoint("1.0");
        viewModel.setCoordYSecondLinePoint("2.0");
        viewModel.setCoordZSecondLinePoint("3.0");
        viewModel.checkLineAndPlaneIntersection();
        assertEquals(expectedMessage, viewModel.getLineStatus());
    }

    @Test
    public void canPrintRightResultForNonIntersection() {
        makeNonIntersection();

        assertEquals("Do not intersect", viewModel.getResult());
    }

    @Test
    public void canPrintRightResultForIntersection() {
        makeIntersection();

        assertEquals("Intersect: (3.00, 4.00, 2.00)", viewModel.getResult());
    }

    @Test
    public void canPrintRightResultForInvalidIntersection() {
        viewModel.setCoordXFirstPlanePoint("");
        viewModel.setCoordYFirstPlanePoint("8.0");
        viewModel.setCoordZFirstPlanePoint("4.0");
        viewModel.setCoordXSecondPlanePoint("6.0");
        viewModel.setCoordYSecondPlanePoint("-2.0");
        viewModel.setCoordZSecondPlanePoint("5.0");
        viewModel.setCoordXThirdPlanePoint("4.0");
        viewModel.setCoordYThirdPlanePoint("8.0");
        viewModel.setCoordZThirdPlanePoint("0.0");
        viewModel.setCoordXFirstLinePoint("4.0");
        viewModel.setCoordYFirstLinePoint("-55.0");
        viewModel.setCoordZFirstLinePoint("1.0");
        viewModel.setCoordXSecondLinePoint("a");
        viewModel.setCoordYSecondLinePoint("-2.0");
        viewModel.setCoordZSecondLinePoint("-15.0");
        viewModel.checkLineAndPlaneIntersection();

        assertEquals("Input error", viewModel.getResult());
    }

    @Test
    public void logIsEmptyOnStart() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsDontIntersectMessage() {
        makeNonIntersection();

        int lastIndex = viewModel.getLog().size() - 1;
        String message = viewModel.getLog().get(lastIndex);

        assertTrue(message.matches(".*" + LogMessages.DO_NOT_INTERSECT + ".*"));
    }

    @Test
    public void logContainsIntersectMessage() {
        makeIntersection();

        int lastIndex = viewModel.getLog().size() - 1;
        String message = viewModel.getLog().get(lastIndex);

        assertTrue(message.matches(".*" + LogMessages.INTERSECT + ".*"));
    }

    @Test
    public void checkPlaneOkStatus() {
        createValidPlane();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.PLANE_PREFIX + LogMessages.OK + ".*"));
    }

    @Test
    public void checkPlaneNotOkStatus() {
        viewModel.setCoordXFirstPlanePoint("SomeWrongInput");
        viewModel.createPlane();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.PLANE_PREFIX + LogMessages.ERROR + ".*"));
    }

    @Test
    public void checkLineOkStatus() {
        createValidLine();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.LINE_PREFIX + LogMessages.OK + ".*"));
    }

    @Test
    public void checkLineNotOkStatus() {
        viewModel.setCoordXFirstLinePoint("SomeWrongInput");
        viewModel.createLine();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.LINE_PREFIX + LogMessages.ERROR + ".*"));
    }

    @Test
    public void canCreateEmptyViewModel() {
        viewModel = new ViewModel();

        assertTrue(isCoordinatesEmpty());

        assertEquals(LogMessages.WAITING, viewModel.getLineStatus());
        assertEquals(LogMessages.WAITING, viewModel.getPlaneStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCreateWithBrokenLogger() {
        viewModel = new ViewModel(null);
    }

    private boolean isCoordinatesEmpty() {
        assertEquals("", viewModel.getCoordXFirstPlanePoint());
        assertEquals("", viewModel.getCoordYFirstPlanePoint());
        assertEquals("", viewModel.getCoordZFirstPlanePoint());
        assertEquals("", viewModel.getCoordXSecondPlanePoint());
        assertEquals("", viewModel.getCoordYSecondPlanePoint());
        assertEquals("", viewModel.getCoordZSecondPlanePoint());
        assertEquals("", viewModel.getCoordXThirdPlanePoint());
        assertEquals("", viewModel.getCoordYThirdPlanePoint());
        assertEquals("", viewModel.getCoordZThirdPlanePoint());
        assertEquals("", viewModel.getCoordXFirstLinePoint());
        assertEquals("", viewModel.getCoordYFirstLinePoint());
        assertEquals("", viewModel.getCoordZFirstLinePoint());
        assertEquals("", viewModel.getCoordXSecondLinePoint());
        assertEquals("", viewModel.getCoordYSecondLinePoint());
        assertEquals("", viewModel.getCoordZSecondLinePoint());
        assertEquals("", viewModel.getResult());

        return true;
    }

    private void makeIntersection() {
        viewModel.setCoordXFirstPlanePoint("0.0");
        viewModel.setCoordYFirstPlanePoint("1.0");
        viewModel.setCoordZFirstPlanePoint("5.0");
        viewModel.setCoordXSecondPlanePoint("2.0");
        viewModel.setCoordYSecondPlanePoint("0.0");
        viewModel.setCoordZSecondPlanePoint("0.0");
        viewModel.setCoordXThirdPlanePoint("1.0");
        viewModel.setCoordYThirdPlanePoint("1.0");
        viewModel.setCoordZThirdPlanePoint("3.0");
        viewModel.setCoordXFirstLinePoint("13.0");
        viewModel.setCoordYFirstLinePoint("9.0");
        viewModel.setCoordZFirstLinePoint("17.0");
        viewModel.setCoordXSecondLinePoint("0.0");
        viewModel.setCoordYSecondLinePoint("2.5");
        viewModel.setCoordZSecondLinePoint("-2.5");
        viewModel.checkLineAndPlaneIntersection();
    }

    private void makeNonIntersection() {
        viewModel.setCoordXFirstPlanePoint("1.0");
        viewModel.setCoordYFirstPlanePoint("94.0");
        viewModel.setCoordZFirstPlanePoint("0.0");
        viewModel.setCoordXSecondPlanePoint("56.0");
        viewModel.setCoordYSecondPlanePoint("2.0");
        viewModel.setCoordZSecondPlanePoint("0.0");
        viewModel.setCoordXThirdPlanePoint("10.0");
        viewModel.setCoordYThirdPlanePoint("1.0");
        viewModel.setCoordZThirdPlanePoint("0.0");
        viewModel.setCoordXFirstLinePoint("1.0");
        viewModel.setCoordYFirstLinePoint("-87.0");
        viewModel.setCoordZFirstLinePoint("15.0");
        viewModel.setCoordXSecondLinePoint("2.0");
        viewModel.setCoordYSecondLinePoint("2.0");
        viewModel.setCoordZSecondLinePoint("15.0");
        viewModel.checkLineAndPlaneIntersection();
    }

    private void createValidPlane() {
        viewModel.setCoordXFirstPlanePoint("1.0");
        viewModel.setCoordYFirstPlanePoint("2.0");
        viewModel.setCoordZFirstPlanePoint("1.0");
        viewModel.setCoordXSecondPlanePoint("2.0");
        viewModel.setCoordYSecondPlanePoint("1.0");
        viewModel.setCoordZSecondPlanePoint("2.0");
        viewModel.setCoordXThirdPlanePoint("3.0");
        viewModel.setCoordYThirdPlanePoint("0.0");
        viewModel.setCoordZThirdPlanePoint("1.0");
        viewModel.createPlane();
    }

    private void createValidLine() {
        viewModel.setCoordXFirstLinePoint("1.0");
        viewModel.setCoordYFirstLinePoint("-2.0");
        viewModel.setCoordZFirstLinePoint("3.0");
        viewModel.setCoordXSecondLinePoint("2.0");
        viewModel.setCoordYSecondLinePoint("-3.0");
        viewModel.setCoordZSecondLinePoint("4.0");
        viewModel.createLine();
    }

    final class LogMessages {
        public static final String DO_NOT_INTERSECT = "Do not intersect";
        public static final String INTERSECT = "Intersect";

        public static final String LINE_PREFIX = "Line ";
        public static final String PLANE_PREFIX = "Plane ";

        public static final String OK = "Correct input";
        private static final String ERROR = "Input error";

        private static final String WAITING = "Waiting for input";

        private LogMessages() { }
    }
}
