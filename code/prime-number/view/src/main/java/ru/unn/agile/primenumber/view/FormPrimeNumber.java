package ru.unn.agile.primenumber.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.primenumber.infrastructure.LoggerFactory;
import ru.unn.agile.primenumber.viewModel.ViewModel;

public class FormPrimeNumber {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField leftBound;
    @FXML
    private TextField rightBound;
    @FXML
    private Button search;
    @FXML
    void initialize() {
        viewModel.setLogger(new LoggerFactory());
        leftBound.textProperty().bindBidirectional(viewModel.leftBoundProperty());
        rightBound.textProperty().bindBidirectional(viewModel.rightBoundProperty());
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.searchPrimeNumber();

            }
        });
        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };
        leftBound.focusedProperty().addListener(focusChangeListener);
        rightBound.focusedProperty().addListener(focusChangeListener);

    }

}
