package ru.unn.agile.fibonacciHeap.model;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.*;

@RunWith(OleasterRunner.class)
public class FibonacciHeapTestAdd {
    private FibonacciHeap heap;
    private Node firstNode;
    private Node secondNode;
    private Node thirdNode;
    private double delta = 0.00001;
    {
        //BEGIN BDD CODE
        //Чекстайл не умеет в BDD фреймворки тестирования
        describe("add method", () -> {
            beforeEach(() -> {
                heap = new FibonacciHeap();
                firstNode = new Node(1);
            });

            describe("Into empty heap", () -> {
                it("Should set as rootNode", () -> {
                    heap.add(firstNode);

                    assertEquals(heap.getRootNode(), firstNode);
                });

                it("Should increment length", () -> {
                    int lengthBefore = heap.getRootAmount();

                    heap.add(firstNode);

                    assertEquals(heap.getRootAmount(), lengthBefore + 1, delta);
                });
            });

            describe("Into heap with exist root", () -> {
                beforeEach(() -> {
                    heap = new FibonacciHeap();
                    heap.add(firstNode);
                });

                describe("Node value is gt than rootValue", () -> {
                    beforeEach(() -> {
                        secondNode = new Node(2);
                        heap.add(secondNode);
                    });

                    describe("Should link", () -> {
                        it("rootNode right to new node", () -> {
                            Node rootRight = heap.getRootNode().getRight();

                            assertEquals(rootRight, secondNode);
                        });

                        it("new node left to root node", () -> {
                            Node leftLink = secondNode.getLeft();

                            assertEquals(heap.getRootNode(), leftLink);
                        });
                    });

                    it("Should set length = 2", () -> {
                        assertEquals(heap.getRootAmount(), 2, delta);
                    });

                    it("Shouldnt'change rootNode", () -> {
                        assertEquals(heap.getRootNode(), firstNode);
                    });
                });

                describe("Node value is lt than rootValue", () -> {
                    beforeEach(() -> {
                        secondNode = new Node(-2);
                        heap.add(secondNode);
                    });

                    it("Should change rootNode", () -> {
                        assertEquals(heap.getRootNode(), secondNode);
                    });
                });
            });

            describe("Into heap where root has right sibblibng", () -> {
                beforeEach(() -> {
                    heap = new FibonacciHeap();
                    heap.add(firstNode);
                    secondNode = new Node(2);
                    heap.add(secondNode);
                    thirdNode = new Node(3);
                    heap.add(thirdNode);
                });

                describe("Should link", () -> {
                    it("rootNode right to new node", () -> {
                        Node rootRight = heap.getRootNode().getRight();

                        assertEquals(rootRight, thirdNode);
                    });

                    it("new node left to root node", () -> {
                        Node leftLink = thirdNode.getLeft();

                        assertEquals(leftLink, heap.getRootNode());
                    });

                    it("new node right to second node", () -> {
                        Node rightLink = thirdNode.getRight();

                        assertEquals(rightLink, secondNode);
                    });

                    it("second node left to new node", () -> {
                        Node leftLink = secondNode.getLeft();

                        assertEquals(thirdNode, leftLink);
                    });
                });
            });
        });
    }
    //END BDD CODE
}
