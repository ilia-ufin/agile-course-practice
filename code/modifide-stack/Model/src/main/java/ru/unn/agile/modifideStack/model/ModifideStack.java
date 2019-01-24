package ru.unn.agile.modifideStack.model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ModifideStack {
    private List<Integer> listStack;

    private Integer minElem;

    public ModifideStack() {
        listStack = new ArrayList<>();
    }

    public boolean isEmpty() {
        return listStack.isEmpty();
    }

    public Integer getMin() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return minElem;
        }
    }

    public int getSize() {
        return listStack.size();
    }

    public Integer push(final Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Element must not be null");
        }

        if (isEmpty()) {
            minElem = item;
            listStack.add(item);
            return item;
        }

        if (item < minElem) {
        listStack.add(2 * item - minElem);
            minElem = item;
        } else {
            listStack.add(item);
        }
        return item;
    }

    public Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Integer top = listStack.remove(getSize() - 1);

        if (top < minElem) {
            minElem = 2 * minElem - top;
            return minElem;
        } else {
            return top;
        }
    }

    public Integer peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Integer top = listStack.get(getSize() - 1);

        if (top < minElem) {
            minElem = 2 * minElem - top;
            return minElem;
        } else {
            return top;
        }
    }
}
