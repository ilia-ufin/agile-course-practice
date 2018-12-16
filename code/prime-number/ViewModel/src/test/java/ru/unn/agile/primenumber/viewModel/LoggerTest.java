package ru.unn.agile.primenumber.viewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.primenumber.infrastructure.InMemoryLogger;
import ru.unn.agile.primenumber.infrastructure.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoggerTest {

    private ViewModel viewModel;

    @Before
    public void initiateLogs() {
        LoggerFactory factory = mock(LoggerFactory.class);
        when(factory.getLogger()).thenReturn(new InMemoryLogger());
        viewModel = new ViewModel();
        viewModel.setLogger(factory);
    }

    @Test
    public void canInitiateViewModel() {
        assertEquals(LogMessage.APPLICATION_STARTED.toString(), viewModel.getLogs());
    }

    @Test
    public void canSetNullLoggerFactory() {
        try {
            viewModel.setLogger(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Logger Factory parameter can't be null", e.getMessage());
        }
    }

    @Test
    public void canLoggingRightProperty() {
        viewModel.setRightBound("a");

        viewModel.onFocusChanged(true, false);

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                        LogMessage.STATE_OF_OPERANDS_MESSAGE.toString(),
                        "", "a"),
                viewModel.getLogs());
    }

    @Test
    public void canLoggingLeftProperty() {
        viewModel.setLeftBound("1");

        viewModel.onFocusChanged(true, false);

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                            LogMessage.STATE_OF_OPERANDS_MESSAGE.toString(),
                            "1", ""),
                viewModel.getLogs());
    }

    @Test
    public void canLoggingSearch() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("4");

        viewModel.searchPrimeNumber();

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_STARTED_MESSAGE.toString(),
                        "1", "4") + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_FINISHED_MESSAGE.toString(),
                        "Success", "2, 3"),
                viewModel.getLogs());
    }

    @Test
    public void canLoggingE2E() {
        viewModel.setLeftBound("2");
        viewModel.setRightBound("5");
        viewModel.onFocusChanged(true, false);
        viewModel.searchPrimeNumber();

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                        LogMessage.STATE_OF_OPERANDS_MESSAGE.toString(),
                        "2", "5") + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_STARTED_MESSAGE.toString(),
                        "2", "5") + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_FINISHED_MESSAGE.toString(),
                        "Success", "2, 3, 5"),
                viewModel.getLogs());
    }

    @Test
    public void canLoggingE2EWithIncorrectNumbers() {
        viewModel.setLeftBound("2");
        viewModel.setRightBound("a");

        viewModel.onFocusChanged(true, false);
        viewModel.searchPrimeNumber();

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                                LogMessage.STATE_OF_OPERANDS_MESSAGE.toString(),
                                "2", "a") + "\n"
                        + String.format(
                                LogMessage.CALCULATION_IS_STARTED_MESSAGE.toString(),
                                "2", "a") + "\n"
                        + String.format(
                                LogMessage.CALCULATION_IS_FINISHED_MESSAGE.toString(),
                                "For input string: \"a\"", ""),
                viewModel.getLogs());
    }

    @Test
    public void canLogE2EWithIncorrectInterval() {
        viewModel.setLeftBound("4");
        viewModel.setRightBound("1");

        viewModel.onFocusChanged(true, false);
        viewModel.searchPrimeNumber();

        assertEquals(
                LogMessage.APPLICATION_STARTED + "\n"
                        + String.format(
                        LogMessage.STATE_OF_OPERANDS_MESSAGE.toString(),
                        "4", "1") + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_STARTED_MESSAGE.toString(),
                        "4", "1") + "\n"
                        + String.format(
                        LogMessage.CALCULATION_IS_FINISHED_MESSAGE.toString(),
                        "Last value must be more or equal to first value!", ""),
                viewModel.getLogs());
    }
}
