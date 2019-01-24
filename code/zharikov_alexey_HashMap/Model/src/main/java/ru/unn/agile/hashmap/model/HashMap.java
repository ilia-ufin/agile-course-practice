package ru.unn.agile.hashmap.model;

import java.util.*;

public class HashMap {
    public static final int SIZE_MAP = 128;
    private int size;
    private ArrayList<LinkedList<Element>> array;

    public HashMap() {
        this.size = 0;
        this.array = new ArrayList<LinkedList<Element>>(SIZE_MAP);

        for (int i = 0; i < SIZE_MAP; i++) {
            this.array.add(new LinkedList<Element>());
        }
    }

    public boolean maybeContainsKey(final String key) {
        return sizearray(key) > 0;
    }

    public boolean containsKey(final String key) {
        for (Element ele : GetListOfElemsByKey(key))
            if (ele.key.equals(key)) {
                return true;
            }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(final String key, final Object value) {
        this.remove(key);
        final LinkedList<Element> elements = GetListOfElemsByKey(key);
        elements.add(new Element(key, value));
        size++;
    }

    public int size() {
        return size;
    }

    public int sizearray(String key) {
        return GetListOfElemsByKey(key).size();
    }

    public ArrayList<LinkedList<Element>> getArray() {
        return array;
    }

    public LinkedList<Element> GetListOfElemsByKey(final String key) {
        final LinkedList<Element> elements = this.array.get(hash(key));
        return elements;
    }

    public Object get(final String key) {
        for (Element ele : GetListOfElemsByKey(key)) {
            if (ele.isEquals(key)) {
                return ele.value;
            }
        }
        throw new NoSuchElementException();
    }

    public void remove(final String key) {
        for (Element ele : GetListOfElemsByKey(key)) {
            if (ele.isEquals(key)) {
                GetListOfElemsByKey(key).remove(ele);
                size--;
                break;
            }
        }
    }

    private int hash(final String key) {
        return key.hashCode() % SIZE_MAP;
    }

    private class Element {
        public final String key;
        public final Object value;

        public Element(final String key, final Object value) {
            this.key = key;
            this.value = value;
        }

        public boolean isEquals(final String key) {
            return this.key.equals(key);
        }

    }
};
