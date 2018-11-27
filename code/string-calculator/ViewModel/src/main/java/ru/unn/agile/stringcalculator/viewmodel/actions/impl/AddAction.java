package ru.unn.agile.stringcalculator.viewmodel.actions.impl;

import ru.unn.agile.stringcalculator.model.StringCalculator;
import ru.unn.agile.stringcalculator.viewmodel.actions.abstraction.IAction;

public class AddAction implements IAction {
    @Override
    public int calculate(final String inputData) {
        return StringCalculator.add(inputData);
    }
}
