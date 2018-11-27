package ru.unn.agile.fraction.model;

import java.util.Objects;

public class Fraction {
    private int numerator;
    private int denominator;

    private Fraction revert() {
        return (this.getNumerator() < 0)
                ? new Fraction(-this.getDenominator(), Math.abs(getNumerator()))
                : new Fraction(this.getDenominator(), this.getNumerator());
    }

    private int getGcd(final int firstVal, final int secondVal) {
        if (secondVal == 0) {
            return Math.abs(firstVal);
        }
        return getGcd(secondVal, firstVal % secondVal);
    }

    public Fraction(final int numerator, final int denominator) {
        this.numerator = numerator;
        if (denominator < 0) {
            throw new ArithmeticException("Denominator cannot be less than zero!");
        }
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero!");
        }
        this.denominator = denominator;
    }

    public void setNumerator(final int numerator) {
        this.numerator = numerator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(final int denominator) {
        this.denominator = denominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction multiply(final Fraction other) {
        int noGcdNum = getNumerator() * other.getNumerator();
        int noGcdDenom = getDenominator() * other.getDenominator();
        int gcd = getGcd(noGcdNum, noGcdDenom);

        return new Fraction(noGcdNum / gcd, noGcdDenom / gcd);
    }

    public Fraction divide(final Fraction other) {
        return this.multiply(other.revert());
    }

    public Fraction add(final Fraction other) {
        int noGcdNum =
                getNumerator() * other.getDenominator() + other.getNumerator() * getDenominator();
        int noGcdDenom = getDenominator() * other.getDenominator();
        int gcd = getGcd(noGcdNum, noGcdDenom);

        return new Fraction(noGcdNum / gcd, noGcdDenom / gcd);
    }

    public Fraction substract(final Fraction other) {
        int noGcdNum =
                getNumerator() * other.getDenominator() - other.getNumerator() * getDenominator();
        int noGcdDenom = getDenominator() * other.getDenominator();
        int gcd = getGcd(noGcdNum, noGcdDenom);

        return new Fraction(noGcdNum / gcd, noGcdDenom / gcd);
    }

    @Override
    public boolean equals(final Object object) {
        Fraction number = (Fraction) object;
        return number.getNumerator() == getNumerator()
                && number.getDenominator() == getDenominator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    public enum Operation {
        ADD(" + ") {
            public Fraction apply(final Fraction l, final Fraction r) {
                return l.add(r);
            }
        },
        SUBSTRACT(" - ") {
            public Fraction apply(final Fraction l, final Fraction r) {
                return l.substract(r);
            }
        },
        MULTIPLY(" * ") {
            public Fraction apply(final Fraction l, final Fraction r) {
                return l.multiply(r);
            }
        },
        DIVIDE(" / ") {
            public Fraction apply(final Fraction l, final Fraction r) {
                return l.divide(r);
            }
        };

        private final String name;

        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract Fraction apply(Fraction l, Fraction r);
    }
}
