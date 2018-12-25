package ru.unn.agile.ConverterTemperatures.infrastructure;

import ru.unn.agile.ConverterTemperatures.viewmodel.ViewModel;
import ru.unn.agile.ConverterTemperatures.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {

    @Override
    public void initViewModel() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
