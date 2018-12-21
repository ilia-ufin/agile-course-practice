package ru.unn.agile.fibonacciHeap.model;

import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap {
    private int rootAmount;
    private Node rootNode;
    public static final int SIZE_OF_TABLE = 40;

    public int getRootAmount() {
        return rootAmount;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public FibonacciHeap() {
        rootAmount = 0;
        rootNode = null;
    }

    public void add(final int value) {
        add(new Node(value));
    }

    public void add(final Node node) {
        if (rootNode == null) {
            rootNode = node;
        } else {
            Node rootRight = rootNode.getRight();

            if (rootRight != null) {
                node.setRight(rootRight);
                rootRight.setLeft(node);
            } else {
                rootNode.setLeft(node);
                node.setRight(rootNode);
            }

            rootNode.setRight(node);
            node.setLeft(rootNode);

            if (node.getValue() < rootNode.getValue()) {
                rootNode = node;
            }
        }

        this.rootAmount++;
    }

    private void removeFromList(final Node z) {
        Node rightNode = z.getRight();
        Node leftNode = z.getLeft();

        if (rightNode != null) {
            rightNode.setLeft(leftNode);
        }

        if (leftNode != null) {
            leftNode.setRight(rightNode);
        }
    }

    private void addToRootList(final Node x) {
        Node rootRight = rootNode.getRight();

        x.setLeft(rootNode);
        x.setRight(rootRight);
        rootNode.setRight(x);
        x.setParent(null);
    }

    public Node pop() {
        Node r = rootNode;

        if (r != null) {
            Node rRight = r.getRight();

            int numberOfChildren = r.getDegree();
            Node child = r.getChild();
            Node tempRight;

            while (numberOfChildren > 0) {
                tempRight = child.getRight();

                removeFromList(child);

                addToRootList(child);

                child = tempRight;

                r.decrementDegree();
                numberOfChildren--;
            }

            removeFromList(r);

            if (r == rRight) {
                rootNode = null;
            } else {
                rootNode = rRight;
                merge();
            }
            rootAmount--;

            return r;
        }

        return null;
    }

    public void merge() {
        List<Node> degreeTable =
                new ArrayList<Node>(SIZE_OF_TABLE);

        for (int i = 0; i < SIZE_OF_TABLE; i++) {
            degreeTable.add(null);
        }

        int numRoots = 0;
        Node x = rootNode;

        if (x != null) {
            numRoots++;
            x = x.getRight();

            while (x != rootNode) {
                numRoots++;
                x = x.getRight();
            }
        }

        while (numRoots > 0) {
            int d = x.getDegree();
            Node next = x.getRight();
            Node y = degreeTable.get(d);

            while (y != null) {
                if (x.getValue() < y.getValue()) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }

                linkChild(y, x);

                degreeTable.set(d, null);
                y = degreeTable.get(d++);
            }

            degreeTable.set(d, x);

            x = next;
            numRoots--;
        }

        rootNode = null;

        for (int i = 0; i < SIZE_OF_TABLE; i++) {
            Node y = degreeTable.get(i);
            if (y == null) {
                continue;
            }

            if (rootNode != null) {
                removeFromList(y);

                addToRootList(y);

                rootNode.setRight(y);
                Node yRight = y.getRight();
                yRight.setLeft(y);

                if (y.getValue() < rootNode.getValue()) {
                    rootNode = y;
                }
            } else {
                rootNode = y;
            }
        }
    }

    public void linkChild(final Node y, final Node x) {
        removeFromList(y);

        y.setParent(x);

        Node xChild = x.getChild();

        if (xChild == null) {
            x.setChild(y);
            y.setRight(y);
            y.setLeft(y);
        } else {
            y.setLeft(xChild);
            y.setRight(xChild.getRight());

            xChild.setRight(y);
            y.getRight().setLeft(y);
        }

        x.incrementDegree();
        y.isMark();
        y.setMark(false);
    }
}
