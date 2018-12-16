package ru.unn.agile.stack.viewmodel;

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
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("0", viewModel.getStackSize());
        assertEquals(viewModel.NONE, viewModel.getStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getStackPopElement());
        assertEquals("", viewModel.getAddingElement());
    }

    @Test
    public void isPopButtonNotVisibleWhenStart() {
        assertEquals(false, viewModel.isPopButtonVisible());
    }

    @Test
    public void isWaitingStatusSetWhenStart() {
        assertEquals(ViewModel.WAITING_FOR_INPUT, viewModel.getStatusMessage());
    }

    @Test
    public void canAddNewElement() {
        viewModel.setAddingElem("1");
        viewModel.addElement();

        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("1", viewModel.getStackSize());
        assertEquals("1.0", viewModel.getStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getStackPopElement());
        assertEquals("1", viewModel.getAddingElement());
    }

    @Test
    public void isReadyToAddStatusSetWhenAddNewElement() {
        viewModel.setAddingElem("11");
        viewModel.addElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessage());
    }

    @Test
    public void isPopButtonVisibleAfterAddingElement() {
        viewModel.setAddingElem("105");
        viewModel.addElement();

        assertEquals(true, viewModel.isPopButtonVisible());
    }

    @Test
    public void doNotAddNonValidElement() {
        viewModel.setAddingElem("abc");
        viewModel.addElement();

        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("0", viewModel.getStackSize());
        assertEquals(viewModel.NONE, viewModel.getStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getStackPopElement());
        assertEquals("abc", viewModel.getAddingElement());
    }

    @Test
    public void doNotAddEmptyElement() {
        viewModel.setAddingElem("");
        viewModel.addElement();

        assertEquals(viewModel.NONE, viewModel.getStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getStackPopElement());
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("0", viewModel.getStackSize());
        assertEquals("", viewModel.getAddingElement());
    }

    @Test
    public void isInvalidFormatStatusSetWhenAddInvalidElement() {
        viewModel.setAddingElem("abc");
        viewModel.addElement();

        assertEquals(ViewModel.INVALID_FORMAT, viewModel.getStatusMessage());
    }

    @Test
    public void isPopNotVisibleWhenAddInvalidElement() {
        viewModel.setAddingElem("dc");
        viewModel.addElement();

        assertEquals(false, viewModel.isPopButtonVisible());
    }

    @Test
    public void canAddFewElements() {
        viewModel.setAddingElem("1");
        viewModel.addElement();
        viewModel.setAddingElem("2");
        viewModel.addElement();
        viewModel.setAddingElem("3.7");
        viewModel.addElement();
        viewModel.setAddingElem("-4.9");
        viewModel.addElement();
        viewModel.setAddingElem("5.95");
        viewModel.addElement();

        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("5", viewModel.getStackSize());
        assertEquals("5.95", viewModel.getStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getStackPopElement());
        assertEquals("5.95", viewModel.getAddingElement());
    }

    @Test
    public void isReadyToAddStatusSetWhenAddFewElement() {
        viewModel.setAddingElem("-18");
        viewModel.addElement();
        viewModel.setAddingElem("38");
        viewModel.addElement();
        viewModel.setAddingElem("24.5");
        viewModel.addElement();
        viewModel.setAddingElem("-28.59");
        viewModel.addElement();
        viewModel.setAddingElem("21");
        viewModel.addElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessage());
    }

    @Test
    public void isPopButtonVisibleAfterAddingFewElement() {
        viewModel.setAddingElem("-61");
        viewModel.addElement();
        viewModel.setAddingElem("41");
        viewModel.addElement();
        viewModel.setAddingElem("36.6");
        viewModel.addElement();
        viewModel.setAddingElem("-28.3");
        viewModel.addElement();
        viewModel.setAddingElem("28");
        viewModel.addElement();

        assertEquals(true, viewModel.isPopButtonVisible());
    }

    @Test
    public void canAddAndPopElement() {
        viewModel.setAddingElem("1");
        viewModel.addElement();
        viewModel.popElement();

        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("0", viewModel.getStackSize());
        assertEquals(viewModel.NONE, viewModel.getStackTopElement());
        assertEquals("1.0", viewModel.getStackPopElement());
        assertEquals("1", viewModel.getAddingElement());
    }

    @Test
    public void isPopButtonNotVisibleAfterAddAndPopElement() {
        viewModel.setAddingElem("-1");
        viewModel.addElement();
        viewModel.popElement();
        assertEquals(false, viewModel.isPopButtonVisible());
    }

    @Test
    public void isReadyToAddStatusSetAfterAddAndPopElement() {
        viewModel.setAddingElem("11");
        viewModel.addElement();
        viewModel.popElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessage());
    }

    @Test
    public void canAddAndPopElementFewTimes() {
        viewModel.setAddingElem("1");
        viewModel.addElement();
        viewModel.popElement();
        viewModel.setAddingElem("-53.7");
        viewModel.addElement();
        viewModel.setAddingElem("11");
        viewModel.addElement();
        viewModel.setAddingElem("65.8");
        viewModel.addElement();
        viewModel.setAddingElem("100");
        viewModel.addElement();
        viewModel.popElement();
        viewModel.popElement();
        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getStackIsEmptyStatus());
        assertEquals("2", viewModel.getStackSize());
        assertEquals("11.0", viewModel.getStackTopElement());
        assertEquals("65.8", viewModel.getStackPopElement());
        assertEquals("100", viewModel.getAddingElement());
    }

    @Test
    public void isPopButtonVisibleAfterAddAndPopElementFewTimes() {
        viewModel.setAddingElem("231");
        viewModel.addElement();
        viewModel.popElement();
        viewModel.setAddingElem("37");
        viewModel.addElement();
        viewModel.setAddingElem("1324");
        viewModel.addElement();

        assertEquals(true, viewModel.isPopButtonVisible());
    }

    @Test
    public void isReadyToAddStatusSetAfterAddAndPopElementFewTimes() {
        viewModel.setAddingElem("11");
        viewModel.addElement();
        viewModel.popElement();
        viewModel.setAddingElem("31");
        viewModel.addElement();
        viewModel.setAddingElem("1");
        viewModel.addElement();
        viewModel.setAddingElem("352");
        viewModel.addElement();
        viewModel.setAddingElem("452");
        viewModel.addElement();
        viewModel.popElement();
        viewModel.popElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessage());
    }

    @Test
    public void isWaitingStatusSetWhenCanNotPopFromEmptyStack() {
        viewModel.popElement();

        assertEquals(ViewModel.WAITING_FOR_INPUT, viewModel.getStatusMessage());
    }

    @Test
    public void isPopButtonNotVisibleWhenCanNotPopFromEmptyStack() {
        viewModel.popElement();

        assertEquals(false, viewModel.isPopButtonVisible());
    }

    @Test
    public void correctDefaultStackIsEmptyStatus() {
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.stackIsEmptyStatusProperty().get());
    }

    @Test
    public void correctDefaultStackSize() {
        assertEquals("0", viewModel.stackSizeProperty().get());
    }

    @Test
    public void correctDefaultTopElement() {
        assertEquals(viewModel.NONE, viewModel.stackTopElementProperty().get());
    }

    @Test
    public void correctDefaultPopElement() {
        assertEquals(viewModel.NONE, viewModel.stackPopElementProperty().get());
    }

    @Test
    public void correctAddingElement() {
        viewModel.setAddingElem("11");

        assertEquals("11", viewModel.addingElementProperty().get());
    }

    @Test
    public void correctDefaultStatusMessage() {
        assertEquals(viewModel.WAITING_FOR_INPUT, viewModel.statusMessageProperty().get());
    }

    @Test
    public void correctDefaultPopButtonVisible() {
        assertEquals(false, viewModel.popButtonVisibleProperty().get());
    }
}
