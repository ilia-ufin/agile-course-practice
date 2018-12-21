package ru.unn.agile.MyAbstractSet.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.unn.agile.MyAbstractSet.viewmodel.ViewModel;
import ru.unn.agile.MyAbstractSet.infrastructure.TxtLogger;
import ru.unn.agile.MyAbstractSet.viewmodel.ViewModel.Operation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

public class AbstractSet {
    @FXML
    private ViewModel viewModel;

    @FXML
    private ComboBox<Operation> comboOperation;

    @FXML
    private TextArea firstSetTextArea;

    @FXML
    private Button executeButton;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private TextArea secondSetTextArea;

    @FXML
    private Label status;

    @FXML
    private ListView<String> logList;

    @FXML
    void initialize() {
        viewModel = new ViewModel(new TxtLogger("./AbstractSet.log"));
        firstSetTextArea.textProperty().bindBidirectional(viewModel.firstSetTextAreaProperty());
        secondSetTextArea.textProperty().bindBidirectional(viewModel.secondSetTextAreaProperty());
        resultTextArea.textProperty().bindBidirectional(viewModel.resultTextAreaProperty());
        comboOperation.setItems(viewModel.operationsProperty().getValue());
        comboOperation.valueProperty().bindBidirectional(viewModel.operationProperty());
        status.textProperty().bindBidirectional(viewModel.statusProperty());

        executeButton.disableProperty().bindBidirectional(
                viewModel.executeButtonDisabledProperty());

        executeButton.setOnAction(event -> viewModel.execute());

        logList.itemsProperty().bindBidirectional(viewModel.logProperty());
    }

}

