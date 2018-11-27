package ru.unn.agile.quadraticequation.model;


import org.junit.Test;

import static org.junit.Assert.*;


public class QuadraticEquationTest {
    public static final double EPSILON = QuadraticEquation.EPSILON;

    @Test
    public void canGetQuadraticCoefficient() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double a = func.getA();

        assertEquals(5.1, a, EPSILON);
    }

    @Test
    public void canGetLinearCoefficient() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double b = func.getB();

        assertEquals(6.1, b, EPSILON);
    }

    @Test
    public void canGetFreeTerm() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double c = func.getC();

        assertEquals(7.1, c, EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateQuadraticEquationWithZeroACoeff() {
        QuadraticEquation func = new QuadraticEquation(0.0, 6.1, 7.1);
    }

    @Test (expected = QuadraticEquationSolverException.class)
    public void checkIfNoRealRoots() {
        QuadraticEquation func = new QuadraticEquation(1.1, 2.1, 3.1);

        func.solve();
    }

    @Test
    public void checkIfOneRoot() {
        QuadraticEquation func = new QuadraticEquation(1.0, -4.0, 4.0);

        double[] roots = func.solve();

        assertEquals((roots[0] == roots[1]), true);
        assertEquals(2.0, roots[0], EPSILON);
    }

    @Test
    public void checkTwoRoots() {
        QuadraticEquation func = new QuadraticEquation(1.0, 2.0, -48.0);

        double[] roots = func.solve();

        assertEquals(-8.0, roots[0], EPSILON);
        assertEquals(6.0, roots[1], EPSILON);
    }

    @Test
    public void checkRootsSequence() {
        QuadraticEquation func = new QuadraticEquation(-1.0, -2.0, 15.0);

        double[] roots = func.solve();

        assertEquals(-5.0, roots[0], EPSILON);
        assertEquals(3.0, roots[1], EPSILON);
    }

    @Test
    public void canSolveQuadraticCoefficientWithZeroBAndCCoeffs() {
        QuadraticEquation func = new QuadraticEquation(5.1, 0.0, 0.0);

        double[] roots = func.solve();

        assertEquals((roots[0] == roots[1]), true);
        assertEquals(0.0, roots[0], EPSILON);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotSolveQuadraticCoefficientWithZeroCoeffs() {
        QuadraticEquation func = new QuadraticEquation(0.0, 0.0, 0.0);

        double[] roots = func.solve();

        assertEquals((roots[0] == roots[1]), true);
        assertEquals(0.0, roots[0], EPSILON);
    }
}
