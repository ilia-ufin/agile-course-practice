package ru.unn.agile.LcdDigits.viewmodel;

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
            FakeLogger logger = new FakeLogger();
            viewModel = new ViewModel(logger);
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getDigits());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenFieldIsEmpty() {
        viewModel.transformLcdDigits();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenFieldIsFilled() {
        setInputDigits();

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canRecognizeBadFormat() {
        viewModel.setDigits("qwerty");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void transferringButtonIsDisabledInitially() {
        assertTrue(viewModel.isTransferringDisabled());
    }

    @Test
    public void transferringButtonIsDisabledWhenFieldIsEmpty() {
        viewModel.setDigits("");

        assertTrue(viewModel.isTransferringDisabled());
    }

    @Test
    public void transferringButtonIsDisabledWhenFormatIsBad() {
        viewModel.setDigits("trash");

        assertTrue(viewModel.isTransferringDisabled());
    }

    @Test
    public void transferringButtonIsEnabledWithCorrectInput() {
        setInputDigits();

        assertFalse(viewModel.isTransferringDisabled());
    }

    @Test
    public void transferringButtonIsEnabledWhenTransferredCorrectly() {
        setInputDigits();

        viewModel.transformLcdDigits();

        assertFalse(viewModel.isTransferringDisabled());
    }

    @Test
    public void statusSuccessWhenTransferredCorrectly() {
        setInputDigits();

        viewModel.transformLcdDigits();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void transferringHasCorrectResultOneDigit() {
        viewModel.setDigits("3");

        viewModel.transformLcdDigits();

        assertEquals("._. \n._| \n._| \n", viewModel.getResult());
    }

    @Test
    public void transferringHasCorrectResultLong() {
        viewModel.setDigits("1234567890");

        viewModel.transformLcdDigits();

        assertEquals("... ._. ._. ... ._. ._. ._. ._. ._. ._. \n"
                + "..| ._| ._| |_| |_. |_. ..| |_| |_| |.| \n"
                + "..| |_. ._| ..| ._| |_| ..| |_| ..| |_| \n", viewModel.getResult());
    }

    private void setInputDigits() {
        viewModel.setDigits("1234");
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger fakeLogger = new FakeLogger();
        ViewModel myViewModel = new ViewModel(fakeLogger);

        assertNotNull(myViewModel);
    }

    @Test
    public void viewModelThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Logger parameter cannot be null", e.getMessage());
        } catch (Exception e) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyAtTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isTransformPuttingSomething() {
        viewModel.transformLcdDigits();

        List<String> log = viewModel.getLog();

        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.transformLcdDigits();
        String logMessage = viewModel.getLog().get(0);

        assertTrue(logMessage.matches(".*" + LogMessages.TRANSFORM_WAS_PRESSED + ".*"));
    }

    @Test
    public void canLogContainInputParameters() {
        viewModel.setDigits("1234567890");
        viewModel.transformLcdDigits();

        String logMessage = viewModel.getLog().get(0);

        assertTrue(logMessage.matches(".*" + viewModel.getDigits() + ".*"));
    }

    @Test
    public void isProperlyFormattingInfoAboutArguments() {
        viewModel.setDigits("1234567890");
        viewModel.transformLcdDigits();

        String logMessage = viewModel.getLog().get(0);
        assertTrue(logMessage.matches(".*" + viewModel.getLogPatternCalculate()));
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setDigits("1234567890");

        viewModel.focusLost();

        String logMessage = viewModel.getLog().get(0);
        assertTrue(logMessage.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void canParametersLogOnEditingProperly() {
        viewModel.setDigits("41");

        viewModel.focusLost();

        String logMessage = viewModel.getLog().get(0);
        assertTrue(logMessage.matches(".*" + "Updated input: Input argument is: \\[41\\]"));
    }

    @Test
    public void canNotLogTheSameArgumentsTwice() {
        viewModel.setDigits("41");
        viewModel.setDigits("41");
        viewModel.focusLost();
        viewModel.focusLost();

        String msg = viewModel.getLog().get(0);

        assertTrue(msg.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void canLogWhenCalculateButtonIsDisabledWhenFormatIsBad() {
        viewModel.setDigits("selfie");
        viewModel.focusLost();

        String logMessage = viewModel.getLog().get(0);

        assertTrue(logMessage.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogEqualParametersTwice() {
        viewModel.setDigits("21");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setDigits("21");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
}
