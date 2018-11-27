package ru.unn.agile.polynomial.viewmodel;

import javafx.util.Pair;
import ru.unn.agile.polynomial.model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class PolynomialParser {
    private boolean degreeDetected = false;
    private boolean xDetected = false;
    private boolean symbolPlusDetected = false;
    private boolean symbolMinusDetected = false;
    private boolean pointDetected = false;
    private List<Pair<Integer, Double>> array = new ArrayList<>();
    private String digitStr = "";
    private String degreeStr = "0";
    public static final String FORMAT_ERROR = "Неверный формат полинома ";
    private String polynomialStrSource;

    PolynomialParser(final String polynomialStr) {
        polynomialStrSource = polynomialStr;
    }

    private void convertX(final int index) {
        if (pointDetected || degreeDetected || xDetected) {
            throw new ViewModelException(FORMAT_ERROR);
        }

        if (symbolMinusDetected) {
            digitStr = "-1";
        } else if (symbolPlusDetected || index == 0) {
            digitStr = "1";
        }

        degreeDetected = false;
        symbolMinusDetected = false;
        symbolPlusDetected = false;
        xDetected = true;
        degreeStr = "1";
    }

    private void convertDegree() {
        if (xDetected) {
            degreeStr = "";
            degreeDetected = true;
            xDetected = false;
        } else {
            throw new ViewModelException(FORMAT_ERROR);
        }
    }

    private void convertPlusMinus(final char symbol, final int index) {
        if (symbolMinusDetected || symbolPlusDetected
                || pointDetected || degreeDetected) {
            throw new ViewModelException(FORMAT_ERROR);
        }
        if (index != 0) {
            array.add(new Pair<>(Integer.valueOf(degreeStr), Double.valueOf(digitStr)));
        }

        if (symbol == '-') {
            symbolMinusDetected = true;
            digitStr = "-";
        } else {
            symbolPlusDetected = true;
            digitStr = "";
        }

        degreeStr = "0";
        xDetected = false;
        degreeDetected = false;
    }

    private void convertPointOrDigit(final char symbol) {
        if (!Character.isDigit(symbol)) {
            if (xDetected || symbolMinusDetected || symbolPlusDetected) {
                throw new ViewModelException(FORMAT_ERROR);
            }
        } else {
            if (xDetected) {
                throw new ViewModelException(FORMAT_ERROR);
            }
        }

        if (degreeDetected) {
            degreeStr += symbol;
        } else {
            digitStr += symbol;
        }

        if (symbol == '.') {
            pointDetected = true;
        } else {
            pointDetected = false;
        }
        degreeDetected = false;
        xDetected = false;
        symbolMinusDetected = false;
        symbolPlusDetected = false;
    }

    public Polynomial parsePolynomial() {
        String polynomialStr = polynomialStrSource.replaceAll("\\s+", "");
        if (polynomialStr.isEmpty()) {
            throw new ViewModelException(FORMAT_ERROR);
        }

        for (int i = 0; i < polynomialStr.length(); i++) {
            if (polynomialStr.charAt(i) == 'x') {
                convertX(i);
            } else if (Character.isDigit(polynomialStr.charAt(i))
                    || polynomialStr.charAt(i) == '.') {
                convertPointOrDigit(polynomialStr.charAt(i));
            } else if (polynomialStr.charAt(i) == '-'
                    || polynomialStr.charAt(i) == '+') {
                convertPlusMinus(polynomialStr.charAt(i), i);
            } else if (polynomialStr.charAt(i) == '^') {
                convertDegree();
            } else {
                throw new ViewModelException(FORMAT_ERROR);
            }
        }

        array.add(new Pair<>(Integer.valueOf(degreeStr), Double.valueOf(digitStr)));
        double[] coeffs = new double[array.get(0).getKey() + 1];
        for (int i = 0; i < array.size(); i++) {
            coeffs[coeffs.length - 1 - array.get(i).getKey().intValue()]
                    += array.get(i).getValue().doubleValue();
        }
        return new Polynomial(coeffs);
    }
}
