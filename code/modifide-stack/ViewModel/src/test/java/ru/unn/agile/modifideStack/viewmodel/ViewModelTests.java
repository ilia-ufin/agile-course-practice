package ru.unn.agile.modifideStack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUpModel() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("0", viewModel.getModifideStackSize());
        assertEquals(viewModel.NONE, viewModel.getModifideStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getModifideStackPopElement());
        assertEquals("", viewModel.getPushElement());
    }

    @Test
    public void isNotVisiblePopButtonWhenStart() {
        assertEquals(false, viewModel.isVisiblePopButton());
    }

    @Test
    public void isWaitingForInputStatusSetWhenStart() {
        assertEquals(ViewModel.WAITING_FOR_INPUT, viewModel.getStatusMessageTxt());
    }

    @Test
    public void testAddNewElement() {
        viewModel.setPushElem("5");
        viewModel.pushingElement();

        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("1", viewModel.getModifideStackSize());
        assertEquals("5", viewModel.getModifideStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getModifideStackPopElement());
        assertEquals("5", viewModel.getPushElement());
    }

    @Test
    public void isReadyToAddStatusSetWhenAddNewElement() {
        viewModel.setPushElem("7");
        viewModel.pushingElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessageTxt());
    }

    @Test
    public void isVisiblePopButtonAfterAddingElement() {
        viewModel.setPushElem("77");
        viewModel.pushingElement();

        assertEquals(true, viewModel.isVisiblePopButton());
    }

    @Test
    public void doNotAddNonValidElement() {
        viewModel.setPushElem("abc");
        viewModel.pushingElement();

        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("0", viewModel.getModifideStackSize());
        assertEquals(viewModel.NONE, viewModel.getModifideStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getModifideStackPopElement());
        assertEquals("abc", viewModel.getPushElement());
    }

    @Test
    public void doNotAddEmptyElement() {
        viewModel.setPushElem("");
        viewModel.pushingElement();

        assertEquals(viewModel.NONE, viewModel.getModifideStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getModifideStackPopElement());
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("0", viewModel.getModifideStackSize());
        assertEquals("", viewModel.getPushElement());
    }

    @Test
    public void isInvalidFormatStatusSetWhenAddInvalidElement() {
        viewModel.setPushElem("abc");
        viewModel.pushingElement();

        assertEquals(ViewModel.INVALID_FORMAT, viewModel.getStatusMessageTxt());
    }

    @Test
    public void isPopNotVisibleWhenAddInvalidElement() {
        viewModel.setPushElem("dc");
        viewModel.pushingElement();

        assertEquals(false, viewModel.isVisiblePopButton());
    }

    @Test
    public void canAddFewElements() {
        viewModel.setPushElem("1");
        viewModel.pushingElement();
        viewModel.setPushElem("2");
        viewModel.pushingElement();
        viewModel.setPushElem("3");
        viewModel.pushingElement();
        viewModel.setPushElem("-4");
        viewModel.pushingElement();
        viewModel.setPushElem("5");
        viewModel.pushingElement();

        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("5", viewModel.getModifideStackSize());
        assertEquals("5", viewModel.getModifideStackTopElement());
        assertEquals(viewModel.NONE, viewModel.getModifideStackPopElement());
        assertEquals("5", viewModel.getPushElement());
    }

    @Test
    public void isReadyToAddStatusSetWhenAddFewElement() {
        viewModel.setPushElem("-13");
        viewModel.pushingElement();
        viewModel.setPushElem("30");
        viewModel.pushingElement();
        viewModel.setPushElem("12");
        viewModel.pushingElement();
        viewModel.setPushElem("25");
        viewModel.pushingElement();
        viewModel.setPushElem("7");
        viewModel.pushingElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessageTxt());
    }

    @Test
    public void isVisiblePopButtonAfterAddingFewElement() {
        viewModel.setPushElem("-55");
        viewModel.pushingElement();
        viewModel.setPushElem("77");
        viewModel.pushingElement();
        viewModel.setPushElem("16");
        viewModel.pushingElement();
        viewModel.setPushElem("-128");
        viewModel.pushingElement();
        viewModel.setPushElem("228");
        viewModel.pushingElement();

        assertEquals(true, viewModel.isVisiblePopButton());
    }

    @Test
    public void canAddAndPopElement() {
        viewModel.setPushElem("1");
        viewModel.pushingElement();
        viewModel.popElement();

        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("0", viewModel.getModifideStackSize());
        assertEquals(viewModel.NONE, viewModel.getModifideStackTopElement());
        assertEquals("1", viewModel.getModifideStackPopElement());
        assertEquals("1", viewModel.getPushElement());
    }

    @Test
    public void isPopButtonNotVisibleAfterAddAndPopElement() {
        viewModel.setPushElem("-1");
        viewModel.pushingElement();
        viewModel.popElement();
        assertEquals(false, viewModel.isVisiblePopButton());
    }

    @Test
    public void isReadyToAddStatusSetAfterAddAndPopElement() {
        viewModel.setPushElem("11");
        viewModel.pushingElement();
        viewModel.popElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessageTxt());
    }

    @Test
    public void canAddAndPopElementFewTimes() {
        viewModel.setPushElem("1");
        viewModel.pushingElement();
        viewModel.popElement();
        viewModel.setPushElem("-53");
        viewModel.pushingElement();
        viewModel.setPushElem("11");
        viewModel.pushingElement();
        viewModel.setPushElem("65");
        viewModel.pushingElement();
        viewModel.setPushElem("100");
        viewModel.pushingElement();
        viewModel.popElement();
        viewModel.popElement();
        assertEquals(viewModel.STACK_IS_NOT_EMPTY, viewModel.getModifideStackIsEmptyStatus());
        assertEquals("2", viewModel.getModifideStackSize());
        assertEquals("11", viewModel.getModifideStackTopElement());
        assertEquals("65", viewModel.getModifideStackPopElement());
        assertEquals("100", viewModel.getPushElement());
    }

    @Test
    public void isVisiblePopButtonAfterAddAndPopElementFewTimes() {
        viewModel.setPushElem("231");
        viewModel.pushingElement();
        viewModel.popElement();
        viewModel.setPushElem("37");
        viewModel.pushingElement();
        viewModel.setPushElem("1324");
        viewModel.pushingElement();

        assertEquals(true, viewModel.isVisiblePopButton());
    }

    @Test
    public void isReadyToAddStatusSetAfterAddAndPopElementFewTimes() {
        viewModel.setPushElem("11");
        viewModel.pushingElement();
        viewModel.popElement();
        viewModel.setPushElem("31");
        viewModel.pushingElement();
        viewModel.setPushElem("1");
        viewModel.pushingElement();
        viewModel.setPushElem("352");
        viewModel.pushingElement();
        viewModel.setPushElem("452");
        viewModel.pushingElement();
        viewModel.popElement();
        viewModel.popElement();

        assertEquals(ViewModel.READY_TO_ADD, viewModel.getStatusMessageTxt());
    }

    @Test
    public void isWaitingStatusSetWhenCanNotPopFromEmptyModifideStack() {
        viewModel.popElement();

        assertEquals(ViewModel.WAITING_FOR_INPUT, viewModel.getStatusMessageTxt());
    }

    @Test
    public void isPopButtonNotVisibleWhenCanNotPopFromEmptyModifideStack() {
        viewModel.popElement();

        assertEquals(false, viewModel.isVisiblePopButton());
    }

    @Test
    public void correctDefaultModifideStackIsEmptyStatus() {
        assertEquals(viewModel.STACK_IS_EMPTY, viewModel.modifideStackEmptyStatusProperty().get());
    }

    @Test
    public void correctDefaultModifideStackSize() {
        assertEquals("0", viewModel.modifideStackSizeProperty().get());
    }

    @Test
    public void correctDefaultTopElement() {
        assertEquals(viewModel.NONE, viewModel.modifideStackTopElementProperty().get());
    }

    @Test
    public void correctDefaultPopElement() {
        assertEquals(viewModel.NONE, viewModel.modifideStackPopElementProperty().get());
    }

    @Test
    public void correctAddingElement() {
        viewModel.setPushElem("11");

        assertEquals("11", viewModel.pushElementProperty().get());
    }

    @Test
    public void correctDefaultStatusMessage() {
        assertEquals(viewModel.WAITING_FOR_INPUT, viewModel.statusMessageTxtProperty().get());
    }

    @Test
    public void correctDefaultPopButtonVisible() {
        assertEquals(false, viewModel.popButtonVisibleProperty().get());
    }

    @Test
    public void correctGetMinElem() {
        viewModel.setPushElem("11");
        viewModel.pushingElement();
        viewModel.setPushElem("15");
        viewModel.pushingElement();
        viewModel.setPushElem("22");
        viewModel.pushingElement();

        assertEquals("11", viewModel.getModifideStackMinElement());
    }
}
