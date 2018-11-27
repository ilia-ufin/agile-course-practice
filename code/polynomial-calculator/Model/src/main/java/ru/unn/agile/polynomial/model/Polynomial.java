package ru.unn.agile.polynomial.model;

public class Polynomial {
    public static final double DELTA = 0.000001;

    private double[] coeffs;
    private int degree;

    public Polynomial() {
        this.coeffs = null;
        this.degree = -1;
    }

    public Polynomial(final double[] coef) {
        int firstElem = 0;
        while ((firstElem < coef.length) && (coef[firstElem] == 0)) {
            firstElem++;
        }
        if (firstElem == coef.length) {
            this.coeffs = new double[]{0.0};
            this.degree = 0;
            return;
        }

        int length = coef.length - firstElem;
        this.coeffs = new double[length];
        for (int i = length; i > 0; i--) {
            this.coeffs[length - i] = coef[firstElem + (length - i)];
        }

        this.degree = length - 1;
    }

    public Polynomial(final Polynomial p) {
        degree = p.degree;
        coeffs = p.coeffs;
    }

    public double[] getCoeffs() {
        return this.coeffs;
    }

    public int getDegree() {
        return this.degree;
    }

    public String toString() {
        if (degree == -1) {
            return "0";
        } else if (degree == 0) {
            return "" + coeffs[0];
        } else if (degree == 1) {
            String s = coeffs[0] + "x";
            if (coeffs[1] == 0.0) {
                return s;
            } else if (coeffs[1] > 0) {
                return s + " + " + coeffs[1];
            } else if (coeffs[1] < 0) {
                return s + " - " + (-coeffs[1]);
            }
        }

        String s = coeffs[0] + "x^" + degree;
        for (int i = 1; i <= degree; i++) {
            if (coeffs[i] == 0.0) {
                continue;
            } else if (coeffs[i] > 0) {
                s = s + " + " + (coeffs[i]);
            } else if (coeffs[i] < 0) {
                s = s + " - " + (-coeffs[i]);
            }
            if (i == degree - 1) {
                s = s + "x";
            } else if (i < degree - 1) {
                s = s + "x^" + (degree - i);
            }
        }
        return s;
    }

    public Polynomial add(final Polynomial p) {
        int maxDegree = Math.max(this.degree, p.degree);
        double[] resultCoeffs = new double[maxDegree + 1];

        int toStart = maxDegree - this.degree;
        for (int i = 0; i <= this.degree; i++) {
            resultCoeffs[toStart + i] = this.coeffs[i];
        }

        toStart = maxDegree - p.degree;
        for (int i = 0; i <= p.degree; i++) {
            resultCoeffs[toStart + i] += p.coeffs[i];
        }

        return new Polynomial(resultCoeffs);
    }

    public Polynomial add(final double d) {
        Polynomial res = new Polynomial(this);
        res.coeffs[this.degree] += d;
        return res;
    }

    public Polynomial multiply(final Polynomial p) {
        int resultDegree = this.degree + p.degree;
        double[] resultCoeffs = new double[resultDegree + 1];

        for (int i = 0; i <= this.degree; i++) {
            for (int j = 0; j <= p.degree; j++) {
                resultCoeffs[i + j] += this.coeffs[i] * p.coeffs[j];
            }
        }

        return new Polynomial(resultCoeffs);
    }

    public Polynomial multiply(final double d) {
        double[] resultCoeffs = new double[this.degree + 1];
        for (int i = 0; i <= this.degree; i++) {
            resultCoeffs[i] = this.coeffs[i] * d;
        }

        return new Polynomial(resultCoeffs);
    }

    public Polynomial subtract(final Polynomial p) {
        return new Polynomial(this.add(p.multiply(-1)));
    }

    public Polynomial subtract(final double d) {
        return new Polynomial(this.add(-d));
    }

    public boolean isEqual(final Polynomial p) {
        if (this.degree != p.degree) {
            return false;
        } else {
            for (int i = 0; i <= this.degree; i++) {
                if (this.coeffs[i] != p.coeffs[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}

