package ru.unn.agile.matrix.model;

import org.junit.Test;
import ru.unn.agile.matrix.model.exceptions.*;

import static org.junit.Assert.*;

public class MatrixTest {
    private final double delta = 0.001;
    private void fillMatrix(final Matrix matrix) {
        int val = 0;
        for (int row = 0;  row < matrix.getRows(); row++) {
            for (int col = 0;  col < matrix.getColls(); col++) {
                matrix.setVal(row, col, val++);
            }
        }
    }
    @Test
    public void canCreateMatrix() {
        Matrix m1 = new Matrix();

        assertNotNull(m1);
    }
    @Test
    public void canCreateMatrixWithCollAndRowNumbers() {
        Matrix m1 = new Matrix(1, 2);

        assertEquals(m1.getRows(), 1);
        assertEquals(m1.getColls(), 2);
    }
    @Test (expected = IncorrectMatrixIndexException.class)
    public void cannotCreateMatrixWithNegativeCollAndRowNumbers() {
        Matrix m1 = new Matrix(-1, -2);
    }
    @Test
    public void canGetCollsAndRowsWithDefaultConstructor() {
        Matrix m1 = new Matrix();

        assertNotEquals(0, m1.getRows());
        assertNotEquals(0, m1.getColls());
    }
    @Test
    public void canSetAndGetElememt() {
        Matrix m1 = new Matrix(1, 1);

        m1.setVal(0, 0, 111);

        assertEquals(111.0, m1.getVal(0, 0), delta);
    }
    @Test
    public void printMatrixIntoString() {
        Matrix m1 = new Matrix();
        fillMatrix(m1);

        assertEquals("[0.0]", m1.toString());
    }
    @Test
    public void printMatrixIntoString1() {
        Matrix m1 = new Matrix(1, 2);
        fillMatrix(m1);

        assertEquals("[0.0 1.0]", m1.toString());
    }
    @Test
    public void printMatrixIntoString2() {
        Matrix m1 = new Matrix(2, 2);
        fillMatrix(m1);

        assertEquals("[0.0 1.0][2.0 3.0]", m1.toString());
    }
    @Test
    public void printEmptyMatrixIntoString() {
        Matrix m1 = new Matrix(0, 0);

        assertEquals("", m1.toString());
    }
    @Test
    public void additionUnitMatrix() {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m1.setVal(0, 0, 1);
        m2.setVal(0, 0, 1);

        Matrix m3 = m1.add(m2);

        assertEquals("[2.0]", m3.toString());
    }
    @Test
    public void additionUnitMatrix2() {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m1.setVal(0, 0, 2);
        m2.setVal(0, 0, 2);

        Matrix m3 = m1.add(m2);

        assertEquals("[4.0]", m3.toString());
    }
    @Test
    public void addition2x2Matrix() {
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.add(m2);

        assertEquals("[0.0 2.0][4.0 6.0]", m3.toString());
    }
    @Test (expected = IncompatibleSizeMatrixException.class)
    public void cannotAdditionDifferentSizeMatrix() {
        Matrix m1 = new Matrix(2, 3);
        Matrix m2 = new Matrix(3, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.add(m2);
    }
    @Test
    public void subtractUnitMatrix() {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m1.setVal(0, 0, 2);
        m2.setVal(0, 0, 1);

        Matrix m3 = m1.subtract(m2);

        assertEquals("[1.0]", m3.toString());
    }
    @Test
    public void canSubtract2x2Matrix() {
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.subtract(m2);

        assertEquals("[0.0 0.0][0.0 0.0]", m3.toString());
    }
    @Test
    public void canDoubleSubtractMatrix() {
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.subtract(m2).subtract(m2);

        assertEquals("[0.0 -1.0][-2.0 -3.0]", m3.toString());
    }
    @Test (expected = IncompatibleSizeMatrixException.class)
    public void cannotSubtractionDifferentSizeMatrix() {
        Matrix m1 = new Matrix(2, 3);
        Matrix m2 = new Matrix(3, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.subtract(m2);
    }
    @Test
    public void multiplyUnitMatrix() {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m1.setVal(0, 0, 2);
        m2.setVal(0, 0, 3);

        Matrix m3 = m1.multiply(m2);

        assertEquals("[6.0]", m3.toString());
    }
    @Test
    public void multiply2x2Matrix() {
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.multiply(m2);

        assertEquals("[2.0 3.0][6.0 11.0]", m3.toString());
    }
    @Test
    public void canMultiplyDifferentSizeMatrix() {
        Matrix m1 = new Matrix(2, 3);
        Matrix m2 = new Matrix(3, 4);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.multiply(m2);

        assertEquals("[20.0 23.0 26.0 29.0][56.0 68.0 80.0 92.0]", m3.toString());
    }
    @Test (expected = IncompatibleSizeMatrixException.class)
    public void cannotMultiplyWrongSizeMatrix() {
        Matrix m1 = new Matrix(2, 5);
        Matrix m2 = new Matrix(3, 4);
        fillMatrix(m1);
        fillMatrix(m2);

        Matrix m3 = m1.multiply(m2);
    }
    @Test
    public void getUnitMatrixDet() {
        Matrix m1 = new Matrix();
        m1.setVal(0, 0, 2);

        assertEquals(2.0, m1.det(), delta);
    }
    @Test
    public void get2x2MatrixDet() {
        Matrix m1 = new Matrix(2, 2);
        fillMatrix(m1);

        assertEquals(-2.0, m1.det(), delta);
    }
    @Test
    public void get3x3MatrixDet() {
        Matrix m1 = new Matrix(3, 3);
        fillMatrix(m1);
        m1.setVal(0, 0, 11);

        assertEquals(-33.0, m1.det(), delta);
    }
    @Test
    public void cannotGetNotSquareMatrixDet() {
        Matrix m1 = new Matrix(2, 3);
        fillMatrix(m1);

        assertEquals(Float.MAX_VALUE, m1.det(), delta);
    }
    @Test
    public void getNonInvertibleMatrixDet() {
        Matrix m1 = new Matrix(3, 3);
        fillMatrix(m1);

        assertEquals(0, m1.det(), delta);
    }
    @Test
    public void canInvertUnitMatrix() {
        Matrix m1 = new Matrix();
        m1.setVal(0, 0, 2);

        Matrix m2 = m1.invert();

        assertEquals("[0.5]", m2.toString());
    }
    @Test
    public void canInvert2x2Matrix() {
        Matrix m1 = new Matrix(2, 2);
        fillMatrix(m1);
        m1.setVal(0, 0, 1);
        Matrix m2 = m1.invert();

        assertEquals("[3.0 -1.0][-2.0 1.0]", m2.toString());
    }
    @Test (expected = IncompatibleSizeMatrixException.class)
    public void cannotInvertNotSquareMatrix() {
        Matrix m1 = new Matrix(2, 3);
        fillMatrix(m1);

        m1.invert();
    }
    @Test (expected = IncompatibleSizeMatrixException.class)
    public void cannotInvertMatrixWithZeroDet() {
        Matrix m1 = new Matrix(3, 3);
        fillMatrix(m1);

        m1.invert();
    }
}
