package ru.unn.agile.quadraticequation.viewmodel;

import ru.unn.agile.quadraticequation.model.QuadraticEquation;
import ru.unn.agile.quadraticequation.model.QuadraticEquationSolverException;
import javafx.beans.property.*;

import java.util.Arrays;
import java.util.List;

public class ViewModel {
    public static final String NO_QUADRATIC_COEFFICIENT_ERR = "Enter quadratic coefficient";
    public static final String NON_NUMERIC_COEFFICIENTS_ERR = "Coefficients must be numeric";
    public static final String EMPTY_COEFFICIENTS_ERR = "Coefficients must be not empty";
    public static final String NO_ROOTS_MESSAGE = "No roots";

    public static final String SOLVE_WAS_PRESSED = "Solved. ";

    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty roots = new SimpleStringProperty();

    private ILogger logger;

    public StringProperty logsProperty() {
        return logs;
    }

    public String getLogs() {
        return logs.get();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        init();
    }

    private void init() {
        a.set("1");
        b.set("0");
        c.set("0");
        logs.set("");
        roots.set("");
    }

    public ViewModel(final ILogger logger) {
        init();
        setLogger(logger);
    }

    public String getA() {
        return aProperty().get();
    }

    public String getB() {
        return bProperty().get();
    }

    public String getC() {
        return cProperty().get();
    }

    public String getRoots() {
        return rootsProperty().get();
    }

    public void setA(final String value) {
        aProperty().set(value);
    }

    public void setB(final String value) {
        bProperty().set(value);
    }

    public void setC(final String value) {
        cProperty().set(value);
    }

    public void solve() {
        QuadraticEquation quadraticEquation;
        if (getA().isEmpty() || getB().isEmpty() || getC().isEmpty()) {
            roots.set(EMPTY_COEFFICIENTS_ERR);
            logger.log(EMPTY_COEFFICIENTS_ERR);
            updateLogs();
            return;
        }
        try {
            quadraticEquation = new QuadraticEquation(
                    Double.parseDouble(getA()),
                    Double.parseDouble(getB()),
                    Double.parseDouble(getC()));
        } catch (NumberFormatException e) {
            roots.set(NON_NUMERIC_COEFFICIENTS_ERR);
            logger.log(NON_NUMERIC_COEFFICIENTS_ERR);
            updateLogs();
            return;
        } catch (IllegalArgumentException e) {
            roots.set(NO_QUADRATIC_COEFFICIENT_ERR);
            logger.log(NO_QUADRATIC_COEFFICIENT_ERR);
            updateLogs();
            return;
        }
        double[] equationRoots;
        try {
            equationRoots = quadraticEquation.solve();
        } catch (QuadraticEquationSolverException e) {
            roots.set(NO_ROOTS_MESSAGE);
            logger.log(NO_ROOTS_MESSAGE);
            updateLogs();
            return;
        }
        roots.set(Arrays.toString(equationRoots));

        StringBuilder message = new StringBuilder(SOLVE_WAS_PRESSED);
        message.append("Arg: a = ").append(getA())
                .append("; b = ").append(getB())
                .append("; c = ").append(getC())
                .append("; roots = ").append(roots.get());
        logger.log(message.toString());
        updateLogs();
    }

    public StringProperty aProperty() {
        return a;
    }

    public StringProperty bProperty() {
        return b;
    }

    public StringProperty cProperty() {
        return c;
    }

    public StringProperty rootsProperty() {
        return roots;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private void updateLogs() {
        StringBuilder logMessages = new StringBuilder();
        List<String> fullLog = getLog();
        for (String log : fullLog) {
            logMessages.append(log).append("\n");
        }
        logs.set(logMessages.toString());
    }
}
