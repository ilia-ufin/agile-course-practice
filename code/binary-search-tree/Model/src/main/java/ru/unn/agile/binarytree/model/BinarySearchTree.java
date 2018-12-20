package ru.unn.agile.binarytree.model;

import  ru.unn.agile.binarytree.model.node.BinaryNode;

public class BinarySearchTree<K extends Comparable<K>, V extends Comparable<V>> {

    private BinaryNode<K, V> root;

    public void insert(final K key, final V val) {
        root = insert(root, key, val);
    }

    private BinaryNode<K, V> insert(
            final BinaryNode<K, V> vertex,
            final K key,
            final V val) {

        if (vertex == null) {
            return new BinaryNode<K, V>(key, val);
        }

        int cmp = key.compareTo(vertex.getKey());

        if (cmp < 0) {
            vertex.setLeft(insert(vertex.getLeft(), key, val));
        } else if (cmp > 0) {
            vertex.setRight(insert(vertex.getRight(), key, val));
        } else if (cmp == 0) {
            vertex.setValue(val);
        }

        vertex.setCount(1 + size(vertex.getLeft()) + size(vertex.getRight()));

        return vertex;
    }

    public BinaryNode<K, V> find(final K key) {
        BinaryNode<K, V> vertex = root;
        while (vertex != null) {
            int cmp = key.compareTo(vertex.getKey());
            if (cmp < 0) {
                vertex = vertex.getLeft();
            } else if (cmp > 0) {
                vertex = vertex.getRight();
            } else if (cmp == 0) {
                return vertex;
            }
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(final BinaryNode<K, V> vertex) {
        if (vertex == null) {
            return 0;
        }
        return vertex.getCount();
    }

    public BinaryNode<K, V> min() {
        return min(root);
    }

    private BinaryNode<K, V> min(final BinaryNode<K, V> vertex) {
        if (vertex != null) {
            if (vertex.getLeft() == null) {
                return vertex;
            }
            return min(vertex.getLeft());
        }
        return null;
    }

    public BinaryNode<K, V> max() {
        return max(root);
    }

    private BinaryNode<K, V> max(final BinaryNode<K, V> vertex) {
        if (vertex != null) {
            if (vertex.getRight() == null) {
                return vertex;
            }
            return max(vertex.getRight());
        }
        return null;
    }

    private BinaryNode<K, V> removeMin(final BinaryNode<K, V> vertex) {
        if (vertex != null) {
            if (vertex.getLeft() == null) {
                return vertex.getRight();
            }

            vertex.setLeft(removeMin(vertex.getLeft()));
            vertex.setCount(size(vertex.getLeft()) + size(vertex.getRight()) + 1);

            return vertex;
        }
        return null;
    }

    public void remove(final K key) {
        root = remove(root, key);
    }

    private BinaryNode<K, V> remove(final BinaryNode<K, V> vertex, final K key) {
        if (vertex == null) {
            return null;
        }

        BinaryNode<K, V> v = vertex;
        int cmp = key.compareTo(v.getKey());
        if (cmp < 0) {
            v.setLeft(remove(v.getLeft(), key));
        } else if (cmp > 0) {
            v.setRight(remove(v.getRight(), key));
        } else {
            if (v.getRight() == null) {
                return v.getLeft();
            }

            if (v.getLeft() == null) {
                return v.getRight();
            }

            BinaryNode<K, V> tmp = v;
            v = min(tmp.getRight());
            v.setRight(removeMin(tmp.getRight()));
            v.setLeft(tmp.getLeft());
        }

        v.setCount(size(v.getLeft()) + size(v.getRight()) + 1);
        return v;
    }

    public enum Operation {
        ADD("Add") {
            public boolean apply(final BinarySearchTree<String, Double> tree,
                                 final BinaryNode<String, Double> node) {
                if (isNodeInTree(tree, node)) {
                    return false;
                }
                tree.insert(node.getKey(), node.getValue());
                return true;
            }
        },

        DELETE("Del") {
            public boolean apply(final BinarySearchTree<String, Double> tree,
                                 final BinaryNode<String, Double> node) {
                if (!isNodeInTree(tree, node)) {
                    return false;
                }
                tree.remove(node.getKey());
                return true;
            }
        };

        private static boolean isNodeInTree(final BinarySearchTree<String, Double> tree,
                                     final BinaryNode<String, Double> node) {
            return null == tree.find(node.getKey()) ? false : true;
        }

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract boolean apply(BinarySearchTree<String, Double> tree,
                                      BinaryNode<String, Double> node);
    }
}
