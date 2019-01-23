package ru.unn.agile.ConverterTemperatures.model;

public enum  TemperaturesUnit {
    FAHRENHEIT(1.8, 32),
    KELVIN(1, 273.15),
    NEWTON(0.33, 0);

    private final double coef;
    private final double offset;
    TemperaturesUnit(final double coef, final double offset) {
        this.coef = coef;
        this.offset = offset;
    }
    public double getCoef() {
        return coef;
    }
    public double getOffset() {
        return offset;
    }
}
