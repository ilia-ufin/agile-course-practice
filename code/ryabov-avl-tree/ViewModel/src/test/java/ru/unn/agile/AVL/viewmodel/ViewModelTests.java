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
    public void canSetDefaultValues() {
        assertEquals("", vm.keyProperty().get());
        assertEquals("", vm.valueProperty().get());
        assertEquals(Operation.INSERT, vm.operationProperty().get());
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
        assertEquals("", vm.resultProperty().get());
    }

    @Test
    public void statusReactsToOperationSwitchToSearch() {
        vm.keyProperty().set("somekey");
        vm.valueProperty().set("somevalue");
        vm.operationProperty().set(Operation.SEARCH);

        assertEquals("", vm.keyProperty().get());
        assertEquals("", vm.valueProperty().get());
        assertEquals(Status.WAITING_FOR_SEARCH.toString(), vm.statusProperty().get());
    }

    @Test
    public void statusReactsToOperationSwitchToInsert() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("somekey");
        vm.valueProperty().set("somevalue");
        vm.operationProperty().set(Operation.INSERT);

        assertEquals("", vm.keyProperty().get());
        assertEquals("", vm.valueProperty().get());
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void statusReactsForInputsChangeForInsert() {
        vm.keyProperty().set("k");
        vm.valueProperty().set("6");
        assertEquals(Status.READY_FOR_INSERT.toString(), vm.statusProperty().get());

        vm.valueProperty().set("");
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());

        vm.valueProperty().set("5");
        vm.keyProperty().set("");
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());

        vm.valueProperty().set("");
        vm.keyProperty().set("");
        assertEquals(Status.WAITING_FOR_INSERT.toString(), vm.statusProperty().get());
    }

    @Test
    public void statusReactsForInputsChangeForSearch() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("k");
        assertEquals(Status.READY_FOR_SEARCH.toString(), vm.statusProperty().get());

        vm.keyProperty().set("");
        assertEquals(Status.WAITING_FOR_SEARCH.toString(), vm.statusProperty().get());
    }

    @Test
    public void actionButtonIsDisabledByDefault() {
        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionButtonReactsForInputsChangeForInsert() {
        vm.keyProperty().set("k");
        vm.valueProperty().set("5");
        assertFalse(vm.actionDisabledProperty().get());

        vm.valueProperty().set("");
        assertTrue(vm.actionDisabledProperty().get());

        vm.valueProperty().set("6");
        vm.keyProperty().set("");
        assertTrue(vm.actionDisabledProperty().get());

        vm.valueProperty().set("");
        vm.keyProperty().set("");
        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void actionButtonReactsForInputsChangeForSearch() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("k");
        assertFalse(vm.actionDisabledProperty().get());

        vm.keyProperty().set("");
        assertTrue(vm.actionDisabledProperty().get());
    }

    @Test
    public void valueIsEditableByDefault() {
        assertFalse(vm.valueDisabledProperty().get());
    }

    @Test
    public void valueIsEditableReactsToOperationChange() {
        vm.operationProperty().set(Operation.SEARCH);
        assertTrue(vm.valueDisabledProperty().get());

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
        vm.keyProperty().set("abc");
        vm.valueProperty().set("10");
        vm.doAction();

        assertEquals("", vm.resultProperty().get());
        assertEquals(Status.INSERTED.toString(), vm.statusProperty().get());
    }

    @Test
    public void ableToFind() {
        vm.keyProperty().set("abc");
        vm.valueProperty().set("10");
        vm.doAction();

        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals("10", vm.resultProperty().get());
        assertEquals(Status.FOUND.toString(), vm.statusProperty().get());
    }

    @Test
    public void ableToSetStatusToNotFound() {
        vm.operationProperty().set(Operation.SEARCH);
        vm.keyProperty().set("abc");
        vm.doAction();

        assertEquals("", vm.resultProperty().get());
        assertEquals(Status.NOT_FOUND.toString(), vm.statusProperty().get());
    }
}
