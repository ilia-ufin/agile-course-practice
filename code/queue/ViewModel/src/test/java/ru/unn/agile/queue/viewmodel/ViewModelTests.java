package ru.unn.agile.queue.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import ru.unn.agile.queue.viewmodel.ViewModel.*;

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
        assertEquals("", viewModel.getInputElem());
        assertEquals("", viewModel.getQueueStringRepresentation());
        assertEquals(State.WAITING_FOR_INPUT, viewModel.getCurrentState());
    }

    @Test
    public void canAddOneElemToQueue() {
        viewModel.setInputElem("5.43");
        viewModel.addProcess();

        assertEquals("[5.43]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canAddMoreElemsToQueue() {
        viewModel.setInputElem("1.6");
        viewModel.addProcess();
        viewModel.setInputElem("-6.3");
        viewModel.addProcess();
        viewModel.setInputElem("0.0");
        viewModel.addProcess();
        viewModel.setInputElem("-4.9");
        viewModel.addProcess();
        viewModel.setInputElem("8");
        viewModel.addProcess();
        viewModel.setInputElem("92");
        viewModel.addProcess();

        assertEquals("[1.6, -6.3, 0.0, -4.9, 8.0, 92.0]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canRemoveHeadFromQueue() {
        viewModel.setInputElem("53");
        viewModel.addProcess();
        viewModel.setInputElem("63.2");
        viewModel.addProcess();
        viewModel.setInputElem("-4.2");
        viewModel.addProcess();

        viewModel.removeProcess();

        assertEquals("[63.2, -4.2]", viewModel.getQueueStringRepresentation());
    }

    @Test
    public void canClearQueue() {
        viewModel.setInputElem("33");
        viewModel.addProcess();
        viewModel.setInputElem("11.3");
        viewModel.addProcess();
        viewModel.setInputElem("2.5");
        viewModel.addProcess();
        viewModel.setInputElem("6.2");
        viewModel.addProcess();

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
        viewModel.addProcess();
        viewModel.setInputElem("8");
        viewModel.addProcess();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isRemoveButtonEnabledWhenQueueIsNotEmpty() {
        viewModel.setInputElem("4");
        viewModel.addProcess();
        viewModel.setInputElem("1");
        viewModel.addProcess();

        assertEquals(true, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void canClearDisableClearButton() {
        viewModel.setInputElem("6");
        viewModel.addProcess();
        viewModel.setInputElem("2");
        viewModel.addProcess();
        viewModel.setInputElem("8");
        viewModel.addProcess();
        viewModel.setInputElem("11");
        viewModel.addProcess();

        viewModel.clear();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canClearDisableRemoveButton() {
        viewModel.setInputElem("23");
        viewModel.addProcess();
        viewModel.setInputElem("1");
        viewModel.addProcess();
        viewModel.setInputElem("5");
        viewModel.addProcess();
        viewModel.setInputElem("3");
        viewModel.addProcess();

        viewModel.clear();

        assertEquals(false, viewModel.isRemoveButtonEnabled());
    }

    @Test
    public void canRemoveDisableClearButton() {
        viewModel.setInputElem("20");
        viewModel.addProcess();

        viewModel.removeProcess();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canRemoveDisableRemoveButton() {
        viewModel.setInputElem("13");
        viewModel.addProcess();

        viewModel.removeProcess();

        assertEquals(false, viewModel.isRemoveButtonEnabled());
    }

}
