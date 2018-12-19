package ru.unn.agile.fibonacciHeap.model;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertEquals;

@RunWith(OleasterRunner.class)
public class FibonacciHeapTestRegression {
    private FibonacciHeap heap;
    private double delta = 0.00001;
    {
        //BEGIN BDD CODE
        describe("Regression cases", () -> {
            beforeEach(() -> {
                heap = new FibonacciHeap();
            });

            it("should pop the minimum item from a heap", () -> {
                heap.add(2);
                heap.add(1);
                assertEquals(heap.pop().getValue(), 1, delta);
                assertEquals(heap.pop().getValue(), 2, delta);
            });

            it("should pop the minimum item from a heap with negate value", () -> {
                heap.add(-2);
                heap.add(1);
                assertEquals(heap.pop().getValue(), -2, delta);
                assertEquals(heap.pop().getValue(), 1, delta);
            });
        });
    }
    //END BDD CODE
}
