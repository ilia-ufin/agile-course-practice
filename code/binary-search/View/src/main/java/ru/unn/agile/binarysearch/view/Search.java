package ru.unn.agile.binarysearch.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.unn.agile.binarysearch.viewmodel.ViewModel;

import ru.unn.agile.binarysearch.infrastructure.TxtLogger;

import java.io.IOException;

public class Search {
    private static final String LOG_PATH = "log";

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
    private TextArea textArea;
    @FXML
    void initialize() {
        try {
            TxtLogger txtLogger = new TxtLogger(LOG_PATH);
            viewModel.setLogger(txtLogger);
        } catch (IOException e) {
            e.printStackTrace();
        }

        arrayInput.textProperty().bindBidirectional(viewModel.arrayInputProperty());
        elementInput.textProperty().bindBidirectional(viewModel.elementInputProperty());
        labelStatus.textProperty().bindBidirectional(viewModel.statusProperty());
        labelResult.textProperty().bindBidirectional(viewModel.resultProperty());
        textArea.textProperty().bindBidirectional(viewModel.logProperty());

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.search();
            }
        });
    }
}
