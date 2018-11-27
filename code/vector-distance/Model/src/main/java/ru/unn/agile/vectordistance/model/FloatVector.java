package ru.unn.agile.vectordistance.model;

import java.util.Objects;

public class FloatVector {
    private float[] elements;

    public FloatVector(final float[] elements) {
        Objects.requireNonNull(elements, "Expected not null array.");
        this.elements = elements;
    }

    public float get(final int i) {
        return this.elements[i];
    }

    public int length() {
        return this.elements.length;
    }
}
