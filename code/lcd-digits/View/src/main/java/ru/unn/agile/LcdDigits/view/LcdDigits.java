package ru.unn.agile.LcdDigits.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.LcdDigits.viewmodel.ViewModel;

public class LcdDigits {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtDigits;
    @FXML
    private Button btnTransform;

    @FXML
    void initialize() {

        txtDigits.textProperty().bindBidirectional(viewModel.digitsProperty());

        btnTransform.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.transformLcdDigits();
            }
        });
    }
}
