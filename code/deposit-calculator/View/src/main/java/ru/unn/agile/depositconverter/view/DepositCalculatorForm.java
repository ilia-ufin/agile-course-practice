package ru.unn.agile.depositconverter.view;

import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModel;
import ru.unn.agile.depositconverter.infrastructure.FileLogger;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;


public class DepositCalculatorForm {
    private DepositCalculatorViewModel calculatorViewModel;
    private JPanel mainPanel;

    private JLabel frequencyOfCapitalizationLabel;
    private JLabel incomeLabel;
    private JLabel depositAmountLabel;
    private JLabel accruedInterestLabel;
    private JLabel termPlacementLabel;
    private JLabel interestRateLabel;

    private JTextField depositAmountTextField;
    private JTextField termPlacementTextField;
    private JTextField interestRateTextField;

    private JButton calculateButton;
    private JComboBox accruedInterestComboBox;
    private JComboBox frequencyOfCapitalizationComboBox;
    private JList<String> logList;
    private JLabel statusText;
    private JLabel statusNow;
    private JLabel incomeResult;
    private JLabel revenueLabel;
    private JLabel revenueResult;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DepositCalculatorForm");
        FileLogger logger = new FileLogger("./DepositCalculatorLog.log");
        frame.setContentPane(new DepositCalculatorForm(
                new DepositCalculatorViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private DepositCalculatorForm() {

    }

    DepositCalculatorForm(final DepositCalculatorViewModel viewModel) {
        calculatorViewModel = viewModel;

        backBind();
        depositCalculateButtonActionListener();
        depositAccruedInterestActionListener();
        depositFrequencyOfCapitalizationActionListener();
        depositCalculatorKeyAdapter();
        depositCalculatorFocusAdapter();
        depositCalculatorFormDefaultValues();

        bind();
    }

    private void bind() {
        calculatorViewModel.setDepositAmount(depositAmountTextField.getText());
        calculatorViewModel.setTermPlacement(termPlacementTextField.getText());
        calculatorViewModel.setInterestRate(interestRateTextField.getText());
        calculatorViewModel.setAccruedInterest(
                accruedInterestComboBox.getSelectedItem().toString());
        calculatorViewModel.setFrequencyOfCapitalization(
                frequencyOfCapitalizationComboBox.getSelectedItem().toString());
    }

    private void backBind() {
        calculateButton.setEnabled(calculatorViewModel.isCalculateButtonEnabled());
        revenueResult.setText(calculatorViewModel.getRevenueWhenAddToDeposit());
        incomeResult.setText(calculatorViewModel.getIncomeViewModel());
        statusNow.setText(calculatorViewModel.getStatus());

        List<String> log = calculatorViewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }

    private void depositCalculatorFormDefaultValues() {
        depositCalculatorSetLabels();
        depositAmountTextField.setText(String.valueOf(calculatorViewModel.getDepositAmount()));
        termPlacementTextField.setText(String.valueOf(calculatorViewModel.getTermPlacement()));
        interestRateTextField.setText(String.valueOf(calculatorViewModel.getInterestRate()));
    }

    private void depositCalculatorSetLabels() {
        frequencyOfCapitalizationLabel.setText("Frequency Of Capitalization");
        revenueLabel.setText("Revenue");
        incomeLabel.setText("Income");
        depositAmountLabel.setText("Deposit Amount");
        accruedInterestLabel.setText("Accrued Interest");
        termPlacementLabel.setText("Term Placement");
        interestRateLabel.setText("Interest Rate");
        statusText.setText("Status");
    }

    private void depositCalculateButtonActionListener() {
        calculateButton.addActionListener(actionEvent -> {
            bind();
            calculatorViewModel.calculate();
            backBind();
        });
    }

    private void depositCalculatorKeyAdapter() {
        KeyAdapter whenInCountType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                calculatorViewModel.checkCountFields();
                backBind();
            }
        };

        depositAmountTextField.addKeyListener(whenInCountType);
        termPlacementTextField.addKeyListener(whenInCountType);
        interestRateTextField.addKeyListener(whenInCountType);
    }

    private void depositFrequencyOfCapitalizationActionListener() {
        frequencyOfCapitalizationComboBox.addActionListener(actionEvent -> {
            bind();
            backBind();
        });
    }

    private void depositAccruedInterestActionListener() {
        accruedInterestComboBox.addActionListener(actionEvent -> {
            bind();
            backBind();
        });
    }

    private void depositCalculatorFocusAdapter() {
        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                bind();
                calculatorViewModel.focusLost();
                backBind();
            }
        };

        depositAmountTextField.addFocusListener(focusLostListener);
        termPlacementTextField.addFocusListener(focusLostListener);
        interestRateTextField.addFocusListener(focusLostListener);
    }
}
