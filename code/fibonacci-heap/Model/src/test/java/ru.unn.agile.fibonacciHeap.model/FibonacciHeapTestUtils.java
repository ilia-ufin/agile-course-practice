package ru.unn.agile.fibonacciHeap.model;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.*;

@RunWith(OleasterRunner.class)
public class FibonacciHeapTestUtils {
    private FibonacciHeap heap;
    private Node firstNode;
    private Node secondNode;
    private Node thirdNode;
    private double delta = 0.00001;
    {
        //BEGIN BDD CODE
        describe("Constructor", () -> {
            beforeEach(() -> {
                heap = new FibonacciHeap();
            });

            describe("By default. Create instance", () -> {
                it("with length = 0", () -> {
                    assertEquals(heap.getRootAmount(), 0, delta);
                });

                it("with null rootNode", () -> {
                   assertNull(heap.getRootNode());
                });
            });
        });

        describe("getNodeListSize method", () -> {
            beforeEach(() -> {
                firstNode = new Node(1);
                secondNode = new Node(2);
                thirdNode = new Node(3);
            });

            it("Should return 1 for single node", () -> {
                assertEquals(heap.getNodeListSize(firstNode), 1, delta);
            });

            it("Should count nodes in tree", () -> {
                firstNode.setChild(secondNode);
                secondNode.setRight(thirdNode);

                assertEquals(heap.getNodeListSize(firstNode), 3, delta);
            });
        });
    }
    //END BDD CODE
}
