package ru.unn.agile.queue.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import ru.unn.agile.queue.viewmodel.ViewModel.*;

import java.util.List;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getInputElem());
        assertEquals("", viewModel.getQueueStringRepresentation());
        assertEquals(State.WAITING_FOR_INPUT, viewModel.getCurrentState());
    }

    @Test
    public void canAddOneElemToQueue() {
        viewModel.setInputElem("5.43");
        viewModel.pushProcess();

        assertEquals("[5.43]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canAddMoreElemsToQueue() {
        viewModel.setInputElem("1.6");
        viewModel.pushProcess();
        viewModel.setInputElem("-6.3");
        viewModel.pushProcess();
        viewModel.setInputElem("0.0");
        viewModel.pushProcess();
        viewModel.setInputElem("-4.9");
        viewModel.pushProcess();
        viewModel.setInputElem("8");
        viewModel.pushProcess();
        viewModel.setInputElem("92");
        viewModel.pushProcess();

        assertEquals("[1.6, -6.3, 0.0, -4.9, 8.0, 92.0]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canRemoveHeadFromQueue() {
        viewModel.setInputElem("53");
        viewModel.pushProcess();
        viewModel.setInputElem("63.2");
        viewModel.pushProcess();
        viewModel.setInputElem("-4.2");
        viewModel.pushProcess();

        viewModel.popProcess();

        assertEquals("[63.2, -4.2]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canClearQueue() {
        viewModel.setInputElem("33");
        viewModel.pushProcess();
        viewModel.setInputElem("11.3");
        viewModel.pushProcess();
        viewModel.setInputElem("2.5");
        viewModel.pushProcess();
        viewModel.setInputElem("6.2");
        viewModel.pushProcess();

        viewModel.clear();

        assertEquals("[]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void isWaitingStateWhenAddElemFieldIsEmpty() {
        viewModel.setInputElem("");

        viewModel.processingAddField();

        assertEquals(State.WAITING_FOR_INPUT, viewModel.getCurrentState());
    }

    @Test
    public void isReadyStateWhenAddElemFieldIsFill() {
        viewModel.setInputElem("6.1");

        viewModel.processingAddField();

        assertEquals(State.READY_TO_ADD, viewModel.getCurrentState());
    }

    @Test
    public void isInvalidStateWhenAddElemFieldhasIncorrectInput() {
        viewModel.setInputElem("Hello");

        viewModel.processingAddField();

        assertEquals(State.INVALID_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void isStateSwitchedToAddReadyIfAddedCorrectInputValue() {
        viewModel.setInputElem("hi");
        viewModel.processingAddField();
        viewModel.setInputElem("33");
        viewModel.processingAddField();

        assertEquals(State.READY_TO_ADD, viewModel.getCurrentState());
    }

    @Test
    public void isStateSwitchedToInvalidIfAddedIncorrectInputValue() {
        viewModel.setInputElem("33");
        viewModel.processingAddField();
        viewModel.setInputElem("hi");
        viewModel.processingAddField();

        assertEquals(State.INVALID_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void isStateSwitchedToWaitingIfAddFieldIsEmpty() {
        viewModel.setInputElem("33");
        viewModel.processingAddField();
        viewModel.setInputElem("");
        viewModel.processingAddField();

        assertEquals(State.WAITING_FOR_INPUT, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStateWhenStartup() {
        assertEquals(State.WAITING_FOR_INPUT, viewModel.getCurrentState());
    }

    @Test
    public void isAddButtonDisabledWhenStartup() {
        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isRemoveButtonDisabledWhenStartup() {
        assertEquals(false, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void isClearButtonDisabledWhenStartup() {
        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isAddButtonEnabledWhenAddFieldIsCorrect() {
        viewModel.setInputElem("37.3");
        viewModel.processingAddField();

        assertEquals(true, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddFieldIsInvalid() {
        viewModel.setInputElem("kdbsj");
        viewModel.processingAddField();

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddFieldIsEmpty() {
        viewModel.setInputElem("");
        viewModel.processingAddField();

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonEnabledWhenQueueIsNotEmpty() {
        viewModel.setInputElem("5");
        viewModel.pushProcess();
        viewModel.setInputElem("8");
        viewModel.pushProcess();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isRemoveButtonEnabledWhenQueueIsNotEmpty() {
        viewModel.setInputElem("4");
        viewModel.pushProcess();
        viewModel.setInputElem("1");
        viewModel.pushProcess();

        assertEquals(true, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void canClearDisableClearButton() {
        viewModel.setInputElem("6");
        viewModel.pushProcess();
        viewModel.setInputElem("2");
        viewModel.pushProcess();
        viewModel.setInputElem("8");
        viewModel.pushProcess();
        viewModel.setInputElem("11");
        viewModel.pushProcess();

        viewModel.clear();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canClearDisableRemoveButton() {
        viewModel.setInputElem("23");
        viewModel.pushProcess();
        viewModel.setInputElem("1");
        viewModel.pushProcess();
        viewModel.setInputElem("5");
        viewModel.pushProcess();
        viewModel.setInputElem("3");
        viewModel.pushProcess();

        viewModel.clear();

        assertEquals(false, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void canRemoveDisableClearButton() {
        viewModel.setInputElem("20");
        viewModel.pushProcess();

        viewModel.popProcess();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canRemoveDisableRemoveButton() {
        viewModel.setInputElem("13");
        viewModel.pushProcess();

        viewModel.popProcess();

        assertEquals(false, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCreateViewModelWithNullLogger() {
        ViewModel viewModel = new ViewModel(null);
    }

    public void isLogEmptyWhenStartup() {
        List<String> log = viewModel.getLog();

        assertEquals("", log);
    }

    @Test
    public void isLogContainsInfoAboutIncorrectInputElement() {
        String inputValue = "abc";
        viewModel.setInputElem(inputValue);
        viewModel.processingAddField();

        List<String> log = viewModel.getLog();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + inputValue + ".*"));
    }

    @Test
    public void isLogContainsInfoAboutTailPushing() {
        double inputValue = 5.5;
        viewModel.setInputElem(Double.toString(inputValue));
        viewModel.pushProcess();

        List<String> log = viewModel.getLog();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getInputElem() + ".*"));
    }

    @Test
    public void isLogContainsInfoAboutHeadPopping() {
        double inputValue = 5.5;
        viewModel.setInputElem(Double.toString(inputValue));
        viewModel.pushProcess();
        viewModel.popProcess();

        List<String> log = viewModel.getLog();

        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + viewModel.getInputElem() + ".*"));
    }

    @Test
    public void isLogContainsInfoAboutClearingInputArray() {
        viewModel.clear();

        List<String> log = viewModel.getLog();

        assertEquals(1, log.size());
    }
}
