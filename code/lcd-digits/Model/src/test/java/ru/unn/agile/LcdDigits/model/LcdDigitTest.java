package ru.unn.agile.LcdDigits.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LcdDigitTest {

    @Test
    public void canCreateLcdDigit() {
        LcdDigit number = new LcdDigit(0);

        assertNotNull(number);
    }

    @Test
    public void canSetNumber() {
        LcdDigit number = new LcdDigit(12);

        number.setNumber(24);

        assertEquals(new LcdDigit(24), number);
    }

    @Test
    public void canGetInitialNumber() {
        LcdDigit number = new LcdDigit(56);

        assertEquals(56, number.getNumber());
    }

    @Test
    public void canGetLcdDigitForThree() {
        LcdDigit number = new LcdDigit(3);

        assertEquals("._. \n._| \n._| \n", number.getLcd());
    }

    @Test
    public void canGetTwoLcdDigits() {
        LcdDigit number = new LcdDigit(78);

        assertEquals("._. ._. \n..| |_| \n..| |_| \n", number.getLcd());
    }

    @Test
    public void canGetThreeLcdDigits() {
        LcdDigit number = new LcdDigit(246);

        assertEquals("._. ... ._. \n._| |_| |_. \n|_. ..| |_| \n", number.getLcd());
    }

    @Test
    public void canGetFourLcdDigits() {
        LcdDigit number = new LcdDigit(1095);

        assertEquals("... ._. ._. ._. \n..| |.| |_| |_. \n..| |_| ..| ._| \n", number.getLcd());
    }

    @Test
    public void canGetLongLcdDigitNumber() {
        LcdDigit number = new LcdDigit(1234567890);

        assertEquals(
                "... ._. ._. ... ._. ._. ._. ._. ._. ._. \n"
                        + "..| ._| ._| |_| |_. |_. ..| |_| |_| |.| \n"
                        + "..| |_. ._| ..| ._| |_| ..| |_| ..| |_| \n", number.getLcd());
    }
}
