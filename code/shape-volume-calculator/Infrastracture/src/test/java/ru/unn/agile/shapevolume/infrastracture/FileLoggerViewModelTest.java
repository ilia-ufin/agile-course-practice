package ru.unn.agile.shapevolume.infrastracture;

import org.junit.Test;
import ru.unn.agile.shapevolume.viewmodel.LogMessages;
import ru.unn.agile.shapevolume.viewmodel.Shape;
import ru.unn.agile.shapevolume.viewmodel.ViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class FileLoggerViewModelTest {
    @Test
    public void canCreateViewModelWithFileLogger() {
        ViewModel vm = new ViewModel();
        vm.setLogger(new FileLogger("testFile"));
    }

    @Test
    public void valueChangeIsLogged() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger("testFile"));

        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        viewModel.firstArgumentValueProperty().set("6");

        File file = new File("testFile");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String logString = getWholeLog(br);
        assertTrue(logString.contains(LogMessages.FIRST_ARGUMENT_INPUTED.toString())
                && logString.contains("6"));
    }

    @Test
    public void shapeChangeIsLogged() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger("testFile"));

        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);

        File file = new File("testFile");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String logString = getWholeLog(br);
        assertTrue(logString.contains(LogMessages.SHAPE_CHANGED.toString())
                && logString.contains(Shape.REGULAR_POLYGON_PRISM.toString()));
    }

    @Test
    public void calculationIsLogged() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger("testFile"));

        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        viewModel.firstArgumentValueProperty().set("6");
        viewModel.secondArgumentValueProperty().set("1");
        viewModel.thirdArgumentValueProperty().set("2");

        File file = new File("testFile");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String logString = getWholeLog(br);
        assertTrue(logString.contains(LogMessages.CALCULATION_PERFORMED.toString())
                && logString.contains(viewModel.getResult()));
    }

    @Test
    public void problemWithFileLoggerIsLogged() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger(""));

        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);

        assertTrue(viewModel
                .logsProperty()
                .get()
                .contains(LogMessages.SOMETHING_WENT_WRONG.toString()));
    }

    @Test
    public void problemWithFileLoggerWhenSetParamsIsLogged() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger(""));

        viewModel.firstArgumentValueProperty().set("6");

        assertTrue(viewModel
                .logsProperty()
                .get()
                .contains(LogMessages.SOMETHING_WENT_WRONG.toString()));
    }

    @Test
    public void loggingOfCalculationWentWrong() throws IOException {
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(new FileLogger(""));

        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        viewModel.firstArgumentValueProperty().set("6");
        viewModel.secondArgumentValueProperty().set("1");
        viewModel.thirdArgumentValueProperty().set("2");

        assertTrue(viewModel
                .logsProperty()
                .get()
                .contains(LogMessages.SOMETHING_WENT_WRONG.toString()));
    }

    private String getWholeLog(final BufferedReader br) throws IOException {
        StringBuilder wholeLog = new StringBuilder();
        String currentLine = br.readLine();
        while (currentLine != null) {
            wholeLog.append(currentLine);
            currentLine = br.readLine();
        }
        return wholeLog.toString();
    }

}
