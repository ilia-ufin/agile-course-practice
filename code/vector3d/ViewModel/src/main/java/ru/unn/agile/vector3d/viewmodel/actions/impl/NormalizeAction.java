package ru.unn.agile.vector3d.viewmodel.actions.impl;

import ru.unn.agile.vector3d.model.Vector3D;
import ru.unn.agile.vector3d.viewmodel.ViewModel;
import ru.unn.agile.vector3d.viewmodel.actions.IAction;

public class NormalizeAction implements IAction {
    @Override
    public void execute(final ViewModel viewModel) {
        String x = viewModel.getVectorX();
        String y = viewModel.getVectorY();
        String z = viewModel.getVectorZ();

        Vector3D vector = createVectorFromStrings(x, y, z);

        viewModel.resultProperty().set(String.valueOf(vector.normalize()));
        viewModel.statusProperty().set(ViewModel.Status.SUCCESS.toString());
    }
}
