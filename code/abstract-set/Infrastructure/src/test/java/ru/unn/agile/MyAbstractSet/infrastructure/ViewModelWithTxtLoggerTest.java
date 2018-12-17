package ru.unn.agile.MyAbstractSet.infrastructure;

import ru.unn.agile.MyAbstractSet.viewmodel.ViewModel;
import ru.unn.agile.MyAbstractSet.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./AbstractSet-ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
