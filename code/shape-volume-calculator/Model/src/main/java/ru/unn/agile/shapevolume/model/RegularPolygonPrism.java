package ru.unn.agile.shapevolume.model;

import static ru.unn.agile.shapevolume.model.ShapeVolumeConstants.ERROR_SIZE_MUST_BE_NOT_NEGATIVE;

public class RegularPolygonPrism {
    private static final String ERROR_INCORRECT_BASE_SIDES_COUNT
            = "Base polygon must have 3 or more sides";

    private static final int POLYGON_MINIMUM_SIDES_COUNT = 3;
    private static final int REGULAR_POLYGON_AREA_COEFF = 4;

    private final int sidesCount;
    private final double sideLength;
    private final double height;

    public RegularPolygonPrism(final int baseSidesCount,
                               final double baseSideLength,
                               final double height) {
        if (baseSideLength < 0 || height < 0) {
            throw new IllegalArgumentException(ERROR_SIZE_MUST_BE_NOT_NEGATIVE);
        }
        if (baseSidesCount < POLYGON_MINIMUM_SIDES_COUNT) {
            throw new IllegalArgumentException(ERROR_INCORRECT_BASE_SIDES_COUNT);
        }
        this.sidesCount = baseSidesCount;
        this.sideLength = baseSideLength;
        this.height = height;
    }

    public double getVolume() {
        double baseArea = sidesCount * sideLength * sideLength
                * Math.cos(Math.PI / sidesCount) / Math.sin(Math.PI / sidesCount)
                / REGULAR_POLYGON_AREA_COEFF;
        return baseArea * height;
    }
}
