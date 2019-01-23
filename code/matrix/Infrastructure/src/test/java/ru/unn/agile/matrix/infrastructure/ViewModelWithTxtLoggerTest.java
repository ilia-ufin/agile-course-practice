package ru.unn.agile.matrix.infrastructure;

import ru.unn.agile.matrix.viewmodel.ViewModel;
import ru.unn.agile.matrix.viewmodel.ViewModelTest;
import static org.junit.Assert.*;
import java.io.IOException;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {
    @Override
    public void setUp() {
        try {
            TxtLogger realLogger =
                    new TxtLogger("./MatrixCalc-ViewModelWithTxtLoggerTest.log");
            super.setExternalViewModel(new ViewModel(realLogger));
        } catch (IOException exc) {
            fail("Could not construct TxtLogger");
        }
    }
}
