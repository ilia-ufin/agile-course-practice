package ru.unn.agile.LcdDigits.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.LcdDigits.viewmodel.ViewModel;
import ru.unn.agile.LcdDigits.Infrastructure.*;

public class LcdDigits {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtDigits;
    @FXML
    private Button btnTransform;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./BookStore.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean prevValue, final Boolean newValue) {
                viewModel.onFocusChanged(prevValue, newValue);
            }
        };

        txtDigits.textProperty().bindBidirectional(viewModel.digitsProperty());
        txtDigits.focusedProperty().addListener(focusChangeListener);

        btnTransform.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.transformLcdDigits();
            }
        });
    }
}
