package ru.unn.agile.MyAbstractSet.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.MyAbstractSet.viewmodel.ViewModel.Operation;

import java.util.List;

public class ViewModelTest {
    private ViewModel viewModel;

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        if (viewModel == null) {
            viewModel = new ViewModel(fakeLogger);
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.firstSetTextAreaProperty().get());
        assertEquals("", viewModel.secondSetTextAreaProperty().get());
        assertEquals("", viewModel.resultTextAreaProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        assertEquals(Operation.UNITE, viewModel.operationProperty().get());
        assertTrue(viewModel.executeButtonDisabledProperty().get());
    }

    @Test
    public void statusIsWaitingWhenFirstFieldIsEmptyButSecondFieldFilled() {
        viewModel.firstSetTextAreaProperty().setValue("");
        viewModel.secondSetTextAreaProperty().setValue("3,4,15");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenBothFieldsFilled() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenOneFieldIsWhiteSpace() {
        viewModel.firstSetTextAreaProperty().setValue("   ");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInvalidInput() {
        viewModel.firstSetTextAreaProperty().setValue("rwtewt-$");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsSuccessWhenUniteOperationExecute() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        viewModel.execute();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetIntersectOperation() {
        viewModel.operationProperty().set(Operation.INTERSECT);

        assertEquals(Operation.INTERSECT, viewModel.operationProperty().get());
    }

    @Test
    public void canSetUniteOperation() {
        viewModel.operationProperty().set(Operation.UNITE);

        assertEquals(Operation.UNITE, viewModel.operationProperty().get());
    }

    @Test
    public void executeButtonIsDisabledInitially() {
        assertTrue(viewModel.executeButtonDisabledProperty().get());
    }

    @Test
    public void executeButtonIsEnabledWhenCorrectInput() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        assertFalse(viewModel.executeButtonDisabledProperty().get());
    }

    @Test
    public void executeButtonIsDisabledWhenBadFormatInput() {
        viewModel.firstSetTextAreaProperty().setValue("fdsgd+");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        assertTrue(viewModel.executeButtonDisabledProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelThrowExceptionWhenLoggerIsNull() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterOperationUnite() {
        viewModel.firstSetTextAreaProperty().setValue("5,6,3");
        viewModel.secondSetTextAreaProperty().setValue("5,23,6");

        viewModel.execute();
        int sizeLog = viewModel.getLog().size();
        String message1 = viewModel.getLog().get(sizeLog - 1);
        String message2 = String.format(LogMessages.EXECUTE_PRESSED, "5,6,3", "5,23,6",
                Operation.UNITE);
        assertTrue(message1.contains(message2));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        viewModel.execute();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.firstSetTextAreaProperty().get()
                + ".*" + viewModel.secondSetTextAreaProperty().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLogAfterPressedExecute() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        viewModel.execute();
        int sizeLog = viewModel.getLog().size();
        String message = viewModel.getLog().get(sizeLog - 1);
        assertTrue(message.matches(".*" + Operation.UNITE.toString() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessage() {
        viewModel.firstSetTextAreaProperty().setValue("4,10,6");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4,9,3");

        viewModel.execute();
        viewModel.execute();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationInLogWhenChange() {
        viewModel.operationProperty().set(Operation.UNITE);
        viewModel.operationProperty().set(Operation.INTERSECT);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_CHANGED
                + Operation.INTERSECT.toString() + ".*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.operationProperty().set(Operation.INTERSECT);
        viewModel.operationProperty().set(Operation.INTERSECT);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void logContainsProperMessageAfterFillSets() {
        viewModel.firstSetTextAreaProperty().setValue("4,10");
        viewModel.secondSetTextAreaProperty().setValue("3,7,4");

        String message1 = viewModel.getLog().get(0);
        String message2 = String.format(LogMessages.EDITING_FINISHED, "4,10", "3,7,4");
        assertTrue(message1.contains(message2));
    }

    @Test
    public void executeIsNotCalledWhenButtonIsDisabled() {
        viewModel.execute();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void logViewContainsMessagesAfterExecute() {
        viewModel.firstSetTextAreaProperty().setValue("45,6,8");
        viewModel.secondSetTextAreaProperty().setValue("3,7,97");

        viewModel.execute();

        assertFalse(viewModel.logProperty().isEmpty());
    }
}
