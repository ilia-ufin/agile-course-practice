package ru.unn.agile.AVL.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel vm;

    @Before
    public void setUp() {
        vm = new ViewModel();
    }

    @After
    public void tearDown() {
        vm = null;
    }

    @Test
    public void keyIsEmptyByDefault() {
        assertEquals("", vm.keyProperty().get());
    }

    @Test
    public void valueIsEmptyByDefault() {
        assertEquals("", vm.valueProperty().get());
    }

    @Test
    public void operationIsInsertByDefault() {
        assertEquals(Operation.INSERT, vm.operationProperty().get());
    }

    @Test
    public void statusIfWaitingForInsertByDefault() {
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void resultIsEmptyByDefault() {
        assertEquals("", vm.resultProperty().get());
    }

    @Test
    public void statusReactsToOperationSwitchToSearch() {
        vm.operationProperty().set(Operation.SEARCH);

        assertEquals(Status.WAITING_FOR_SEARCH.toString(), vm.statusProperty().get());
    }

    @Test
    public void keyIsClearedOnOperationSwitch() {
        vm.keyProperty().set("somekey");
        vm.operationProperty().set(Operation.SEARCH);

        assertEquals("", vm.keyProperty().get());
    }

    @Test
    public void valueIsClearedOnOperationSwitch() {
        vm.valueProperty().set("somevalue");
        vm.operationProperty().set(Operation.SEARCH);

        assertEquals("", vm.valueProperty().get());
    }

    @Test
    public void statusReactsToOperationSwitchToInsert() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.operationProperty().set(Operation.INSERT);

        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    private void fillKeyAndValue() {
        vm.keyProperty().set("abc");
        vm.valueProperty().set("123");
    }

    @Test
    public void canSetWaitingForInsertStatusOnKeyChange() {
        fillKeyAndValue();
        vm.keyProperty().set("");

        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void canSetWaitingForInsertStatusOnValueChange() {
        fillKeyAndValue();
        vm.valueProperty().set("");

        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void canSetReadyForInsertStatus() {
        fillKeyAndValue();
        assertEquals(Status.READY_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void canSetReadyForSearchStatus() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("k");

        assertEquals(Status.READY_FOR_SEARCH.toString(), vm.statusProperty().get());
    }

    @Test
    public void canSetWaitingForSearchStatusOnKeyChange() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("");

        assertEquals(Status.WAITING_FOR_SEARCH.toString(), vm.statusProperty().get());
    }

    @Test
    public void actionButtonIsDisabledByDefault() {
        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionIsEnabledWhenInputsReadyForInsert() {
        fillKeyAndValue();

        assertFalse(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionIsDisabledWhenKeyIsMissedForInsert() {
        fillKeyAndValue();
        vm.keyProperty().set("");

        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionIsDisabledWhenValueIsMissedForInsert() {
        fillKeyAndValue();
        vm.valueProperty().set("");

        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionIsEnabledWhenInputsReadyForSearch() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("k");

        assertFalse(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionIsDisabledWhenKeyIsMissedForSearch() {
        vm.keyProperty().set("abc");
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("");

        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void valueIsEditableByDefault() {
        assertFalse(vm.valueDisabledProperty().get());
    }

    @Test
    public void valueIsNotEditableForSearch() {
        vm.operationProperty().set(Operation.SEARCH);

        assertTrue(vm.valueDisabledProperty().get());
    }

    @Test
    public void valueIsEditableWhenBackToInsert() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.operationProperty().set(Operation.INSERT);

        assertFalse(vm.valueDisabledProperty().get());
    }

    @Test
    public void canSetBadFormatStatus() {
        vm.valueProperty().set("abc");
        assertEquals(Status.BAD_FORMAT.toString(), vm.statusProperty().get());
    }

    @Test
    public void ableToInsert() {
        fillKeyAndValue();
        vm.doAction();

        assertEquals(Status.INSERTED.toString(), vm.statusProperty().get());
    }

    @Test
    public void resultIsEmptyAfterInsert() {
        fillKeyAndValue();
        vm.doAction();

        assertEquals("", vm.resultProperty().get());
    }

    @Test
    public void ableToFind() {
        fillKeyAndValue();
        vm.doAction();

        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals(Status.FOUND.toString(), vm.statusProperty().get());
    }

    @Test
    public void resultIsUpdatedWhenAbleToFind() {
        fillKeyAndValue();
        vm.doAction();

        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals("123", vm.resultProperty().get());
    }

    @Test
    public void ableToSetStatusToNotFound() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals(Status.NOT_FOUND.toString(), vm.statusProperty().get());
    }

    @Test
    public void resultIsEmptyWhenNotAbleToFind() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals("", vm.resultProperty().get());
    }
}
