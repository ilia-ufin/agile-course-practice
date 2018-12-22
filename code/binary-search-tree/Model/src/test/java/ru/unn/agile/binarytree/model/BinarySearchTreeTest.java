package ru.unn.agile.binarytree.model;

import ru.unn.agile.binarytree.model.node.BinaryNode;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void canCreateVertexBST() {
        BinaryNode<String, Integer> tree = new BinaryNode<String, Integer>("A", 4);

        assertEquals(1, tree.getCount());
    }

    @Test
    public void areEqualVertexBST() {
        BinaryNode<String, Integer> vertex1 = new BinaryNode<String, Integer>("X", 1);
        BinaryNode<String, Integer> vertex2 = new BinaryNode<String, Integer>("X", 1);

        assertEquals(0, vertex1.compareTo(vertex2));
    }

    @Test
    public void areNotEqualVertexWithEqualKeyBST() {
        BinaryNode<String, Integer> vertex1 = new BinaryNode<String, Integer>("X", 1);
        BinaryNode<String, Integer> vertex2 = new BinaryNode<String, Integer>("X", 2);

        assertNotEquals(0, vertex1.compareTo(vertex2));
    }

    @Test
    public void areNotEqualVertexWithEqualValueBST() {
        BinaryNode<String, Integer> vertex1 = new BinaryNode<String, Integer>("X", 1);
        BinaryNode<String, Integer> vertex2 = new BinaryNode<String, Integer>("Y", 1);

        assertNotEquals(0, vertex1.compareTo(vertex2));
    }

    @Test
    public void canConvertToStringVertexBST() {
        BinaryNode<String, Double> vertex = new BinaryNode<String, Double>("X", 55.55);

        assertEquals("[ X; 55.55 ]", vertex.toString());
    }

    @Test
    public void canCreateBST() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();

        assertNotNull(tree);
    }

    @Test
    public void canCreateEmptyBST() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();

        assertEquals(0, tree.size());
    }

    @Test
    public void canInsertOneVertexInBST() {
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();

        tree.insert("A", 1);

        assertEquals(1, tree.size());
    }

    @Test
    public void canInsertFiveVerticesInBST() {
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();

        tree.insert("A", 5);
        tree.insert("C", 2);
        tree.insert("B", 9);
        tree.insert("Z", 7);
        tree.insert("R", 3);

        assertEquals(5, tree.size());
    }

    @Test
    public void canFindVertexByExistingKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("A", 5.5);
        tree.insert("Z", 7.7);
        tree.insert("R", 3.3);

        assertNotNull(null, tree.find("Z"));
    }

    @Test
    public void canFindVertexByKeyNotExistingInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("A", 5.5);
        tree.insert("Z", 7.7);
        tree.insert("R", 3.3);

        assertNull(null, tree.find("X"));
    }

    @Test
    public void canRemoveVertexByExistingKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("Y", 5.2);
        tree.insert("Z", 8.8);
        tree.insert("R", 4.4);
        tree.insert("X", 1.5);

        String key = "R";

        tree.remove(key);

        assertNull(tree.find(key));
        assertEquals(3, tree.size());
    }

    @Test
    public void canRemoveVertexByNotExistingKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("Y", 2.2);
        tree.insert("Z", 8.8);
        tree.insert("R", 4.4);
        tree.insert("X", 1.1);

        String key = "A";

        tree.remove(key);

        assertNull(null, tree.find(key));
        assertEquals(4, tree.size());
    }

    @Test
    public void canRemoveAllVertexByKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        String key1 = "Y";
        String key2 = "Z";
        String key3 = "R";
        String key4 = "X";

        tree.insert(key1, 2.2);
        tree.insert(key2, 8.8);
        tree.insert(key3, 4.4);
        tree.insert(key4, 1.1);

        tree.remove(key1);
        tree.remove(key2);
        tree.remove(key3);
        tree.remove(key4);

        assertEquals(0, tree.size());
    }

    @Test
    public void canFindVertexWithMinKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("A", 33.33);
        tree.insert("B", 33.33);
        tree.insert("C", 44.44);

        BinaryNode<String, Double> minVertex = new  BinaryNode<String, Double>("A", 33.33);
        BinaryNode<String, Double> foundMinVertex = tree.min();

        assertEquals(0, minVertex.compareTo(foundMinVertex));
    }

    @Test
    public void canFindVertexWithMinKeyInEmptyBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        assertNull(tree.min());
    }

    @Test
    public void canFindVertexWithMaxKeyInBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        tree.insert("A", 2.2);
        tree.insert("B", 8.8);
        tree.insert("C", 4.4);

        BinaryNode<String, Double> maxVertex = new  BinaryNode<String, Double>("C", 4.4);
        BinaryNode<String, Double> foundMaxVertex = tree.max();

        assertEquals(0, maxVertex.compareTo(foundMaxVertex));
    }

    @Test
    public void canFindVertexWithMaxKeyInEmptyBST() {
        BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

        assertNull(tree.max());
    }

    @Test
    public void canSetValueToBinaryNode() {
        BinaryNode<String, Integer> tree = new BinaryNode<String, Integer>("A", 4);
        tree.setValue(5);

        assertEquals(Integer.valueOf(5), tree.getValue());
    }


    @Test
    public void canInsertWithTheSameKey() {
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<String, Integer>();

        tree.insert("A", 5);
        tree.insert("A", 3);

        assertEquals(1, tree.size());
    }
}
