package ru.unn.agile.ConverterTemperatures.model;

public final class TemperaturesConverter {
    private static final double ABS_MIN_TEMP = -273.15;

    private TemperaturesConverter() {
    }
    public static double convert(final double value, final TemperaturesUnit tempUnit) {
        if (value < ABS_MIN_TEMP) {
            throw new TemperaturesConverterExceptions("Value less more abs min temperature");
        } else {
            return tempUnit.getCoef() * value + tempUnit.getOffset();
        }
    }
}
