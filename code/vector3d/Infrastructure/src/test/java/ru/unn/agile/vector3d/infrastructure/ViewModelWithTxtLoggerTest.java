package ru.unn.agile.vector3d.infrastructure;

import ru.unn.agile.vector3d.viewmodel.ViewModel;
import ru.unn.agile.vector3d.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
