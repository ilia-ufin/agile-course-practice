package ru.unn.agile.depositconverter.view;

import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModel;

import javax.swing.*;
import java.awt.*;


public class DepositCalculatorForm extends JFrame {
    private DepositCalculatorViewModel calculatorViewModel = new DepositCalculatorViewModel();
    private JPanel panel1;
    private JComboBox frequencyOfCapitalizationComboBox;
    private JTextField revenueTextField;
    private JLabel frequencyOfCapitalizationLabel;
    private JPanel resultPanel;
    private JLabel revenueLabel;
    private JLabel incomeLabel;
    private JLabel depositAmountLabel;
    private JLabel accruedInterestLabel;
    private JLabel termPlacementLabel;
    private JLabel interestRateLabel;
    private JTextField depositAmountTextField;
    private JTextField termPlacemantTextField;
    private JTextField interestRateTextField;
    private JTextField incomeTextField;
    private JButton calculateButton;
    private JComboBox accruedInterestCombobox;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DepositCalculatorForm");
        frame.setContentPane(new DepositCalculatorForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    DepositCalculatorForm() {
        super();
        depositCalculatorFormDefaultValues();
        calculateButton.addActionListener(e -> {
            backBind();
            calculatorViewModel.calculate();
            revenueTextField.setText(calculatorViewModel.getRevenueWhenAddToDeposit());
            incomeTextField.setText(calculatorViewModel.getIncomeViewModel());
            bind();
        });
        bind();
    }

    private void bind() { //кладём во вью
        calculateButton.setEnabled(calculatorViewModel.isCalculatorButtonEnabled());
    }

    private void backBind() { //берём из view
        calculatorViewModel.setDepositAmount(depositAmountTextField.getText());
        calculatorViewModel.setTermPlacement(termPlacemantTextField.getText());
        calculatorViewModel.setInterestRate(interestRateTextField.getText());
        calculatorViewModel.setAccruedInterest(
                accruedInterestCombobox.getSelectedItem().toString());
        calculatorViewModel.setFrequencyOfCapitalization(
                frequencyOfCapitalizationComboBox.getSelectedItem().toString());
    }

    private void depositCalculatorFormDefaultValues() {
        depositCalculatorSetLabels();
        resultPanel.setBackground(Color.GREEN);
        depositAmountTextField.setText(String.valueOf(calculatorViewModel.getDepositAmountView()));
        termPlacemantTextField.setText(String.valueOf(calculatorViewModel.getTermPlacementView()));
        interestRateTextField.setText(String.valueOf(calculatorViewModel.getInterestRateView()));
    }

    private void depositCalculatorSetLabels() {
        frequencyOfCapitalizationLabel.setText("FrequencyOfCapitalization");
        revenueLabel.setText("Revenue");
        incomeLabel.setText("Income");
        depositAmountLabel.setText("depositAmount");
        accruedInterestLabel.setText("AccruedInterest");
        termPlacementLabel.setText("TermPlacement");
        interestRateLabel.setText("InterestRate");
    }
}
