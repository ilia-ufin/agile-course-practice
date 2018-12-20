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

            describe("By default:", () -> {
                it("Create instance with length = 0", () -> {
                    assertEquals(heap.getRootAmount(), 0, delta);
                });

                it("Create instance with null rootNode", () -> {
                   assertNull(heap.getRootNode());
                });
            });
        });
    }
    //END BDD CODE
}
