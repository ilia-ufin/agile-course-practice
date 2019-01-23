package ru.unn.agile.fibonacciHeap.model;

public class Node {
    private Node left, right, child, parent;
    private int degree = 0;
    private boolean mark = false;
    private int value;

    Node(final int value) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.child = null;
        this.degree = 0;
        this.value = value;
    }

    public void incrementDegree() {
        this.degree++;
    }

    public void decrementDegree() {
        this.degree--;
    }

    public int getDegree() {
        return degree;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(final Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(final Node right) {
        this.right = right;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(final Node child) {
        this.child = child;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setMark(final boolean mark) {
        this.mark = mark;
    }

    public boolean isMark() {
        return this.mark;
    }
};
