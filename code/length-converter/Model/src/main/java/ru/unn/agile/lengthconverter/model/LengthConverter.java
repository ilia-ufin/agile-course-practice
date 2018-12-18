package ru.unn.agile.lengthconverter.model;

public final class LengthConverter {
    private LengthConverter() {
    }
    public static double convert(final LengthUnit sourceUnit,
                                 final double value,
                                 final LengthUnit targetUnit) {
        return value * sourceUnit.getUnitToMeter() / targetUnit.getUnitToMeter();
    }
}
