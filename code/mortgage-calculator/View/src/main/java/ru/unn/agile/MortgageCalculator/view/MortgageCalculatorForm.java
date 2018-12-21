package ru.unn.agile.mortgagecalculator.view;

import ru.unn.agile.mortgagecalculator.viewmodel.legacy.ViewModel;

import ru.unn.agile.mortgagecalculator.infrastructure.TxtLogger;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public final class MortgageCalculatorForm {
    private JPanel mainPanel;
    private JTextField txtInterestRate;
    private JTextField txtTermMortgage;
    private JButton calculateButton;
    private JLabel lbStatus;
    private JTextField txtFullPriceMortgage;
    private JTextField txtApartmentPrice;
    private JTextField txtInitialPayment;
    private JTable tablePayPerMonth;
    private JPanel panelTableMortgage;
    private JTextArea textLog;
    private ViewModel viewModel;
    private static final int HEIGHT_TAB_WIDGET = 200;
    private static final int WIDTH_TAB_WIDGET = 400;
    private static final int HEIGHT_WINDOWS = 550;
    private static final int WIDTH_WINDOWS = 900;


    private MortgageCalculatorForm(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        fullPriceMortgageCalculatorActionListener();
        fullPriceMortgageCalculatorAKeyAdapter();

        tablePayPerMonth = new JTable(viewModel.getTableModel());
        tablePayPerMonth.setPreferredScrollableViewportSize(new Dimension(WIDTH_TAB_WIDGET,
                HEIGHT_TAB_WIDGET));
        JScrollPane scrollTable = new JScrollPane(tablePayPerMonth);
        panelTableMortgage.setLayout(new FlowLayout());
        panelTableMortgage.add(scrollTable);
        tablePayPerMonth.setEnabled(false);
        panelTableMortgage.setVisible(false);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("MortgageCalculatorForm");
        frame.setContentPane(new MortgageCalculatorForm(new ViewModel(
                new TxtLogger("MortgageCalculator.log")
        )).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(WIDTH_WINDOWS, HEIGHT_WINDOWS);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setApartmentPrice(txtApartmentPrice.getText());
        viewModel.setInitialPayment(txtInitialPayment.getText());
        viewModel.setInterestRate(txtInterestRate.getText());
        viewModel.setTermMortgage(txtTermMortgage.getText());
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnable());
        txtFullPriceMortgage.setText(viewModel.getFullPriceMortgage());
        lbStatus.setText(viewModel.getStatus());

        StringBuilder str = new StringBuilder();
        for (String record : viewModel.getLog()) {
            str.append(record).append("\n");
        }
        textLog.setText(str.toString());
    }

    private void fullPriceMortgageCalculatorActionListener() {
        calculateButton.addActionListener(actionEvent -> {
            bind();
            viewModel.calculateFullPriceMortgage();
            backBind();

            tablePayPerMonth.setModel(viewModel.getTableModel());
            panelTableMortgage.setVisible(true);
        });
    }

    private void fullPriceMortgageCalculatorAKeyAdapter() {
        KeyAdapter whenInCountType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.checkCountFields();
                backBind();

                panelTableMortgage.setVisible(false);
            }
        };

        txtApartmentPrice.addKeyListener(whenInCountType);
        txtInitialPayment.addKeyListener(whenInCountType);
        txtInterestRate.addKeyListener(whenInCountType);
        txtTermMortgage.addKeyListener(whenInCountType);
    }
};
