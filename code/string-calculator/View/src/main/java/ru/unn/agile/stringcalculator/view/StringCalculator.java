package ru.unn.agile.stringcalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.stringcalculator.viewmodel.Operation;
import ru.unn.agile.stringcalculator.viewmodel.ViewModel;

public class StringCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField textInput;
    @FXML
    private ComboBox<Operation> comboBoxOperation;
    @FXML
    private Button buttonCalc;

    @FXML
    void initialize() {
        textInput.textProperty().bindBidirectional(viewModel.inputDataProperty());
        comboBoxOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        buttonCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
