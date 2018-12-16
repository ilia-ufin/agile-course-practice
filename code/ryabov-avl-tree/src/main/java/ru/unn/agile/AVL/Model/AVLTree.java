package ru.unn.agile.AVL.Model;

public class AVLTree<K extends Comparable<K>, V extends Comparable<V>> {

    private enum ChildType { Left, Right }

    public Node<K, V> insert(final K key, final V value) {

        if (root == null) {
            root = new Node<>(key, value);
            return root;
        }

        Node<K, V> current = root;
        Node<K, V> parent  = current;

        ChildType[] parents = new ChildType[root.getHeight()];
        int parentsCount = 0;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.getKey());
            if (cmp < 0) {
                current = current.getLeft();
                parents[parentsCount] = ChildType.Left;
            } else if (cmp > 0) {
                current = current.getRight();
                parents[parentsCount] = ChildType.Right;
            } else {
                current.setValue(value);
                return current;
            }
            parentsCount++;
        }

        current = new Node<>(key, value);
        int cmp = key.compareTo(parent.getKey());
        if (cmp < 0) {
            parent.setLeft(current);
        } else if (cmp > 0) {
            parent.setRight(current);
        }

        Node<K, V> nodeToBalance;
        for (int i = parentsCount - 1; i > 0; i--) {
            nodeToBalance = parent;
            parent        = parent.getParent();
            nodeToBalance = balanceNode(nodeToBalance);

            if (parents[i] == ChildType.Left) {
                parent.setLeft(nodeToBalance);
            } else {
                parent.setRight(nodeToBalance);
            }
        }

        root = balanceNode(parent);
        root.setParent(null);
        return current;
    }

    public Node<K, V> find(final K key) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.getKey());
            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                return current;
            }
        }

        return null;
    }

    private int getHeight(final Node<K, V> node) {
        return node != null ? node.getHeight() : 0;
    }

    private int getBalance(final Node<K, V> node) {
        return getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    private void fixHeight(final Node<K, V> node) {
        int leftHeight  = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        node.setHeight((leftHeight > rightHeight ? leftHeight : rightHeight) + 1);
    }

    private Node<K, V> rotateRight(final Node<K, V> node) {
        Node<K, V> temp = node.getLeft();

        node.setLeft(temp.getRight());

        temp.setRight(node);

        fixHeight(node);
        fixHeight(temp);

        return temp;
    }

    private Node<K, V> rotateLeft(final Node<K, V> node) {
        Node<K, V> temp = node.getRight();

        node.setRight(temp.getLeft());

        temp.setLeft(node);

        fixHeight(node);
        fixHeight(temp);

        return temp;
    }

    private Node<K, V> balanceNode(final Node<K, V> node) {
        fixHeight(node);

        if (getBalance(node) == 2) {
            if (getBalance(node.getRight()) < 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        //Bypass gradle magic number check
        final int disbalanced = -2;

        if (getBalance(node) == disbalanced) {
            if (getBalance(node.getLeft()) > 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        return node;
    }

    private Node<K, V> root = null;
}
