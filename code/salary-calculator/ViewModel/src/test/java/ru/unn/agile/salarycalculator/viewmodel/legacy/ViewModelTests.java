package ru.unn.agile.salarycalculator.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import ru.unn.agile.salarycalculator.viewmodel.legacy.ViewModel.Status;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUpEmptyExample() {
        MyFakeLogger logger = new MyFakeLogger();
        viewModel = new ViewModel(logger);
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("154");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
    }

    @After
    public void deleteExample() {
        viewModel = null;
    }

    @Test
    public void checkStatusInBegin() {
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
    }

    @Test
    public void checkSetters() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("145");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");

        assertEquals("10000", viewModel.getSalary());
        assertEquals("145", viewModel.getWorkedHours());
        assertEquals("5", viewModel.getCountMonth());
        assertEquals("2000", viewModel.getCountYear());
    }

    @Test
    public void checkStatusWhenReadyCalculate() {
        viewModel.checkCountFields();

        assertEquals(Status.READY_CALCULATE, viewModel.getStatus());
    }

    @Test
    public void checkStatusCash() {
        viewModel.checkCountFields();
        viewModel.calculateSalary();

        assertEquals(Status.CASH, viewModel.getStatus());
    }

    @Test
    public void isLengthCharactersSalaryNotCorrect() {
        viewModel.setSalary("1000000000000000000000000");
        viewModel.checkCountFields();

        assertEquals(Status.BAD_SALARY_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenOneOfCountFieldEmpty() {
        viewModel.setCountYear("");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithChar() {
        viewModel.setWorkedHours("a");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_WORKED_HOURS_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectMonth() {
        viewModel.setCountMonth("50");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectYear() {
        viewModel.setCountYear("19191");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNormalParameters() {
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("7281.52", viewModel.getResult());
    }

    @Test
    public void checkThatButtonEnabled() {
        viewModel.checkCountFields();

        assertTrue(viewModel.isCalculateButtonEnable());
    }

    @Test
    public void checkResultWithOvertime() {
        viewModel.setWorkedHours("200");
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("10213.04", viewModel.getResult());
    }

    @Test
    public void checkResultWithLessTime() {
        viewModel.setWorkedHours("10");
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("472.83", viewModel.getResult());
    }


    @Test
    public void checkResultWithNegativeWorkedHours() {
        viewModel.setWorkedHours("-144");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_WORKED_HOURS_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeSalary() {
        viewModel.setSalary("-10000");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_SALARY_FORMAT_SIGN, viewModel.getStatus());
    }

    @Test
    public void checkResultWithIncorrectMonthCount() {
        viewModel.setCountMonth("notNum");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkResultSalaryNotNumber() {
        viewModel.setSalary("abc");
        viewModel.checkCountFields();

        assertEquals(Status.BAD_COUNT_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusAndButtonWhenIncorrectDate() {
        viewModel.setCountMonth("35");
        viewModel.checkCountFields();

        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        MyFakeLogger logger = new MyFakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isCalculatePuttingSomething() {
        viewModel.calculateSalary();

        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.calculateSalary();
        String message = viewModel.getLog().get(0);

        assertEquals(message, ViewModel.LogMessages.CALCULATE_WAS_PRESSED + "Arguments"
                + ": salary = " + viewModel.getSalary()
                + "; workedHours = " + viewModel.getWorkedHours()
                + "; countMonth = " + viewModel.getCountMonth()
                + "; countYear = " + viewModel.getCountYear() + ".");
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.checkCountFields();
        viewModel.calculateSalary();
        viewModel.calculateSalary();
        viewModel.calculateSalary();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setSalary("1000");
        viewModel.focusLost();
        String message = viewModel.getLog().get(0);

        assertEquals(message, getLogTemplate());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        viewModel.setCountYear("1000");
        viewModel.setCountYear("1000");

        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertEquals(message, getLogTemplate());
    }

    @Test
    public void doNotLogSalaryParametersTwiceWithPartialInput() {
        viewModel.setSalary("1000");
        viewModel.setSalary("1000");

        viewModel.focusLost();

        viewModel.checkCountFields();
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogCountMonthParametersTwiceWithPartialInput() {
        viewModel.setCountMonth("12");
        viewModel.setCountMonth("12");
        viewModel.setCountMonth("12");

        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.checkCountFields();
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogCountWorkHoursParametersTwiceWithPartialInput() {
        viewModel.setWorkedHours("40");
        viewModel.setWorkedHours("40");
        viewModel.focusLost();
        viewModel.checkCountFields();

        assertEquals(1, viewModel.getLog().size());
    }

    public String getLogTemplate() {
        return ViewModel.LogMessages.EDITING_FINISHED
                + "Input arguments are: ["
                + viewModel.getSalary() + "; "
                + viewModel.getWorkedHours() + "; "
                + viewModel.getCountMonth() + "; "
                + viewModel.getCountYear() + "]";
    }
}
