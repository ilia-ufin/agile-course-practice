package ru.unn.agile.ConwayGame.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void viewModelThrowsNullLoggerException() {
        try {
            new ViewModel(null);
            fail("Exception was not thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can not be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel newViewModel = new ViewModel(logger);

        assertNotNull(newViewModel);
    }

    @Test
    public void isLogEmptyAtFirst() {
        List<String> logList = viewModel.getLogList();

        assertEquals(0, logList.size());
    }

    @Test
    public void isCalculatePuttingSomeData() {
        viewModel.calculateNextGeneration();

        List<String> logList = viewModel.getLogList();
        assertNotEquals(0, logList.size());
    }

    @Test
    public void isLogsContainProperMessage() {
        viewModel.calculateNextGeneration();
        List<String> logList = viewModel.getLogList();

        assertTrue(logList.get(0).matches(".*"
                + ViewModel.LogMessages.SUBMIT_WAS_PRESSED + ".*"));
    }

    @Test
    public void isSubmitLogIsCorrect() {
        viewModel.calculateNextGeneration();
        List<String> logList = viewModel.getLogList();

        String message = viewModel.getLogPatternSubmit();

        assertTrue(logList.get(0).contains(message));
    }

    @Test
    public void isEditingRowsNumberFinishLogged() {
        viewModel.setRowsNumber("1");
        viewModel.focusLost();

        List<String> logList = viewModel.getLogList();
        assertTrue(logList.get(0).matches(".*"
                + ViewModel.LogMessages.PARAMETERS_EDITING_FINISHED + ".*"));
    }

    @Test
    public void isEditingColumnsNumberFinishLogged() {
        viewModel.setRowsNumber("3");
        viewModel.focusLost();

        List<String> logList = viewModel.getLogList();
        assertTrue(logList.get(0).matches(".*"
                + ViewModel.LogMessages.PARAMETERS_EDITING_FINISHED + ".*"));
    }

    @Test
    public void isEditingFirstGenerationFinishLogged() {
        viewModel.setFirstGeneration("..**");
        viewModel.focusLost();

        List<String> logList = viewModel.getLogList();
        assertTrue(logList.get(0).matches(".*"
                + ViewModel.LogMessages.PARAMETERS_EDITING_FINISHED + ".*"));
    }

    @Test
    public void notLogEqualArgumentsTwice() {
        viewModel.setRowsNumber("6");
        viewModel.setRowsNumber("6");
        viewModel.focusLost();
        viewModel.focusLost();

        List<String> logList = viewModel.getLogList();

        assertTrue(logList.get(0).matches(".*"
                + ViewModel.LogMessages.PARAMETERS_EDITING_FINISHED + ".*"));
        assertEquals(1, logList.size());
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.rowsNumberProperty().get());
        assertEquals("", viewModel.columnsNumberProperty().get());
        assertEquals("", viewModel.firstGenerationProperty().get());
        assertEquals("", viewModel.inputProperty().get());
        assertEquals("", viewModel.outputProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusItWaitingWhenBlankFields() {
        viewModel.calculateNextGeneration();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyToSetWhenFieldsAreCorrectlyFilled() {
        setInputSizes();

        assertEquals(Status.READY_TO_SET.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.rowsNumberProperty().set("q");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canWaitIfNoAllFieldsAreFilled() {
        viewModel.rowsNumberProperty().set("5");

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatWhileSmthgIsBad() {
        viewModel.setRowsNumber("5");
        viewModel.setColumnsNumber("q");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void isCreateGridDisabledWhileBadFormat() {
        viewModel.setRowsNumber("5");
        viewModel.setColumnsNumber("q");

        assertTrue(viewModel.isCreationGridDisabled());
    }

    @Test
    public void isCreateGridDisableWhenBlankFields() {
        viewModel.setRowsNumber("7");

        assertTrue(viewModel.isCreationGridDisabled());
    }

    @Test
    public void isCreateGridDisableWhenBadStatus() {
        viewModel.statusProperty().set(Status.BAD_FORMAT.toString());

        assertTrue(viewModel.isCreationGridDisabled());
    }

    @Test
    public void isCreateGridAbleWithGoodSizes() {
        setInputSizes();

        assertFalse(viewModel.isCreationGridDisabled());
    }

    @Test
    public void isSubmitAble() {
        setInputSizesAndData();

        assertFalse(viewModel.isSubmitionDisabled());
    }

    @Test
    public void isSubmitDisableWhileGenerationIsEmpty() {
        setInputSizes();
        viewModel.setFirstGeneration("");

        assertTrue(viewModel.isSubmitionDisabled());
    }

    @Test
    public void isFirstGenerationLessThenSizes() {
        setInputSizes();
        viewModel.setFirstGeneration("..*");

        assertEquals(Status.READY_TO_SET.toString(), viewModel.getStatus());
    }

    @Test
    public void isFirstGenerationMoreThenSizes() {
        setInputSizes();
        viewModel.setFirstGeneration("..**.");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void isFirstGenerationBadFormat() {
        setInputSizes();
        viewModel.setFirstGeneration("..*k");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void isFirstGenerationGood() {
        setInputSizesAndData();

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void isSubmitAbleWithGoodData() {
        setInputSizesAndData();

        assertFalse(viewModel.isSubmitionDisabled());
    }

    @Test
    public void isCorrectInput() {
        viewModel.setRowsNumber("2");
        viewModel.setColumnsNumber("3");
        viewModel.setFirstGeneration("*.**.*");

        viewModel.calculateNextGeneration();

        assertEquals("*.*\n*.*\n", viewModel.getInput());
    }

    @Test
    public void isCorrectShortOutput() {
        viewModel.setRowsNumber("1");
        viewModel.setColumnsNumber("1");
        viewModel.setFirstGeneration("*");

        viewModel.calculateNextGeneration();

        assertEquals(".\n", viewModel.getOutput());
    }

    @Test
    public void isCorrectOutput() {
        viewModel.setRowsNumber("4");
        viewModel.setColumnsNumber("8");
        viewModel.setFirstGeneration("............*......**...........");

        viewModel.calculateNextGeneration();

        assertEquals("........\n...**...\n...**...\n........\n", viewModel.getOutput());
    }

    private void setInputSizes() {
        viewModel.setRowsNumber("2");
        viewModel.setColumnsNumber("2");
    }

    private void setInputSizesAndData() {
        viewModel.setRowsNumber("2");
        viewModel.setColumnsNumber("2");
        viewModel.setFirstGeneration(".**.");
    }

}
