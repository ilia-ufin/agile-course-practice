package ru.unn.agile.ConwayGame.infrastructure;

        import ru.unn.agile.ConwayGame.viewmodel.ViewModel;
        import ru.unn.agile.ConwayGame.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTest extends ViewModelTests {

    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelTextLogger.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
