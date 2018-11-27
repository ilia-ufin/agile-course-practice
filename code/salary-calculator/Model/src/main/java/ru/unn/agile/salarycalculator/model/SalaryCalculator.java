package ru.unn.agile.salarycalculator.model;

import java.time.LocalDate;

public class SalaryCalculator {
    private static final double NDS = 0.87;
    private static final int WORK_HOUR_IN_DAY = 8;
    private static final int DEFAULT_VACATION_YEAR = 2010;
    private static final int MAX_HOUR_IN_MONTH = 500;
    private static final double MAX_SALARY = 1000000000;

    private double salary;
    private  int workedHourInMonth;
    private int lengthOfVacation;
    private LocalDate startOfVacation;
    private LocalDate countingMonth;

    public SalaryCalculator() {
        this.salary = 0;
        this.workedHourInMonth = 0;
        this.lengthOfVacation = 0;
        this.startOfVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
        this.countingMonth = LocalDate.now();
    }

    private boolean isIncorrectInput() {
        return salary <= 0
                || lengthOfVacation < 0
                || workedHourInMonth < 0;
    }

    public void setSalary(final double inSalary) {
        if (inSalary < 0) {
            throw new NumberFormatException("Negative salary");
        }
        if (inSalary > MAX_SALARY) {
            throw new NumberFormatException("Not a valid value salary");
        }
        this.salary = inSalary;
    }

    public void setWorkedHourInMonth(final int inWorkedHourInMonth) {
        if (inWorkedHourInMonth < 0) {
            throw new NumberFormatException("Negative meaning");
        }
        if (inWorkedHourInMonth > MAX_HOUR_IN_MONTH) {
            throw new NumberFormatException("Maximum value exceeded");
        }
        this.workedHourInMonth = inWorkedHourInMonth;
    }

    public void setLengthOfVacation(final int inLengthOfVacation) {
        if (inLengthOfVacation < 0) {
            throw new NumberFormatException("Negative vacation length");
        }
        this.lengthOfVacation = inLengthOfVacation;
    }

    public void setStartOfVacation(final LocalDate inStartOfVacation) {
        this.startOfVacation = inStartOfVacation;
    }

    public void setCountingMonth(final LocalDate isCountingMonth) {
        this.countingMonth = isCountingMonth;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getWorkedHourInMonth() {
        return this.workedHourInMonth;
    }

    public int getLengthOfVacation() {
        return this.lengthOfVacation;
    }

    public LocalDate getStartOfVacation() {
        return  this.startOfVacation;
    }

    public LocalDate getCountingMonth() {
        return this.countingMonth;
    }

    public double calculateSalaryWithNDS() {
        return calculateSalaryWithoutNDS() * NDS;
    }

    private double calculateSalaryWithoutNDS() {
        if (isIncorrectInput()) {
            throw new ArithmeticException("Values must be not negatively");
        }
        if (isEmployeeWorkedMoreThanNormHoursInMonth()) {
            return calculateSalaryWithOvertime();
        }
        if (isEmployeeWorkedLessThanNormHoursInMonth()) {
            return calculateSalaryForLessHours();
        }
        return calculateSalaryForNormalHours();
    }


    private double calculateSalaryForLessHours() {
        return workedHourInMonth * calculateSalaryForOneJobHour();
    }

    private double calculateSalaryForNormalHours() {
        return getSummOfWorkedHours() * calculateSalaryForOneJobHour();
    }

    private double calculateSalaryWithOvertime() {
        return calculateSalaryForNormalHours() + calculateSalaryForOvertime();
    }

    private double calculateSalaryForOvertime() {
        return (calculateSalaryForOneJobHour() * 2) * (workedHourInMonth - getSummOfWorkedHours());
    }

    private double calculateSalaryForOneJobHour() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(countingMonth);

        int jobDaysInCountMonth = countMonth.countJobDaysInMonth();

        return salary / (summJobHours(jobDaysInCountMonth));
    }

    private int getSummOfWorkedHours() {
        WorkWithCalendar countMonth = new WorkWithCalendar()
                .setCountMonth(countingMonth)
                .setStartVacation(startOfVacation)
                .setLengthOfVacation(lengthOfVacation);

        int cashDaysInMonth = countMonth.countCashDaysInMonth();

        return summJobHours(cashDaysInMonth);
    }

    private int summJobHours(final int amountDays) {
        return amountDays * WORK_HOUR_IN_DAY;
    }

    private boolean isEmployeeWorkedLessThanNormHoursInMonth() {
        return workedHourInMonth < getSummOfWorkedHours();
    }

    private boolean isEmployeeWorkedMoreThanNormHoursInMonth() {
        return workedHourInMonth > getSummOfWorkedHours();
    }
}
