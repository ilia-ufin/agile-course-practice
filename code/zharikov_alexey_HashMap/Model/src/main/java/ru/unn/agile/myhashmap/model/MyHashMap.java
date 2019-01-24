package ru.unn.agile.myhashmap.model;

import java.util.*;

public class MyHashMap {
    private List<LinkedList<Element>> array;
    static final int SIZE_MAP = 128;
    private int size;

    public MyHashMap() {
        super();
        this.size = 0;
        this.array = new ArrayList<>(SIZE_MAP);

        for (int i = 0; i < SIZE_MAP; i++) {
            this.array.add(new LinkedList<Element>());
        }
    }

    public boolean maybeContainsKey(final String key) {
        return sizearray(key) > 0;
    }

    public boolean containsKey(final String key) {
        for (Element ele : getListOfElemsByKey(key)) {
            if (ele.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(final String key, final Object value) {
        this.remove(key);
        final List<Element> elements = getListOfElemsByKey(key);
        elements.add(new Element(key, value));
        size++;
    }

    public int size() {
        return size;
    }

    public int sizearray(final String key) {
        return getListOfElemsByKey(key).size();
    }

    public List<LinkedList<Element>> getArray() {
        return array;
    }

    public List<Element> getListOfElemsByKey(final String key) {
        return this.array.get(hash(key));
    }

    public Object get(final String key) {
        for (Element ele : getListOfElemsByKey(key)) {
            if (ele.isEquals(key)) {
                return ele.value;
            }
        }
        throw new NoSuchElementException();
    }

    public void remove(final String key) {
        for (Element ele : getListOfElemsByKey(key)) {
            if (ele.isEquals(key)) {
                getListOfElemsByKey(key).remove(ele);
                size--;
                break;
            }
        }
    }

    private int hash(final String key) {
        return key.hashCode() % SIZE_MAP;
    }

    private class Element {
        private final String key;
        private final Object value;

        Element(final String key, final Object value) {
            this.key = key;
            this.value = value;
        }

        public boolean isEquals(final String key) {
            return this.key.equals(key);
        }

    }
};
