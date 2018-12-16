package ru.unn.agile.AVL.Model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NodeTest {
    @Test
    public void creatingANode() {
        Node<Integer, Integer> node = new Node<>(0, 0);

        assertNotNull(node);
    }

}
