package ru.unn.agile.matrix.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import ru.unn.agile.matrix.viewmodel.ViewModel;
import ru.unn.agile.matrix.viewmodel.ViewModel.Operation;
import ru.unn.agile.matrix.infrastructure.TxtLogger;

import java.io.IOException;
import static java.lang.System.exit;

public class Calculator {
    private static final int EXIT_CODE_GENERAL_ERROR = 1;

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField matrixA;
    @FXML
    private TextField matrixB;
    @FXML
    private ComboBox<Operation> operation;
    @FXML
    private Button calculateButton;
    @FXML
    private TextField result;
    @FXML
    private Text status;
    @FXML
    private ListView<String> logView;

    @FXML
    void initialize() {
        logView.setStyle("-fx-font-size: 20;");
        operation.setStyle("-fx-font-size: 15;");

        try {
            viewModel = new ViewModel(new TxtLogger("./MatrixCalc.log"));
        } catch (IOException exc) {
            exit(EXIT_CODE_GENERAL_ERROR);
        }

        matrixA.textProperty().bindBidirectional(viewModel.matrixAProperty());
        matrixB.textProperty().bindBidirectional(viewModel.matrixBProperty());
        operation.setItems(viewModel.operationsProperty().getValue());
        operation.valueProperty().bindBidirectional(viewModel.operationProperty());
        result.textProperty().bindBidirectional(viewModel.resultProperty());
        status.textProperty().bindBidirectional(viewModel.statusProperty());

        calculateButton.disableProperty().bindBidirectional(
                viewModel.calculateButtonDisabledProperty()
        );

        calculateButton.setOnAction(event -> viewModel.calculate());

        logView.itemsProperty().bindBidirectional(viewModel.logProperty());
    }
}
