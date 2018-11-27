package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubtractHolidaysTests {
    @Test
    public void checkNotDayOff() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));

        assertTrue(countMonth.isNotDayOff(3));
    }

    @Test
    public void checkDayOff() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));

        assertFalse(countMonth.isNotDayOff(22));
    }

    @Test
    public void checkDayForSubtractionWithoutVacation() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.AUGUST, 1));

        assertEquals(0, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkDayForSubtractionWithSameVacationMonth() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.AUGUST, 4));
        countMonth.setLengthVacation(14);

        assertEquals(10, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkDayForSubtractionWithDifferentVacationMonth() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.JULY, 28));
        countMonth.setLengthVacation(14);

        assertEquals(8, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithStartInCountMonthButEndInAnother() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.AUGUST, 25));
        countMonth.setLengthVacation(14);

        assertEquals(5, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationInMiddleOfMonth() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.SEPTEMBER, 10));
        countMonth.setLengthVacation(14);

        assertEquals(10, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationEndInMiddleOfAnotherMonth() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.OCTOBER, 28));
        countMonth.setLengthVacation(14);

        assertEquals(7, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWithVacationStartInMiddleOfCountMonth() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.MARCH, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.MARCH, 21));
        countMonth.setLengthVacation(14);

        assertEquals(8, countMonth.getHolidaysInVacation());
    }

    @Test
    public void checkSubtractionDayWhenAllMonthInVacation() {
        SubtractHolidays countMonth = new SubtractHolidays();

        countMonth.setCheckMonth(LocalDate.of(2018, Month.JULY, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.JUNE, 19));
        countMonth.setLengthVacation(50);

        assertEquals(22, countMonth.getHolidaysInVacation());
    }

}
