package ru.unn.agile.vector3d.viewmodel.validators.impl;

import ru.unn.agile.vector3d.viewmodel.ViewModel;
import ru.unn.agile.vector3d.viewmodel.validators.IValidator;

public class VectorValidator implements IValidator {
    @Override
    public void validate(final ViewModel viewModel) {
        String x = viewModel.getVectorX();
        String y = viewModel.getVectorY();
        String z = viewModel.getVectorZ();

        ViewModel.Status status = validateVector(x, y, z);

        viewModel.statusProperty().set(status.toString());
    }

    protected ViewModel.Status validateVector(final String x, final String y, final String z) {
        if (x.isEmpty() || y.isEmpty() || z.isEmpty()) {
            return ViewModel.Status.WAITING;
        }

        try {
            Double.parseDouble(x);
            Double.parseDouble(y);
            Double.parseDouble(z);
        } catch (NumberFormatException e) {
            return ViewModel.Status.BAD_FORMAT;
        }

        return ViewModel.Status.READY;
    }
}
