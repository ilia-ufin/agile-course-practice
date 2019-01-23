package ru.unn.agile.MyAbstractSet.model;

import java.util.function.Consumer;

public class MyAbstractSet {

    private MyAVLTree tree;

    public MyAbstractSet(final Object... objs) {
        tree = new MyAVLTree();
        for (Object obj : objs) {
            tree.add(obj);
        }
    }

    public MyAbstractSet(final MyAbstractSet set) {
        tree = new MyAVLTree(set.tree);
    }

    public int size() {
        return tree.size();
    }

    public boolean empty() {
        return tree.empty();
    }

    public boolean contains(final Object obj) {
        return tree.contains(obj);
    }

    public void add(final Object obj) {
        tree.add(obj);
    }

    public void remove(final Object obj) {
        tree.remove(obj);
    }

    public MyAbstractSet unite(final MyAbstractSet other) {
        MyAbstractSet union = new MyAbstractSet(this);
        Consumer<Object> unionAdder = key -> union.add(key);

        other.tree.depthFirstApply(unionAdder);
        return union;
    }

    public MyAbstractSet intersect(final MyAbstractSet op2) {
        MyAbstractSet intersection = new MyAbstractSet();
        MyAbstractSet smallestSet = (this.size() < op2.size()) ? this : op2;
        MyAbstractSet otherSet = (smallestSet == op2) ? this : op2;

        Consumer<Object> intersectAdder = (Object key) -> {
            if (otherSet.contains(key)) {
                intersection.add(key);
            }
        };

        smallestSet.tree.depthFirstApply(intersectAdder);
        return intersection;
    }

    public boolean isSubset(final MyAbstractSet other) {
        MyAbstractSet union = this.unite(other);
        return union.isSame(other);
    }

    public boolean isSuperset(final MyAbstractSet other) {
        return other.isSubset(this);
    }

    public boolean isSame(final MyAbstractSet other) {
        return tree.isSame(other.tree);
    }

    @Override
    public String toString() {
        return tree.toString();
    }
}

