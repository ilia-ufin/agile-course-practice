package ru.unn.agile.stack.model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<E> {
    private List<E> list;

    public Stack() {
        list = new ArrayList<>();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public E push(final E item) {
        if (item == null) {
            throw new IllegalArgumentException("Element must not be null");
        }

        list.add(item);
        return item;
    }

    public E pop() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return list.remove(size() - 1);
    }

    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return list.get(size() - 1);
    }
}
