package ru.unn.agile.modifideStack.model;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModifideStackTest {
    @Test
    public void canCreateModifideStack() {
        ModifideStack modifideStack = new ModifideStack();

        assertNotEquals(null, modifideStack);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotPushNullToModifideStack() {
        ModifideStack modifideStack = new ModifideStack();

        modifideStack.push(null);
    }

    @Test
    public void canPushObjectToModifideStack() {
        ModifideStack modifideStack = new ModifideStack();

        Integer res = modifideStack.push(5);

        Integer appruve = 5;

        assertEquals(appruve, res);
    }

    @Test
    public void isSizeOfEmptyModifideStackEqualsZero() {
        ModifideStack modifideStack = new ModifideStack();

        int size = modifideStack.getSize();

        assertEquals(0, size);
    }

    @Test
    public void canGetSizeOfNotEmptyModifideStack() {
        ModifideStack modifideStack = new ModifideStack();
        modifideStack.push(5);

        int size = modifideStack.getSize();

        assertEquals(1, size);
    }

    @Test(expected = EmptyStackException.class)
    public void canNotPopFromEmptyModifideStack() {
        ModifideStack modifideStack = new ModifideStack();

        modifideStack.pop();
    }

    @Test
    public void canPopFromNotEmptyModifideStack() {
        ModifideStack modifideStack = new ModifideStack();
        modifideStack.push(5);
        Integer res = modifideStack.pop();

        Integer appruve = 5;

        assertEquals(appruve, res);
    }
}
