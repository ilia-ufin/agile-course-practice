package ru.unn.agile.lengthconverter.model;

public final class LengthConverte {
    private LengthConverte() {
    }
    public static double convert(final LengthUnit sourceUnit,
                                 final double value,
                                 final LengthUnit targetUnit) {
        if (value < 0) {
            throw new LengthConverterExceptions("Value is negative");
        } else {
            return value * sourceUnit.getUnitToMeter() / targetUnit.getUnitToMeter();
        }
    }
}
