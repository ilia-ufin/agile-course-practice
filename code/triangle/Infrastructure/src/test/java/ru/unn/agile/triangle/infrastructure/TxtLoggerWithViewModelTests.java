package ru.unn.agile.triangle.infrastructure;

import ru.unn.agile.triangle.viewmodel.ViewModel;
import ru.unn.agile.triangle.viewmodel.ViewModelTest;

public class TxtLoggerWithViewModelTests extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_with_TxtLogger_Tests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
