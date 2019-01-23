package ru.unn.agile.MyAbstractSet.model;

class Node<T> implements Comparable<Node> {
    private final T key;
    private Node left, right;
    private int height;

    Node(final T k) {
        key = k;
        left = null;
        right = null;
        height = 1;
    }

    Node(final T k, final Node l, final Node r) {
        key = k;
        left = l;
        right = r;
        height = 1 + max(l.height, r.height);
    }

    Node(final Node<T> other) {
        this.key = other.key;
        this.left = other.left;
        this.right = other.right;
        this.height = other.height;
    }

    public T getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int h) {
        height = h;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(final Node l) {
        left = l;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(final Node r) {
        right = r;
    }

    @Override
    public int compareTo(final Node other) {
        if (this.key instanceof Integer && other.key instanceof Integer) {
            // This is done for testing purposes only
            return Integer.compare((Integer) this.key, (Integer) other.key);
        }

        int hash1 =  this.getKey().hashCode();
        int hash2 = other.getKey().hashCode();
        return Integer.compare(hash1, hash2);
    }

    private int max(final int a, final int b) {
        return (a > b) ? a : b;
    }
}
