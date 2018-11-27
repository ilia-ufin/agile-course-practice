package ru.unn.agile.salarycalculator.model;

import java.time.LocalDate;

public class WorkWithCalendar {
    private static final int DEFAULT_VACATION_YEAR = 2010;
    private int lengthOfVacation;
    private LocalDate startVacation;
    private LocalDate countMonth;

    public WorkWithCalendar() {
        this.lengthOfVacation = 0;
        this.startVacation = LocalDate.of(DEFAULT_VACATION_YEAR, 1, 1);
        this.countMonth = LocalDate.now();
    }

    public int countJobDaysInMonth() {
        SubtractHolidays checkingPeriod = new SubtractHolidays();

        checkingPeriod.setCheckMonth(countMonth);

        int jobDaysInMonth = 0;
        for (int i = 1; i <= countMonth.lengthOfMonth(); i++) {
            if (checkingPeriod.isNotDayOff(i)) {
                jobDaysInMonth++;
            }
        }
        return jobDaysInMonth;
    }

    public int countCashDaysInMonth() {
        SubtractHolidays checkingPeriod = new SubtractHolidays();

        checkingPeriod.setCheckMonth(countMonth);
        checkingPeriod.setStartVacation(startVacation);
        checkingPeriod.setLengthVacation(lengthOfVacation);

        int cashDaysInMonth = countJobDaysInMonth();
        if (!isCountYearNotVacationYear()) {
            return cashDaysInMonth - checkingPeriod.getHolidaysInVacation();
        }
        return cashDaysInMonth;
    }

    public WorkWithCalendar setStartVacation(final LocalDate inStartVacation) {
        startVacation = inStartVacation;
        return this;
    }

    public WorkWithCalendar setCountMonth(final LocalDate inCountMonth) {
        countMonth = inCountMonth;
        return this;
    }

    public WorkWithCalendar setLengthOfVacation(final int inLengthOfVacation) {
        lengthOfVacation = inLengthOfVacation;
        return this;
    }

    private boolean isCountYearNotVacationYear() {
        return countMonth.getYear() != startVacation.getYear();
    }
}
