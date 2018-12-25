package ru.unn.agile.binarytree.model.node;

public class BinaryNode<K extends Comparable<K>, V extends Comparable<V>>
        implements Comparable<BinaryNode<K, V>> {
    private K key;
    private V value;
    private BinaryNode<K, V> left, right;
    private int count;

    public BinaryNode(final K key, final V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.count = 1;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public BinaryNode<K, V>  getLeft() {
        return this.left;
    }

    public void setLeft(final BinaryNode<K, V> left) {
        this.left = left;
    }

    public BinaryNode<K, V>  getRight() {
        return this.right;
    }

    public void setRight(final BinaryNode<K, V> right) {
        this.right = right;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "[ " + this.key + "; " + this.value + " ]";
    }

    @Override
    public int compareTo(final BinaryNode<K, V> o) {
        int cmp = this.key.compareTo(o.getKey());
        if (cmp == 0) {
            return this.value.compareTo(o.getValue());
        } else {
            return cmp;
        }
    }
}
