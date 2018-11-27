package ru.unn.agile.vector3d.viewmodel.actions;

import ru.unn.agile.vector3d.viewmodel.Operation;
import ru.unn.agile.vector3d.viewmodel.actions.impl.*;

public final class ActionFactory {
    private ActionFactory() {
    }

    public static IAction getAction(final Operation operation) {
        if (Operation.ADD.equals(operation)) {
            return new AddAction();
        } else if (Operation.SUBTRACT.equals(operation)) {
            return new SubtractAction();
        } else if (Operation.DOT.equals(operation)) {
            return new DotAction();
        } else if (Operation.MULTIPLY.equals(operation)) {
            return new MultiplyAction();
        } else if (Operation.MAGNITUDE.equals(operation)) {
            return new MagnitudeAction();
        } else if (Operation.NORMALIZE.equals(operation)) {
            return new NormalizeAction();
        } else if (Operation.CROSS.equals(operation)) {
            return new CrossAction();
        }
        return null;
    }
}
