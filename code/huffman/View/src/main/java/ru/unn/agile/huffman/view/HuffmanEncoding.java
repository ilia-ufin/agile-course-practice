package ru.unn.agile.huffman.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import ru.unn.agile.huffman.viewmodel.ILogger;
import ru.unn.agile.huffman.viewmodel.ViewModel;
import ru.unn.agile.huffman.infrastructure.TextFileLogger;

public class HuffmanEncoding {
    @FXML
    private ViewModel viewModel;

    @FXML
    private Button btnEncode;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label lbStatus;

    @FXML
    void initialize() {
        ILogger logger = new TextFileLogger(TextFileLogger.STANDARD_LOG_FILE_NAME);
        viewModel.setLogger(logger);
        txtInput.textProperty().bindBidirectional(viewModel.txtProperty());
        txtOutput.textProperty().bindBidirectional(viewModel.txtEncodeProperty());
        lbStatus.textProperty().bindBidirectional(viewModel.statusProperty());

        btnEncode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.encode();
            }
        });
    }
}
