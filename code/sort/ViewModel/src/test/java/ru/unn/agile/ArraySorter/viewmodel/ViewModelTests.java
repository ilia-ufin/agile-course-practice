package ru.unn.agile.ArraySorter.viewmodel;

import ru.unn.agile.ArraySorter.viewmodel.ViewModel.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals("", viewModel.getElemArray());
        assertEquals("", viewModel.getSortedArrayStringRepresentation());
        assertEquals("", viewModel.getInputArrayStringRepresentation());
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void canAddOneElementToArray() {
        viewModel.setInputValue("3.0");
        viewModel.addProcess();

        assertEquals("[3.0]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canAddSeveralElementsToArray() {
        viewModel.setInputValue("-5.0");
        viewModel.addProcess();
        viewModel.setInputValue("4.0");
        viewModel.addProcess();
        viewModel.setInputValue("2.75");
        viewModel.addProcess();

        assertEquals("[-5.0, 4.0, 2.75]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canClearArray() {
        viewModel.setInputValue("6");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("10");
        viewModel.addProcess();
        viewModel.setInputValue("8");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals("[]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  canSortOfArrayWithOneElement() {
        viewModel.setInputValue("-4.0");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4.0]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  canSortOfNonSortedBigArray() {
        viewModel.setInputValue("-4.0");
        viewModel.addProcess();
        viewModel.setInputValue("-0.4");
        viewModel.addProcess();
        viewModel.setInputValue("3.1");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4.0, -0.4, 3.1]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  isWaitingStatusWhenLaunch() {
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStatWhenBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStateWhenAddAndDelElemEmptyField() {
        viewModel.setInputValue("");

        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isReadyStateWhenAddElemFieldIsWriteIn() {
        viewModel.setInputValue("6.1");

        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setInputValue("b");

        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.setInputValue("-1.0");
        viewModel.addProcess();
        viewModel.setInputValue("2.0");
        viewModel.addProcess();
        viewModel.setInputValue("5.4");

        viewModel.sort();

        assertEquals(Status.SUCCESSFUL, viewModel.getCurrentState());
    }

    @Test
    public void isAddButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isAddButtonEnabledAddElemFieldIsCorrect() {
        viewModel.setInputValue("1.2");
        viewModel.processingAddField(1);

        assertEquals(true, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddElemFieldIsEmpty() {
        viewModel.setInputValue("");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddElemIsInvalid() {
        viewModel.setInputValue("ijijfdf");
        viewModel.processingAddField(1);

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonEnabledWhenArrayIsNotEmpty() {
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("9");
        viewModel.addProcess();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWhenArrayIsNotEmpty() {
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();

        assertEquals(true, viewModel.isSortButtonEnabled());
    }
    @Test
    public void isClearButtonDisabledWhenClearArray() {
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenClearArray() {
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("5");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isClearButtonAddOneElementEnabled() {
        viewModel.setInputValue("2");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsCorrect() {
        viewModel.setInputValue("test");
        viewModel.processingAddField(1);
        viewModel.setInputValue("12.1");
        viewModel.processingAddField(1);

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsInvalid() {
        viewModel.setInputValue("12.1");
        viewModel.processingAddField(1);
        viewModel.setInputValue("test");
        viewModel.processingAddField(1);

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsEmpty() {
        viewModel.setInputValue("12.1");
        viewModel.processingAddField(1);
        viewModel.setInputValue("");
        viewModel.processingAddField(1);

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }
}
