package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class WorkWithCalendarTests {
    @Test
    public void checkJobDaysInSeptember() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.OCTOBER, 1));

        assertEquals(23, countMonth.countJobDaysInMonth());
    }

    @Test
    public void checkJobDaysInAugust() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.AUGUST, 1));

        assertEquals(23, countMonth.countJobDaysInMonth());
    }

    @Test
    public void checkJobDaysMonthWithoutVacation() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.MARCH, 1));

        assertEquals(22, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysInMonthWith14VacationDays() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setLengthOfVacation(14);

        assertEquals(13, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysInMonthWith7VacationDays() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.SEPTEMBER, 8));
        countMonth.setLengthOfVacation(7);

        assertEquals(15, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysInMonthWithEndOfVacationInAnotherMonth() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.AUGUST, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.AUGUST, 25));
        countMonth.setLengthOfVacation(14);

        assertEquals(18, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysInMonthWithEndOfVacationInMiddleOfMonth() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        countMonth.setStartVacation(LocalDate.of(2018, Month.AUGUST, 25));
        countMonth.setLengthOfVacation(10);

        assertEquals(19, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysInMonthWhenVacationInAnotherYear() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        countMonth.setStartVacation(LocalDate.of(2019, Month.AUGUST, 25));
        countMonth.setLengthOfVacation(14);

        assertEquals(20, countMonth.countCashDaysInMonth());
    }

    @Test
    public void checkSalaryDaysWhenAllCountMonthInVacation() {
        WorkWithCalendar countMonth = new WorkWithCalendar();

        countMonth.setCountMonth(LocalDate.of(2014, Month.OCTOBER, 1));
        countMonth.setStartVacation(LocalDate.of(2014, Month.SEPTEMBER, 25));
        countMonth.setLengthOfVacation(50);

        assertEquals(0, countMonth.countCashDaysInMonth());
    }
}

