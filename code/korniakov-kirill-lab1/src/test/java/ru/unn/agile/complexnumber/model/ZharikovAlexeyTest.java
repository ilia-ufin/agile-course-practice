package ru.unn.agile.complexnumber.model;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZharikovAlexeyTest {

    @Test
    public void canNullArgsComplexNumbers() {
        ComplexNumber z = new ComplexNumber(0, 0);

        assertNotNull(z);
    }

    @Test
    public void canCompareComplexNumbers() {
        ComplexNumber cn1 = new ComplexNumber(5, 10);
        ComplexNumber cn2 = new ComplexNumber(5, 10);

        assertTrue(cn1.equals(cn2));
    }

    @Test
    public void canAddComplexNumbers() {
        ComplexNumber cn1 = new ComplexNumber(5, 5);
        ComplexNumber cn2 = new ComplexNumber(5, 5);

        ComplexNumber cn = cn1.add(cn2);

        assertEquals(new ComplexNumber(10, 10), cn);
    }
}
