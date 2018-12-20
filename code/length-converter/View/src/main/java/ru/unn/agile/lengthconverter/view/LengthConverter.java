package ru.unn.agile.lengthconverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.lengthconverter.infrastructure.TxtLogger;
import ru.unn.agile.lengthconverter.model.LengthUnit;
import ru.unn.agile.lengthconverter.viewmodel.ViewModel;

public class LengthConverter {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<LengthUnit> unitFrom;

    @FXML
    private ComboBox<LengthUnit> unitTo;

    @FXML
    private Button btnConvert;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./LengthConverter.log"));

        value.textProperty().bindBidirectional(viewModel.convertFromProperty());

        unitFrom.valueProperty().bindBidirectional(viewModel.unitPropertyFrom());
        unitTo.valueProperty().bindBidirectional(viewModel.unitPropertyTo());

        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }

}
