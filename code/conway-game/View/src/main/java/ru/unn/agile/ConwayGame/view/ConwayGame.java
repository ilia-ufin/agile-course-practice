package ru.unn.agile.ConwayGame.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.ConwayGame.viewmodel.ViewModel;
import ru.unn.agile.ConwayGame.infrastructure.*;

public class ConwayGame {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtRows;
    @FXML
    private TextField txtColumns;
    @FXML
    private TextField txtFirstGeneration;
    @FXML
    private Button btnSubmit;

    @FXML
    void initialize() {

        txtRows.textProperty().bindBidirectional(viewModel.rowsNumberProperty());
        txtColumns.textProperty().bindBidirectional(viewModel.columnsNumberProperty());
        txtFirstGeneration.textProperty().bindBidirectional(viewModel.firstGenerationProperty());

        viewModel.setLogger(new TxtLogger("./ConwayGame.log"));
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculateNextGeneration();
            }
        });
    }
}
