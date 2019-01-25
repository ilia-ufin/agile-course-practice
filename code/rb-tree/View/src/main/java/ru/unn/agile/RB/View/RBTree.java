package ru.unn.agile.RB.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.RB.ViewModel.ViewModel;

public class RBTree {
    @FXML
    private ViewModel viewModel;

    @FXML
    private Button performInsertion;
    @FXML
    private Button performFind;
    @FXML
    private TextField key;
    @FXML
    private TextField value;
    @FXML
    private Label     status;

    @FXML
    void initialize() {
        key.textProperty().bindBidirectional(viewModel.keyProperty());
        value.textProperty().bindBidirectional(viewModel.valueProperty());
        status.textProperty().bind(viewModel.statusProperty().asString());

        performFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.actionFind();
            }
        });

        performInsertion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.actionInsert();
            }
        });
    }
}
