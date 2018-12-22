package ru.unn.agile.huffman.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.huffman.model.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.txtProperty().get());
        assertEquals("", viewModel.txtEncodeProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenEncodingEmptyText() {
        viewModel.encode();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenTextAreaIsFilling() {
        viewModel.txtProperty().set("Text is filling");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void encodingButtonIsDisabledInitially() {
        assertTrue(viewModel.encodingDisabledProperty().get());
    }

    @Test
    public void encodingButtonIsEnabledWhenInputText() {
        viewModel.txtProperty().set("Text is filling");

        assertFalse(viewModel.encodingDisabledProperty().get());
    }

    @Test
    public void encodingButtonIsDisabledAfterClearInputTextArea() {
        viewModel.txtProperty().set("Text");

        viewModel.txtProperty().set("");

        assertTrue(viewModel.encodingDisabledProperty().get());
    }

    @Test
    public void checkingCorrectEncodingResult() {
        viewModel.txtProperty().set("Check result");

        viewModel.encode();

        assertEquals("010101000101111001000110100111111101001011",
                viewModel.txtEncodeProperty().get());
    }

    @Test
    public void encodingButtonIsEnabledWhenEncodingText() {
        viewModel.txtProperty().set("Text");

        viewModel.encode();

        assertFalse(viewModel.isEncodingDisabled());
    }

    @Test
    public void encodingTextAreaIsFillAfterClearInputTextArea() {
        viewModel.txtProperty().set("Text");
        viewModel.encode();

        viewModel.txtProperty().set("");

        assertFalse(viewModel.txtEncodeProperty().get().isEmpty());
    }

    @Test
    public void encodingButtonIsDisabledAfterEncodingWhenClearInputTextArea() {
        viewModel.txtProperty().set("Text");
        viewModel.encode();

        viewModel.txtProperty().set("");

        assertTrue(viewModel.encodingDisabledProperty().get());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        ILogger logger = new DummyLoger();

        viewModel = new ViewModel(logger);

        assertEquals(logger, viewModel.getLogger());
    }

    @Test
    public void canSetLogger() {
        ILogger logger = new DummyLoger();
        viewModel.setLogger(logger);

        assertEquals(logger, viewModel.getLogger());
    }

    @Test
    public void canGetLog() {
        ILogger logger = new DummyLoger();
        viewModel.setLogger(logger);

        viewModel.txtProperty().set("Text");
        viewModel.encode();

        assertEquals(logger.getLog().get(0) + "\n", viewModel.getLog());
        assertEquals(logger.getLog().get(0) + "\n", viewModel.logProperty().get());

    }

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
