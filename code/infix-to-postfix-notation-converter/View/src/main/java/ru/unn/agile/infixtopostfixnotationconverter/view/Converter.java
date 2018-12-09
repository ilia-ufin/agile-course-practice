package ru.unn.agile.infixtopostfixnotationconverter.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ru.unn.agile.infixtopostfixnotationconverter.infrastructure.FileLogger;
import ru.unn.agile.infixtopostfixnotationconverter.viewmodel.ViewModel;

import java.io.IOException;

import static java.lang.System.exit;

public class Converter {
    private static final int EXIT_CODE_GENERAL_ERROR = 1;

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField infixExpression;
    @FXML
    private TextField postfixExpression;
    @FXML
    private TextField expressionResult;
    @FXML
    private Text status;
    @FXML
    private Button convertAndCalculateButton;
    @FXML
    private ListView<String> log;

    @FXML
    void initialize() {
        viewModel = new ViewModel();
        try {
            viewModel.setLogger(new FileLogger("infix-to-postfix-converter.log"));
        } catch (IOException e) {
            e.printStackTrace();
            exit(EXIT_CODE_GENERAL_ERROR);
        }

        infixExpression.textProperty().bindBidirectional(viewModel.infixExpressionProperty());
        postfixExpression.textProperty().bindBidirectional(viewModel.postfixExpressionProperty());
        expressionResult.textProperty().bindBidirectional(viewModel.expressionResultProperty());
        status.textProperty().bindBidirectional(viewModel.statusProperty());
        log.itemsProperty().bind(viewModel.logLinesProperty());

        convertAndCalculateButton.disableProperty().bindBidirectional(
                viewModel.convertButtonDisabledProperty());

        convertAndCalculateButton.setOnAction(event -> viewModel.convertAndCalculate());
    }
}
