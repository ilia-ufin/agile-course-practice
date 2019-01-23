package ru.unn.agile.polynomial.infrastructure;

import ru.unn.agile.polynomial.viewmodel.ViewModel;
import ru.unn.agile.polynomial.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {

    @Override
    public void initViewModel() {
        TextLogger realLogger = new TextLogger("./ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
