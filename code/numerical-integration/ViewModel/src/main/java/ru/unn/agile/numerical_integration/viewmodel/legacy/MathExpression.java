package ru.unn.agile.numerical_integration.ViewModel.legacy;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathExpression {
    public static final String ERROR_FAILED_TO_COMPUTE =
            "Failed to compute expression";
    public static final String ERROR_UNEXPECTED_SYMBOLS =
            "Unexpected symbols in expression";

    public static final String VARIABLE_NAME = "x";

    private String expression;
    private String error;
    private double result;
    private ScriptEngine engine;

    public MathExpression(final String expression) {
        this.expression = expression;
        checkExpression();
        if (hasError()) {
            return;
        }
        prepareExpression();

        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("js");
    }

    public boolean eval(final double variableValue) {
        if (hasError()) {
            return false;
        }

        try {
            engine.put(VARIABLE_NAME, variableValue);
            result = (double) engine.eval(expression);
        } catch (ScriptException e) {
            error = ERROR_FAILED_TO_COMPUTE;
        }

        return !hasError();
    }

    public String getError() {
        return error;
    }

    public double getResult() {
        return result;
    }

    private boolean hasError() {
        return !error.isEmpty();
    }

    private void checkExpression() {
        error = "";

        if (!expression.matches("[\\s\\d" + VARIABLE_NAME + "+\\-*/.()]*")) {
            error = ERROR_UNEXPECTED_SYMBOLS;
        }
    }

    private void prepareExpression() {
        // Remove all whitespaces to check if the expression is empty
        expression = expression.replace(" ", "");
        if (expression.isEmpty()) {
            // Insert 0.0 to empty expr to eval it to 0
            expression = "0.0";
        } else {
            // Insert 0.0 to non empty expr to cast the result to double
            expression = "0.0+" + expression;
        }
    }
}
