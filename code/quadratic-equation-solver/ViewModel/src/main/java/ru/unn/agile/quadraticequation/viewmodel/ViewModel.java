package ru.unn.agile.quadraticequation.viewmodel;

import ru.unn.agile.quadraticequation.model.QuadraticEquation;
import ru.unn.agile.quadraticequation.model.QuadraticEquationSolverException;
import javafx.beans.property.*;

import java.util.Arrays;

public class ViewModel {
    public static final String NO_QUADRATIC_COEFFICIENT_ERR = "Enter quadratic coefficient";
    public static final String NON_NUMERIC_COEFFICIENTS_ERR = "Coefficients must be numeric";
    public static final String NO_ROOTS_MESSAGE = "No roots";

    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();

    private final StringProperty roots = new SimpleStringProperty();

    public ViewModel() {
        a.set("0");
        b.set("0");
        c.set("0");
        roots.set("");
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

        try {
            quadraticEquation = new QuadraticEquation(
                    Double.parseDouble(a.get()),
                    Double.parseDouble(b.get()),
                    Double.parseDouble(c.get()));
        } catch (NumberFormatException e) {
            roots.set(NON_NUMERIC_COEFFICIENTS_ERR);
            return;
        } catch (IllegalArgumentException e) {
            roots.set(NO_QUADRATIC_COEFFICIENT_ERR);
            return;
        }
        double[] equationRoots;
        try {
            equationRoots = quadraticEquation.solve();
        } catch (QuadraticEquationSolverException e) {
            roots.set(NO_ROOTS_MESSAGE);
            return;
        }

        roots.set(Arrays.toString(equationRoots));
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
}
