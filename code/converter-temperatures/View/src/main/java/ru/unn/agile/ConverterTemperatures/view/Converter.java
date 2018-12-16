package ru.unn.agile.ConverterTemperatures.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.ConverterTemperatures.model.TemperaturesUnit;
import ru.unn.agile.ConverterTemperatures.viewmodel.ViewModel;

public class Converter {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<TemperaturesUnit> cbScale;
    @FXML
    private Button btnConvert;

    @FXML
    void initialize() {
        value.textProperty().bindBidirectional(viewModel.convertFromProperty());

        cbScale.valueProperty().bindBidirectional(viewModel.scaleProperty());

        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }

}
