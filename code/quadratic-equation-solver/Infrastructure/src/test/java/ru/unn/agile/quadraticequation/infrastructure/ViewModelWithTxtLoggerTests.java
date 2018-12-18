package ru.unn.agile.quadraticequation.infrastructure;

import ru.unn.agile.quadraticequation.viewmodel.ViewModel;
import ru.unn.agile.quadraticequation.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModelWithTxtLoggerTests-lab3.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
