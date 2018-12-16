package ru.unn.agile.MyAbstractSet.model;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MyAVLTree {

    private Node root;
    private int nodesCount;

    public MyAVLTree() {
        nodesCount = 0;
    }

    public MyAVLTree(final Object... objs) {
        for (Object obj : objs) {
            add(obj);
        }
    }

    public MyAVLTree(final MyAVLTree tree) {
        Consumer<Object> adder = key -> this.add(key);
        tree.depthFirstApply(adder);
    }

    public int size() {
        return nodesCount;
    }

    public boolean empty() {
        return nodesCount == 0;
    }

    public int height() {
        return (root == null) ? 0 : root.getHeight();
    }

    public boolean contains(final Object obj) {
        Node node = new Node<>(obj);
        Node curr = root;

        while (curr != null) {
            if (node.compareTo(curr) < 0) {
                curr = curr.getLeft();
            } else if (node.compareTo(curr) > 0) {
                curr = curr.getRight();
            } else {
                return true;
            }
        }

        return false;
    }

    public final void add(final Object obj) {
        if (obj == null) {
            return;
        }

        Node newNode = new Node<>(obj);
        root = add(root, newNode);
    }

    public final void remove(final Object obj) {
        if (obj == null) {
            return;
        }

        if (this.contains(obj)) {
            --nodesCount;
            Node removeMe = new Node<>(obj);
            root = remove(root, removeMe);
        }
    }

    public void depthFirstApply(final Consumer<Object> func) {
        depthFirstApply(root, func);
    }

    public List<Object> getObjectList() {
        List<Object> list = new ArrayList<Object>();
        Consumer<Object> listAdder = key -> list.add(key);

        depthFirstApply(listAdder);
        return list;
    }

    public boolean isSame(final MyAVLTree other) {
        if (this.size() != other.size() || this.height() != other.height()) {
            return false;
        }

        List<Object> thisObjectList = this.getObjectList();
        for (Object obj : thisObjectList) {
            if (!other.contains(obj)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Object> list = getObjectList();
        String separator = ", ";

        sb.append("{");
        for (Object obj : list) {
            sb.append(obj.toString());
            sb.append(", ");
        }

        if (sb.length() > 1) {
            sb.delete(/*start*/sb.length() - separator.length(),
                    /*end*/ sb.length());
        }

        sb.append("}");
        return sb.toString();
    }

    private Node add(final Node parentNode, final Node newNode) {
        if (parentNode == null) {
            ++nodesCount;
            return newNode;
        }

        if (newNode.compareTo(parentNode) < 0) {
            parentNode.setLeft(add(parentNode.getLeft(), newNode));
        } else if (newNode.compareTo(parentNode) > 0) {
            parentNode.setRight(add(parentNode.getRight(), newNode));
        } else {
            return parentNode; // No duplicate nodes allowed
        }

        updateNodeHeight(parentNode);
        return balanceNode(parentNode);
    }

    private Node remove(final Node parentNode, final Node removeMe) {
        if (parentNode == null) {
            return parentNode;
        }

        Node newNode = parentNode;
        if (removeMe.compareTo(newNode) < 0) {
            newNode.setLeft(remove(newNode.getLeft(), removeMe));
        } else if (removeMe.compareTo(newNode) > 0) {
            newNode.setRight(remove(newNode.getRight(), removeMe));
        } else {
            newNode = removeNode(newNode);
        }

        if (newNode == null) {
            return newNode;
        }

        updateNodeHeight(newNode);
        return balanceNode(newNode);
    }

    private void depthFirstApply(final Node node, final Consumer<Object> func) {
        if (node == null) {
            return;
        }

        depthFirstApply(node.getLeft(), func);
        func.accept(node.getKey());
        depthFirstApply(node.getRight(), func);
    }

    private Node balanceNode(final Node node) {
        int balance = getNodeBalance(node);

        if (balance > 1 && getNodeBalance(node.getLeft()) > 0) {
            // Left Left Rotate
            return rightRotate(node);
        } else if (balance < -1 && getNodeBalance(node.getRight()) < 0) {
            // Right Right Rotate
            return leftRotate(node);
        } else if (balance > 1 && getNodeBalance(node.getLeft()) < 0) {
            // Left Right Rotate
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        } else if (balance < -1 && getNodeBalance(node.getRight()) > 0) {
            // Right Left Rotate
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private Node leftRotate(final Node node) {
        Node rightChild = node.getRight();
        Node rightLeftGrandChild = rightChild.getLeft();

        node.setRight(rightLeftGrandChild);
        rightChild.setLeft(node);

        updateNodeHeight(node);
        updateNodeHeight(rightChild);

        return rightChild;
    }

    private Node rightRotate(final Node node) {
        Node leftChild = node.getLeft();
        Node leftRightGrandChild = leftChild.getRight();

        node.setLeft(leftRightGrandChild);
        leftChild.setRight(node);

        updateNodeHeight(node);
        updateNodeHeight(leftChild);

        return leftChild;
    }

    private void updateNodeHeight(final Node node) {
        int leftHeight = getLeftSubTreeHeight(node);
        int rightHeight = getRightSubTreeHeight(node);
        int nodeNewHeight = 1 + max(leftHeight, rightHeight);
        node.setHeight(nodeNewHeight);
    }

    private int getNodeBalance(final Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getLeftSubTreeHeight(node);
        int rightHeight = getRightSubTreeHeight(node);

        return leftHeight - rightHeight;
    }

    private Node removeNode(final Node node) {
        if (node.getLeft() == null || node.getRight() == null) {
            return removeNodeWithNoChildrenOrOneChild(node);
        } else {
            return removeNodeWithTwoChildren(node);
        }
    }

    private Node removeNodeWithNoChildrenOrOneChild(final Node node) {
        Node child = (node.getLeft() == null) ? node.getRight() : node.getLeft();
        boolean noChildren = (child == null);
        return (noChildren) ? null : child;
    }

    private Node removeNodeWithTwoChildren(final Node node) {
        Node smallestRightNode = getMinNodeStartingFrom(node.getRight());
        Node newNode = new Node<>(smallestRightNode.getKey(), node.getLeft(), node.getRight());
        newNode.setRight(remove(node.getRight(), smallestRightNode));
        return newNode;
    }

    private Node getMinNodeStartingFrom(final Node startNode) {
        Node current = startNode;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private int getLeftSubTreeHeight(final Node node) {
        if (node == null) {
            return 0;
        }
        return (node.getLeft() == null) ? 0 : node.getLeft().getHeight();
    }

    private int getRightSubTreeHeight(final Node node) {
        if (node == null) {
            return 0;
        }
        return (node.getRight() == null) ? 0 : node.getRight().getHeight();
    }

    private int max(final int a, final int b) {
        return (a > b) ? a : b;
    }
}
