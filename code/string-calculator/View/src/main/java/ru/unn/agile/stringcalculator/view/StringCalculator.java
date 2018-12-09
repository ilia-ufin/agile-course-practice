package ru.unn.agile.stringcalculator.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.stringcalculator.viewmodel.Operation;
import ru.unn.agile.stringcalculator.viewmodel.ViewModel;
import ru.unn.agile.stringcalculator.infrastructure.*;

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
        viewModel.setLogger(new TxtLogger("./StringCalculatorLog.log"));

        final ChangeListener<Boolean> subsChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.subsChanged(oldValue, newValue);
            }
        };

        textInput.textProperty().bindBidirectional(viewModel.inputDataProperty());
        textInput.focusedProperty().addListener(subsChangeListener);

        comboBoxOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        buttonCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
