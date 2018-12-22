package ru.unn.agile.shapevolume.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.shapevolume.infrastracture.FileLogger;
import ru.unn.agile.shapevolume.viewmodel.Shape;
import ru.unn.agile.shapevolume.viewmodel.ViewModel;

public class VolumeCalculator {
    private static final String FILE_NAME = "shapeVolume.log";
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtFirstArgument;
    @FXML
    private TextField txtSecondArgument;
    @FXML
    private TextField txtThirdArgument;
    @FXML
    private ComboBox<Shape> cbShapes;

    @FXML
    void initialize() {
        txtFirstArgument.textProperty().bindBidirectional(viewModel.firstArgumentValueProperty());
        txtSecondArgument.textProperty().bindBidirectional(viewModel.secondArgumentValueProperty());
        txtThirdArgument.textProperty().bindBidirectional(viewModel.thirdArgumentValueProperty());
        cbShapes.valueProperty().bindBidirectional(viewModel.currentShapeProperty());
        viewModel.setLogger(new FileLogger(FILE_NAME));
    }
}
