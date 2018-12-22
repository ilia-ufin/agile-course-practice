package ru.unn.agile.BookStore.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            FakeLogger fakeLogger = new FakeLogger();
            viewModel = new ViewModel(fakeLogger);
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    public void setViewModel(final ViewModel vM) {
        this.viewModel = vM;
    }

    private void setInputData() {
        viewModel.books1Property().set("1");
        viewModel.books2Property().set("2");
        viewModel.books3Property().set("3");
        viewModel.books4Property().set("4");
        viewModel.books5Property().set("5");
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getBooks1());
        assertEquals("", viewModel.getBooks2());
        assertEquals("", viewModel.getBooks3());
        assertEquals("", viewModel.getBooks4());
        assertEquals("", viewModel.getBooks5());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithNonSetFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.books1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenNotEnoughFieldsAreFill() {
        viewModel.books1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.books1Property().set("trash");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.setBooks1("1");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void calculationHasCorrectResult() {
        viewModel.setBooks1("1");
        viewModel.setBooks2("3");
        viewModel.setBooks3("2");
        viewModel.setBooks4("1");
        viewModel.setBooks5("5");

        viewModel.calculate();

        assertEquals("82.8", viewModel.getResult());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setBooks5("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyAtTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isCalculatePuttingSomething() {
        viewModel.calculate();

        List<String> log = viewModel.getLog();

        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void canLogContainInputParameters() {
        setInputData();
        viewModel.calculate();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + viewModel.getBooks1()
                + ".*" + viewModel.getBooks2()
                + ".*" + viewModel.getBooks3()
                + ".*" + viewModel.getBooks4() + ".*"
        ));
    }

    @Test
    public void isProperlyFormattingInfoAboutArguments() {
        setInputData();
        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getLogPatternCalculate()));
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setBooks1("1");

        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void canParametersLogOnEditingProperly() {
        setInputData();

        viewModel.focusLost();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + "Updated input: Input arguments are: \\[1; 2; 3; 4\\]"));
    }

    @Test
    public void canNotLogTheSameArgumentsTwice() {
        setInputData();
        setInputData();
        viewModel.focusLost();
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void canNotLogTheSameArgumentsTwiceWithPartialInput() {
        viewModel.setBooks1("2");
        viewModel.setBooks1("2");
        viewModel.setBooks1("2");

        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();

        assertEquals(1, viewModel.getLog().size());
    }


    @Test
    public void canLogWhenCalculateButtonIsDisabledWhenFormatIsBad() {
        viewModel.setBooks1("trash");
        viewModel.focusLost();
        viewModel.setBooks2("2");
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void doNotLogEqualParametersTwice() {
        viewModel.setBooks4("2");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setBooks4("2");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
}
