package ru.unn.agile.queue.model;

import ru.unn.agile.queue.model.errorhandling.EmptyQueueException;

import org.junit.Test;
import static org.junit.Assert.*;


public final class QueueTest {
    @Test
    public void canCreateQueue() {
        Queue<Integer> q = new Queue<>();

        assertNotEquals(null, q);
    }

    @Test
    public void canPushIntegerElementToQueue() {
        Queue<Integer> q = new Queue<>();
        Integer expectedElement = 1;

        q.push(expectedElement);

        assertEquals(expectedElement, q.getTail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenPushNullElementToQueue() {
        Queue<Integer> q = new Queue<>();

        q.push(null);
    }

    @Test
    public void canPopIntegerElementFromQueue() {
        Queue<Integer> q = new Queue<>();
        Integer expectedElement = 1;
        q.push(expectedElement);

        Integer actualElement = q.pop();

        assertEquals(expectedElement, actualElement);
    }

    @Test(expected = EmptyQueueException.class)
    public void throwWhenPopFromEmptyQueue() {
        Queue<Integer> q = new Queue<>();

        q.pop();
    }

    @Test
    public void isQueueWithoutIntegerElementsEmptiness() {
        Queue<Integer> q = new Queue<>();

        assertTrue(q.isEmpty());
    }

    @Test
    public void isQueueWithOneIntegerElementNotEmpty() {
        Queue<Integer> q = new Queue<>();
        q.push(1);

        assertFalse(q.isEmpty());
    }

    @Test(expected = EmptyQueueException.class)
    public void throwWhenGetHeadIntegerElementFromEmptyQueue() {
        Queue<Integer> q = new Queue<>();

        q.getHead();
    }

    @Test(expected = EmptyQueueException.class)
    public void throwWhenGetTailIntegerElementFromEmptyQueue() {
        Queue<String> q = new Queue<>();

        q.getTail();
    }

    @Test
    public void canPushStringElementToQueue() {
        Queue<String> q = new Queue<>();
        String expectedElement = "abc";

        q.push(expectedElement);

        assertEquals(expectedElement, q.getTail());
    }

    @Test
    public void isQueueSupportedFIFOPrinciple() {
        Queue<Integer> q = new Queue<>();
        Integer firstExpectedElement = 1;
        Integer secondExpectedElement = 2;
        q.push(firstExpectedElement);
        q.push(secondExpectedElement);

        Integer firstActualElement = q.pop();
        Integer secondActualElement = q.pop();

        assertEquals(firstExpectedElement, firstActualElement);
        assertEquals(secondExpectedElement, secondActualElement);
    }
}
