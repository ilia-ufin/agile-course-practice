package ru.unn.agile.fibonacciHeap.model;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(OleasterRunner.class)
public class FibonacciHeapTestPop {
    private FibonacciHeap heap;
    private Node firstNode;
    private Node secondNode;
    private Node thirdNode;
    private double delta = 0.00001;

    {
        //BEGIN BDD CODE
        describe("pop method", () -> {
            beforeEach(() -> {
                heap = new FibonacciHeap();
            });

            describe("Empty heap", () -> {
               it("Should return null", () -> {
                  assertNull(heap.pop());
               });
            });

            describe("rootNode hasn't child", () -> {
                describe("Heap size = 1", () -> {
                    beforeEach(() -> {
                        firstNode = new Node(1);
                        heap.add(firstNode);
                    });

                    it("Should return rootNode", () -> {
                        Node min = heap.pop();

                        assertEquals(min, firstNode);
                    });

                    it("Should set rootAmount to 0", () -> {
                        heap.pop();

                        assertEquals(heap.getRootAmount(), 0);
                    });

                    it("Should set rootNode to null", () -> {
                        heap.pop();

                        assertNull(heap.getRootNode());
                    });
                });

                describe("Heap size = 2", () -> {
                    beforeEach(() -> {
                        firstNode = new Node(1);
                        secondNode = new Node(2);
                        heap.add(firstNode);
                        heap.add(secondNode);
                    });

                    it("Should set rootAmount to 1", () -> {
                        heap.pop();

                        assertEquals(heap.getRootAmount(), 1);
                    });

                    it("Should set rootNode to secondNode", () -> {
                        heap.pop();

                        assertEquals(heap.getRootNode(), secondNode);
                    });
                });
            });

            describe("rootNode has child", () -> {
                beforeEach(() -> {
                    firstNode = new Node(1);
                    secondNode = new Node(2);
                    thirdNode = new Node(-1);
                    heap.add(firstNode);
                    heap.add(secondNode);

                    thirdNode.setParent(firstNode);
                    firstNode.setChild(thirdNode);
                    firstNode.incrementDegree();
                });

                it("Should set parent to rootNode", () -> {
                    heap.pop();
                    Node rootNode = heap.getRootNode();

                    assertEquals(thirdNode.getParent(), rootNode);
                });

                it("Should decrement degree", () -> {
                    int degreeBefore = firstNode.getDegree();

                    heap.pop();

                    int degreeAfter = firstNode.getDegree();

                    assertEquals(degreeAfter, degreeBefore - 1, delta);
                });
            });
        });
    }
    //END BDD CODE
};
