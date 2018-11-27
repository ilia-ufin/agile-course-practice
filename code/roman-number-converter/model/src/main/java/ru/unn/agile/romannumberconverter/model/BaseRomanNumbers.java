package ru.unn.agile.romannumberconverter.model;

public enum BaseRomanNumbers {
    I(1, "I"),
    IV(4, "IV"),
    V(5, "V"),
    IX(9, "IX"),
    X(10, "X"),
    XL(40, "XL"),
    L(50, "L"),
    XC(90, "XC"),
    C(100, "C"),
    CD(400, "CD"),
    D(500, "D"),
    CM(900, "CM"),
    M(1000, "M");

    private int arabicValue;
    private String romanValue;

    BaseRomanNumbers(final int arabicNumber, final String romanNumber) {
        this.arabicValue = arabicNumber;
        this.romanValue = romanNumber;
    }

    public int arabicValue() {
        return arabicValue;
    }
    public String romanValue() {
        return romanValue;
    }
}
