package ru.unn.agile.salarycalculator.view.legacy;

import ru.unn.agile.salarycalculator.infrastructure.Logger;
import ru.unn.agile.salarycalculator.viewmodel.legacy.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public final class SalaryCalculatorView {
    private JPanel mainPanel;
    private JTextField txtSalary;
    private JTextField txtWorkedHours;
    private JTextField txtCountYear;
    private JTextField txtResult;
    private JButton calculateButton;
    private JLabel lbStatus;
    private JTextField txtCountMonth;
    private JPanel loggerPanel;
    private JLabel loggerLabel;
    private JList<String> listLog;
    private ViewModel viewModel;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 300;

    private SalaryCalculatorView() {
    }

    private SalaryCalculatorView(final ViewModel viewModelArg) {
        this.viewModel = viewModelArg;
        backBind();
        salaryCalculatorActionListener();
        salaryCalculatorKeyAdapter();
        loggerPanel.setBackground(Color.CYAN);
        loggerLabel.setBackground(Color.BLACK);
        txtSalary.addFocusListener(focusLostListener);
        txtWorkedHours.addFocusListener(focusLostListener);
        txtCountMonth.addFocusListener(focusLostListener);
        txtCountYear.addFocusListener(focusLostListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("SalaryCalculatorView");
        Logger logger = new Logger("./salaryCalculator.log");
        frame.setContentPane(new SalaryCalculatorView(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setSalary(txtSalary.getText());
        viewModel.setWorkedHours(txtWorkedHours.getText());
        viewModel.setCountMonth(txtCountMonth.getText());
        viewModel.setCountYear(txtCountYear.getText());
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnable());
        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        listLog.setListData(items);
    }

    private void salaryCalculatorActionListener() {
        calculateButton.addActionListener(actionEvent -> {
            bind();
            viewModel.calculateSalary();
            backBind();
        });
    }

    private void salaryCalculatorKeyAdapter() {
        KeyAdapter whenInCountType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.checkCountFields();
                backBind();
            }
        };

        txtSalary.addKeyListener(whenInCountType);
        txtWorkedHours.addKeyListener(whenInCountType);
        txtCountMonth.addKeyListener(whenInCountType);
        txtCountYear.addKeyListener(whenInCountType);
    }

    private FocusAdapter focusLostListener = new FocusAdapter() {
        public void focusLost(final FocusEvent e) {
            bind();
            viewModel.focusLost();
            backBind();
        }
    };

}
