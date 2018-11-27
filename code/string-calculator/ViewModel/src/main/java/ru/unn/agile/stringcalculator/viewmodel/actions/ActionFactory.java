package ru.unn.agile.stringcalculator.viewmodel.actions;

import ru.unn.agile.stringcalculator.viewmodel.Operation;
import ru.unn.agile.stringcalculator.viewmodel.actions.abstraction.IAction;
import ru.unn.agile.stringcalculator.viewmodel.actions.impl.AddAction;

public final class ActionFactory {
    private ActionFactory() { }

    public static IAction create(final Operation operation) {
        return operation.equals(Operation.ADD) ? new AddAction() : null;
    }
}
