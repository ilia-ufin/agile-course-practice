package ru.unn.agile.shapevolume.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.unn.agile.shapevolume.model.TestConstants.EPSILON;

public class RegularPolygonPrismTest {

    @Test
    public void whenZeroHeightThenZeroVolume() {
        RegularPolygonPrism prism = new RegularPolygonPrism(3, 2.0, 0.0);

        double volume = prism.getVolume();

        assertEquals(0.0, volume, EPSILON);
    }

    @Test
    public void whenEquilateralTriangleBaseThenCalculateVolume() {
        RegularPolygonPrism prism = new RegularPolygonPrism(3, 2.0, 5.0);

        double volume = prism.getVolume();

        assertEquals(8.660254, volume, EPSILON);
    }
    @Test
    public void whenHexagonBaseThenCalculateVolume() {
        RegularPolygonPrism prism = new RegularPolygonPrism(6, 1.0, 2.0);

        double volume = prism.getVolume();

        assertEquals(5.196152, volume, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeHeightThenThrows() {
        new RegularPolygonPrism(3, 1.0, -1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLessThanThreeSidesThenThrows() {
        new RegularPolygonPrism(2, 1.0, 1.0);
    }
}
