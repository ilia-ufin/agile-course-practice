package ru.unn.agile.vector3d.viewmodel.validators.impl;

import ru.unn.agile.vector3d.viewmodel.ViewModel;

public class BothVectorsValidator extends VectorValidator {
    @Override
    public void validate(final ViewModel viewModel) {
        super.validate(viewModel);

        if (!ViewModel.Status.READY.toString()
                .equals(viewModel.statusProperty().get())) {
            return;
        }

        String x = viewModel.getOtherVectorX();
        String y = viewModel.getOtherVectorY();
        String z = viewModel.getOtherVectorZ();

        ViewModel.Status status = validateVector(x, y, z);

        viewModel.statusProperty().set(status.toString());
    }
}
