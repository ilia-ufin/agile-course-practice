package ru.unn.agile.lengthconverter.model;

public enum LengthUnit {
    METERS(1),
    MILLIMETERS(1e-3),
    KILOMETERS(1e3),
    FEET(0.3048);

    private final double unitToMeter;
    LengthUnit(final double unitToMeter) {
        this.unitToMeter = unitToMeter;
    }
    public double getUnitToMeter() {
        return unitToMeter;
    }
}
