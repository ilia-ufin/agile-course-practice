package ru.unn.agile.vector3d.viewmodel.actions;

import ru.unn.agile.vector3d.model.Vector3D;
import ru.unn.agile.vector3d.viewmodel.ViewModel;

public interface IAction {
    void execute(ViewModel viewModel);

    default Vector3D createVectorFromStrings(String x, String y, String z) {
        return new Vector3D(Double.parseDouble(x),
                Double.parseDouble(y),
                Double.parseDouble(z));
    }
}
