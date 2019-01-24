package ru.unn.agile.modifideStack.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.modifideStack.viewmodel.ViewModel;



public class ModifideStackView {
    @FXML
    private ViewModel viewModel;

    @FXML
    private Button popElementButton;
    @FXML
    private Button pushElementButton;
    @FXML
    private TextField inputNewElemField;

    @FXML
    void initialize() {
        inputNewElemField.textProperty().bindBidirectional(viewModel.pushElementProperty());

        pushElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.pushingElement();
            }
        });

        popElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.popElement();
            }
        });
    }
}
