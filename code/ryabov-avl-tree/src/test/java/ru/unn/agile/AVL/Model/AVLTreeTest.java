package ru.unn.agile.AVL.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test
    public void insertTheKeyIntoAnEmptyTree() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        Node<Integer, Integer> nodeToInsert = new Node<>(0, 0);
        Node<Integer, Integer> insertedNode
                = tree.insert(nodeToInsert.getKey(), nodeToInsert.getValue());

        assertEquals(nodeToInsert.getValue(),
                insertedNode != null ? insertedNode.getValue() : null);
    }

    @Test
    public void findNull() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        assertEquals(tree.find(null), null);
    }

    @Test
    public void insertIntegerStringSequence() {
        AVLTree<Integer, String> tree = new AVLTree<>();

        tree.insert(1, "A");
        tree.insert(2, "B");
        tree.insert(4, "D");
        tree.insert(5, "E");
        tree.insert(3, "C");
        Node<Integer, String> insertedNode = tree.insert(6, "F");
        tree.insert(7, "G");
        tree.insert(8, "H");

        assertEquals("F", insertedNode != null ? insertedNode.getValue() : null);
    }


    @Test
    public void findNodeInASequenceWithEqualKeys() {
        AVLTree<Integer, String> tree = new AVLTree<>();

        Node<Integer, String> nodeToFind = new Node<>(77777, "FFFFFF");
        tree.insert(77777, "F");
        tree.insert(77777, "FF");
        tree.insert(77777, "FFF");
        tree.insert(77777, "FFFF");
        tree.insert(77777, "FFFFF");
        tree.insert(77777, "FFFFFF");
        tree.insert(77777, "FFFFFFF");
        tree.insert(77777, "FFFFFFFF");
        tree.insert(nodeToFind.getKey(), nodeToFind.getValue());
        tree.insert(977, "G");
        tree.insert(455678, "H");

        Node<Integer, String> foundNode = tree.find(nodeToFind.getKey());
        assertEquals(nodeToFind.getValue(), foundNode != null ? foundNode.getValue() : null);
    }

    private static void insertSequence(final AVLTree<Integer, Integer> tree, final int[] sequence) {
        for (Integer number : sequence) {
            tree.insert(number, number);
        }
    }


    private static void checkSequenceExists(final AVLTree<Integer, Integer> tree,
                                             final int[] sequence) {
        for (Integer number : sequence) {
            Node<Integer, Integer> foundNode = tree.find(number);
            assertEquals(number, foundNode != null ? foundNode.getValue() : null);
        }
    }

    private static void checkNodeBalance(final Node<Integer, Integer> node) {
        assertNotNull(node);
        assertNotNull(node.getRight());
        assertNotNull(node.getLeft());
        int balance = node.getRight().getHeight() - node.getLeft().getHeight();
        assertTrue(balance > -2 && balance < 2);
    }

    @Test
    public void insertAndFindSymmetricNumberSequence() {
        int[] sequence = java.util.stream.IntStream.rangeClosed(-50, 50).toArray();
        AVLTree<Integer, Integer> tree = new AVLTree<>();

        insertSequence(tree, sequence);
        checkSequenceExists(tree, sequence);
    }

    @Test
    public void checkTreeBalancingRightRotate() {
        int[] sequence = {5, 6, 4, 3, 2, 1};
        AVLTree<Integer, Integer> tree = new AVLTree<>();

        insertSequence(tree, sequence);

        Node<Integer, Integer> root = tree.find(5);
        checkNodeBalance(root);
    }

    @Test
    public void checkTreeBalancingLeftRotate() {
        int[] sequence = {5, 6, 4, 7, 8, 9};
        AVLTree<Integer, Integer> tree = new AVLTree<>();

        insertSequence(tree, sequence);

        Node<Integer, Integer> root = tree.find(5);
        checkNodeBalance(root);
    }
}
