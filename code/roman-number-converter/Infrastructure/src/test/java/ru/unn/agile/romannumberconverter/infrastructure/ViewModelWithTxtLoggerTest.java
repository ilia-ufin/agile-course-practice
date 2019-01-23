package ru.unn.agile.romannumberconverter.infrastructure;

import ru.unn.agile.romannumberconverter.viewmodel.ViewModel;
import ru.unn.agile.romannumberconverter.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {
    @Override
    public void createViewModel() {
        TxtLogger realLogger = new TxtLogger("./ViewModelTextLogger.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
