package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class SalaryCalculatorTests {
    private final double delta = 0.01;

    private static void setInputData(final SalaryCalculator calculator) {
        calculator.setSalary(18000);
        calculator.setCountingMonth(LocalDate.of(2018, Month.OCTOBER, 1));
    }

    @Test
    public void checkCalculateSalaryConstructor() {
        SalaryCalculator calculator = new SalaryCalculator();

        LocalDate date = LocalDate.of(2010, 1, 1);

        assertEquals(0, calculator.getSalary(), delta);
        assertEquals(0, calculator.getWorkedHourInMonth());
        assertEquals(0, calculator.getLengthOfVacation());
        assertEquals(date, calculator.getStartOfVacation());

        date = LocalDate.now();

        assertEquals(date, calculator.getCountingMonth());
    }

    @Test
    public void canSetSalary() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);

        assertEquals(18000, calculator.getSalary(), delta);
    }

    @Test (expected = NumberFormatException.class)
    public void negativeSalary() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setSalary(-1000);
    }

    @Test
    public void canSetWoredHourInMonth() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setWorkedHourInMonth(100);

        assertEquals(100, calculator.getWorkedHourInMonth());
    }

    @Test (expected = NumberFormatException.class)
    public void  negativeWoredHourInMonth() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setWorkedHourInMonth(-100);
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetWorkedHourInMonthMoreMax() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setWorkedHourInMonth(600);
    }

    @Test
    public void canSetLenthOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setLengthOfVacation(1000);

        assertEquals(1000, calculator.getLengthOfVacation());
    }

    @Test (expected = NumberFormatException.class)
    public void negativeLenthOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setLengthOfVacation(-100);
    }

    @Test (expected = NumberFormatException.class)
    public void canCalculateWithNegativeSalary() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setSalary(-10000);

        assertEquals(0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test (expected = NumberFormatException.class)
    public void negativeWorkedHours() {
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setWorkedHourInMonth(-180);

        assertEquals(0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test (expected = NumberFormatException.class)
    public void onlyNegativeWorkedHours() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.APRIL, 1));
        calculator.setWorkedHourInMonth(-200);

        assertEquals(0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInNormalMonth() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(180);

        assertEquals(15319.57, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInNormalMonthWithOvertime() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(200);

        assertEquals(18383.48, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInNormalMonthWithLessHours() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(154);

        assertEquals(13106.74, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWith14DaysOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(104);
        calculator.setStartOfVacation(LocalDate.of(2018, Month.OCTOBER, 6));
        calculator.setLengthOfVacation(14);

        assertEquals(8851.31, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWithStartOfVacationAndFinishInAnother() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(144);
        calculator.setStartOfVacation(LocalDate.of(2018, Month.OCTOBER, 27));
        calculator.setLengthOfVacation(14);

        assertEquals(12255.65, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWithEndOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        calculator.setWorkedHourInMonth(120);
        calculator.setStartOfVacation(LocalDate.of(2018, Month.OCTOBER, 27));
        calculator.setLengthOfVacation(14);

        assertEquals(10677.27, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWhichAllInVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setWorkedHourInMonth(0);
        calculator.setStartOfVacation(LocalDate.of(2019, Month.SEPTEMBER, 27));
        calculator.setLengthOfVacation(50);

        assertEquals(0.0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWhichInYearNotEqualYearOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        calculator.setWorkedHourInMonth(160);
        calculator.setStartOfVacation(LocalDate.of(2019, Month.OCTOBER, 27));
        calculator.setLengthOfVacation(14);

        assertEquals(14236.36, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWithOvertimeAndWithoutVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setInputData(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        calculator.setWorkedHourInMonth(160);

        assertEquals(14236.36, calculator.calculateSalaryWithNDS(), delta);
    }
}
