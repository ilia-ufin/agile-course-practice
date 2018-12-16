package ru.unn.agile.matrix.model;

import ru.unn.agile.matrix.model.exceptions.*;

public class Matrix {
    private int rows;
    private int colls;
    private float[][] pMatrix;

    private void allocMatrix() {
        if (rows != 0 && colls != 0) {
            pMatrix = new float[rows][colls];
        }
    }

    public Matrix(final int rows, final int colls) {
        if (rows < 0 || colls < 0) {
            throw new IncorrectMatrixIndexException();
        }

        this.rows = rows;
        this.colls = colls;
        allocMatrix();
    }

    public Matrix() {
        this.rows = 1;
        this.colls = 1;
        allocMatrix();
    }

    public int getColls() {
        return colls;
    }

    public int getRows() {
        return rows;
    }

    private boolean isSameSizeMatrix(final Matrix m1) {
        return (rows != m1.rows || colls != m1.colls) ? false : true;
    }

    public void setVal(final int row, final int coll, final float val) {
        pMatrix[row][coll] = val;
    }

    public float getVal(final int row, final int coll) {
        return pMatrix[row][coll];
    }

    public String toString() {
        if (colls == 0 || rows == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int row = 0;  row < rows; row++) {
            builder.append('[');
            builder.append(pMatrix[row][0]);
            for (int col = 1;  col < colls; col++) {
                builder.append(" ").append(pMatrix[row][col]);
            }
            builder.append(']');
        }

        return builder.toString();
    }

    private Matrix matrixAddition(final Matrix m1, final Matrix m2, final boolean isPositive) {
        Matrix tmpMatrix = new Matrix(this.rows, m2.colls);
        int coef = -1;
        if (isPositive) {
            coef = 1;
        }
        for (int row = 0;  row < this.rows; row++) {
            for (int col = 0;  col < this.colls; col++) {
                tmpMatrix.setVal(row, col, m1.pMatrix[row][col] + coef * m2.pMatrix[row][col]);
            }
        }
        return tmpMatrix;
    }

    public Matrix add(final Matrix m2) {
        if (!isSameSizeMatrix(m2)) {
            throw new IncompatibleSizeMatrixException();
        }

        return matrixAddition(this, m2, true);
    }

    public Matrix subtract(final Matrix m2) {
        if (!isSameSizeMatrix(m2)) {
            throw new IncompatibleSizeMatrixException();
        }

        return matrixAddition(this, m2, false);
    }

    public Matrix multiply(final Matrix m2) {
        if (this.colls != m2.rows) {
            throw new IncompatibleSizeMatrixException();
        }

        Matrix tmpMatrix = new Matrix(this.rows, m2.colls);
        for (int row = 0;  row < this.rows; row++) {
            for (int col = 0;  col < m2.colls; col++) {
                int tmpVal = 0;
                for (int i = 0;  i < this.colls; i++) {
                    tmpVal += pMatrix[row][i] * m2.pMatrix[i][col];
                }
                tmpMatrix.setVal(row, col, tmpVal);
            }
        }
        return tmpMatrix;
    }

    private float determinant(final Matrix matrix, final int iterSize) {

        float det = 0;
        if (iterSize == 1) {
            return matrix.getVal(0, 0);
        } else if (iterSize == 2) {
            return matrix.getVal(0, 0) * matrix.getVal(1, 1)
                    - matrix.getVal(1, 0) * matrix.getVal(0, 1);
        } else {
            for (int j1 = 0; j1 < iterSize; j1++) {
                Matrix tmpMatrix = new Matrix(iterSize - 1, iterSize - 1);

                for (int i = 1; i < iterSize; i++) {
                    int j2 = 0;
                    for (int j = 0; j < iterSize; j++) {
                        if (j == j1) {
                            continue;
                        }
                        tmpMatrix.setVal(i - 1, j2, matrix.getVal(i, j));
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0 + j1 + 1.0) * matrix.getVal(0, j1)
                        * determinant(tmpMatrix, iterSize - 1);
            }
        }
        return det;
    }

    public float det() {
        if (colls != rows) {
            return Float.MAX_VALUE;
        }
        return determinant(this, colls);
    }

    public Matrix invert() {
        if (colls != rows || det() == 0.0) {
            throw new IncompatibleSizeMatrixException();
        }
        Matrix tmpMatrix = new Matrix(this.rows, this.colls);
        for (int row = 0;  row < this.rows; row++) {
            for (int col = 0;  col < this.colls; col++) {
                tmpMatrix.setVal(row, col, pMatrix[row][col]);
            }
        }

        return inverseMatrixJordanGauss(tmpMatrix);
    }

    private void jordanGaussReverseMove(final Matrix m1, final Matrix m2,
                              final int from, final int to, final int k) {
        for (int i = from; i < to; i++) {
            for (int j = 0; j < m1.getColls(); j++) {
                m2.pMatrix[i][j] = m2.pMatrix[i][j] - m2.pMatrix[k][j] * m1.pMatrix[i][k];
            }
            for (int j = m1.getColls() - 1; j >= k; j--) {
                m1.pMatrix[i][j] = m1.pMatrix[i][j] - m1.pMatrix[k][j] * m1.pMatrix[i][k];
            }
        }

    }

    private Matrix inverseMatrixJordanGauss(final Matrix m1) {
        int size = m1.rows;
        Matrix resultMatrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    resultMatrix.setVal(i, j, 1);
                } else {
                    resultMatrix.setVal(i, j, 0);
                }
            }
        }
        for (int k = 0; k < size; k++) {
            for (int j = k + 1; j < size; j++) {
                m1.pMatrix[k][j] = m1.pMatrix[k][j] / m1.pMatrix[k][k];
            }
            for (int j = 0; j < size; j++) {
                resultMatrix.setVal(k, j, resultMatrix.getVal(k, j) / m1.pMatrix[k][k]);
            }
            m1.pMatrix[k][k] = m1.pMatrix[k][k] / m1.pMatrix[k][k];
            if (k > 0) {
                jordanGaussReverseMove(m1, resultMatrix, 0, k, k);
            }
            jordanGaussReverseMove(m1, resultMatrix, k + 1, size, k);
        }
        return resultMatrix;
    }
}
