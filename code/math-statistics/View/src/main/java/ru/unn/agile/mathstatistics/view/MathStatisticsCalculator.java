package ru.unn.agile.mathstatistics.view;

import javax.swing.*;

import ru.unn.agile.mathstatistics.viewmodel.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public final class MathStatisticsCalculator {
    private JTextField valueText;
    private JTextField probabilityText;
    private JTextField momentOrderText;
    private JTextField momentOffsetText;
    private JTextField resultText;
    private JTextArea sampleTextArea;
    private JButton addToDistributionButton;
    private JButton resetButton;
    private JButton calculateButton;
    private JComboBox<Operation> operationComboBox;
    private JPanel mainPanel;
    private JLabel statusMessageLabel;

    private ViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("MathStatisticsCalculator");
        frame.setContentPane(
                new MathStatisticsCalculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MathStatisticsCalculator() {
    }

    private MathStatisticsCalculator(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfOperations();

        addCalculateButtonListener();
        addAddToDistributionButtonListener();
        addResetButtonListener();
        addOperationComboBoxListener();

        addDistributionUnitKeyAdapter();
        addMomentOrderKeyAdapter();
        addMomentOffsetKeyAdapter();
    }

    private void loadListOfOperations() {
        Operation[] operations = Operation.values();
        operationComboBox.setModel(new JComboBox<>(operations).getModel());
    }

    private void addCalculateButtonListener() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                MathStatisticsCalculator.this.viewModel.calculateProcess();
                backBind();
            }
        });
    }

    private void addAddToDistributionButtonListener() {
        addToDistributionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                MathStatisticsCalculator.this.viewModel.addToDistributionProcess();
                backBind();
            }
        });
    }

    private void addResetButtonListener() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                MathStatisticsCalculator.this.viewModel.resetProcess();
                backBind();
            }
        });
    }

    private void addOperationComboBoxListener() {
        operationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });
    }

    private void addDistributionUnitKeyAdapter() {
        KeyAdapter distributionUnitKeyAdapter = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                MathStatisticsCalculator.this.viewModel.checkDistributionUnit();
                backBind();
            }
        };

        valueText.addKeyListener(distributionUnitKeyAdapter);
        probabilityText.addKeyListener(distributionUnitKeyAdapter);
    }

    private void addMomentOrderKeyAdapter() {
        KeyAdapter momentOrderKeyAdapter = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                MathStatisticsCalculator.this.viewModel.checkMomentOrder();
                backBind();
            }
        };

        momentOrderText.addKeyListener(momentOrderKeyAdapter);
    }

    private void addMomentOffsetKeyAdapter() {
        KeyAdapter momentOffsetKeyAdapter = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                MathStatisticsCalculator.this.viewModel.checkMomentOffset();
                backBind();
            }
        };

        momentOffsetText.addKeyListener(momentOffsetKeyAdapter);
    }

    private void bind() {
        viewModel.setValueText(valueText.getText());
        viewModel.setProbabilityText(probabilityText.getText());
        viewModel.setMomentOrderText(momentOrderText.getText());
        viewModel.setMomentOffsetText(momentOffsetText.getText());

        viewModel.setOperation((Operation) operationComboBox.getSelectedItem());
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled());

        valueText.setText(viewModel.getValueText());
        probabilityText.setText(viewModel.getProbabilityText());
        momentOrderText.setText(viewModel.getMomentOrderText());
        momentOffsetText.setText(viewModel.getMomentOffsetText());
        operationComboBox.setSelectedItem(viewModel.getOperation());
        statusMessageLabel.setText(viewModel.getStatusMessageText());
        resultText.setText(viewModel.getResultText());

        momentOffsetText.setEnabled(viewModel.isOffsetTextEnabled());
        momentOrderText.setEnabled(viewModel.isOrderTextEnabled());

        List<String> distributionUnits = viewModel.getDistributionUnits();
        String distributionUnitsString = "";
        for (String distributionUnit : distributionUnits) {
            distributionUnitsString += distributionUnit + "\n";
        }
        sampleTextArea.setText(distributionUnitsString);
    }
}
