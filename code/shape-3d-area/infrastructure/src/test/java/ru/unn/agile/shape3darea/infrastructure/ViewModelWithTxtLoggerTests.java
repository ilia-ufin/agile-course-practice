package ru.unn.agile.shape3darea.infrastructure;

import ru.unn.agile.shape3darea.viewmodel.ViewModel;
import ru.unn.agile.shape3darea.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("shape_3d_area_view_model_txt_logger_test.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
