package ru.unn.agile.caesarcipher.viewmodel;

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
    public void tear() {
        viewModel = null;
    }

    @Test
    public void isLoggerEmptyInBegin() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isLogFilledAfterInput() {
        viewModel.setTextBoxInput("ABC");
        viewModel.logInputParams();

        viewModel.setTextBoxOffset("1");
        viewModel.logInputParams();

        List<String> log = viewModel.getLog();

        assertEquals(2, log.size());
    }

    @Test
    public void canShowLogMessageInput() {
        viewModel.setTextBoxInput("ABC");

        viewModel.logInputParams();
        String logMessage = viewModel.getLog().toString();

        assertTrue(logMessage.contains(String.format(LogMessage.INPUT.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue())));
    }

    @Test
    public void canShowLogMessageInputOffset() {
        viewModel.setTextBoxOffset("1");

        viewModel.logInputParams();
        String logMessage = viewModel.getLog().toString();

        assertFalse(!logMessage.contains(String.format(LogMessage.INPUT.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue())));
    }

    @Test
    public void canShowLogMessageEncode() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("1");

        viewModel.codeCaesar();
        String logMessage = viewModel.getLog().toString();

        assertTrue(logMessage.contains(String.format(LogMessage.FINISHED.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue(),
                viewModel.getCaesarCipher())));
    }

    @Test
    public void canShowCorrectLogMessageEncode() {
        viewModel.setTextBoxOffset("1");
        viewModel.logInputParams();

        viewModel.codeCaesar();

        assertEquals(String.format(LogMessage.FINISHED.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue(),
                viewModel.getCaesarCipher()), viewModel.editingFinishMessage());
    }

    @Test
    public void canShowCorrectLogMessageInputOffset() {
        viewModel.setTextBoxOffset("1");
        viewModel.logInputParams();

        assertEquals(String.format(LogMessage.INPUT.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue()),
                viewModel.editingInputMessage());
    }

    @Test
    public void canShowCorrectLogMessageInput() {
        viewModel.setTextBoxInput("ABC");
        viewModel.logInputParams();

        assertEquals(String.format(LogMessage.INPUT.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue()),
                viewModel.editingInputMessage());
    }

    @Test
    public void canShowCorrectLogMessagesInput() {
        viewModel.setTextBoxInput("ABC");

        viewModel.logInputParams();
        viewModel.logInputParams();

        assertEquals(String.format(LogMessage.INPUT.getDescription(),
                viewModel.getInputTextBoxValue(), viewModel.getOffsetTextBoxValue()),
                viewModel.editingInputMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenLoggerIsNull() {
        ILogger logger = null;

        viewModel = new ViewModel(logger);
    }

    @Test
    public void canRecognizeThatButtonIsDisabled() {
        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canRecognizeThatButtonIsEnabledWhenEnter() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("2");
        assertTrue(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canDisableButtonIfAllDataHasBeenEntered() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canEnableButtonWhenClearInputTextBox() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("");

        viewModel.setTextBoxInput("");
        viewModel.setTextBoxOffset("");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canEnableButtonWhenClearOffsetTextBox() {
        viewModel.setTextBoxInput("");
        viewModel.setTextBoxOffset("1");

        viewModel.setTextBoxInput("");
        viewModel.setTextBoxOffset("");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canOffsetByGivenNumber() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("1");

        viewModel.codeCaesar();

        assertEquals("BCD", viewModel.getCaesarCipher());
    }

    @Test
    public void canDisplayErrorWithIncompleteData() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("");

        assertEquals("Input correct value", viewModel.getStatus());
    }

    @Test
    public void canDisplayErrorWithIncorrectData() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("abc");

        assertEquals("Input correct value", viewModel.getStatus());
    }

    @Test
    public void canDisplayStatus() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("1");

        assertEquals("Correct input", viewModel.getStatus());
    }

    @Test
    public void canDisplayLimitedStatus() {
        viewModel.setTextBoxInput("");
        viewModel.setTextBoxOffset("1234567890123");

        assertEquals("You can enter up to 12 digits", viewModel.getStatus());
    }

    @Test
    public void canDisplaySuccessfullyStatus() {
        viewModel.setTextBoxInput("ABC");
        viewModel.setTextBoxOffset("1");

        viewModel.codeCaesar();

        assertEquals("Successful", viewModel.getStatus());
    }


    @Test
    public void isStatusWaitingInTheBegin() {
        assertEquals("Waiting", viewModel.getStatus());
    }
}
