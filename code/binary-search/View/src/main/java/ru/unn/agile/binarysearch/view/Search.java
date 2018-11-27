package ru.unn.agile.binarysearch.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.binarysearch.viewmodel.ViewModel;

public class Search {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField arrayInput;
    @FXML
    private TextField elementInput;
    @FXML
    private Button button;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelResult;
    @FXML
    void initialize() {
        arrayInput.textProperty().bindBidirectional(viewModel.arrayInputProperty());
        elementInput.textProperty().bindBidirectional(viewModel.elementInputProperty());
        labelStatus.textProperty().bindBidirectional(viewModel.statusProperty());
        labelResult.textProperty().bindBidirectional(viewModel.resultProperty());

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.search();
            }
        });
    }
}
