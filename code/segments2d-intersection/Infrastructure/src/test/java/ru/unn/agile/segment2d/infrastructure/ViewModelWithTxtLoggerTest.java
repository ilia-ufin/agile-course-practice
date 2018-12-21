package ru.unn.agile.segment2d.infrastructure;

import ru.unn.agile.segment2d.viewmodel.ViewModel;
import ru.unn.agile.segment2d.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {

    @Override
    public void createViewModel() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
