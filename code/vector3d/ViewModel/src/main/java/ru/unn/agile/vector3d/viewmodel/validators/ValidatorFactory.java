package ru.unn.agile.vector3d.viewmodel.validators;

import ru.unn.agile.vector3d.viewmodel.Operation;
import ru.unn.agile.vector3d.viewmodel.validators.impl.BothVectorsValidator;
import ru.unn.agile.vector3d.viewmodel.validators.impl.MultiplyCoeffValidator;
import ru.unn.agile.vector3d.viewmodel.validators.impl.VectorValidator;

public final class ValidatorFactory {
    private ValidatorFactory() {
    }

    public static IValidator getValidator(final Operation operation) {
        if (Operation.ADD.equals(operation)
                || Operation.SUBTRACT.equals(operation)
                || Operation.DOT.equals(operation)
                || Operation.CROSS.equals(operation)) {
            return new BothVectorsValidator();
        } else if (Operation.MULTIPLY.equals(operation)) {
            return new MultiplyCoeffValidator();
        } else if (Operation.MAGNITUDE.equals(operation)
                || Operation.NORMALIZE.equals(operation)) {
            return new VectorValidator();
        }

        return null;
    }
}
