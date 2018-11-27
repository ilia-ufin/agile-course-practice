package ru.unn.agile.vector3d.viewmodel.actions.impl;

import ru.unn.agile.vector3d.model.Vector3D;
import ru.unn.agile.vector3d.viewmodel.ViewModel;
import ru.unn.agile.vector3d.viewmodel.actions.IAction;

public class MagnitudeAction implements IAction {
    @Override
    public void execute(final ViewModel viewModel) {
        Vector3D vector = createVectorFromStrings(viewModel.getVectorX(),
                viewModel.getVectorY(), viewModel.getVectorZ());

        viewModel.resultProperty().set(String.valueOf(vector.magnitude()));
        viewModel.statusProperty().set(ViewModel.Status.SUCCESS.toString());
    }
}
