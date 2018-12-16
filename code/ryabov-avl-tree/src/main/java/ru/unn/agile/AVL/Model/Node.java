package ru.unn.agile.AVL.Model;

public class Node<K extends Comparable<K>, V extends Comparable<V>> {

    public Node(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public void setParent(final Node<K, V> parent) {
        this.parent = parent;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(final Node<K, V> left) {
        this.left = left;
        if (this.left != null) {
            this.left.setParent(this);
        }
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(final Node<K, V> right) {
        this.right = right;
        if (this.right != null) {
            this.right.setParent(this);
        }
    }
    private K          key    = null;
    private V          value  = null;

    private int        height = 1;

    private Node<K, V> parent = null;
    private Node<K, V> left   = null;
    private Node<K, V> right  = null;
}
