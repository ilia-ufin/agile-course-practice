package ru.unn.agile.numerical_integration.view.legacy;

import ru.unn.agile.numerical_integration.viewmodel.legacy.ViewModel;
import ru.unn.agile.numerical_integration.infrastructure.FileLogger;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.lang.System.exit;

public final class MainForm {
    private static final int EXIT_CODE_GENERAL_ERROR = 1;

    private final ViewModel model;

    private JPanel mainPanel;
    private JTextField functionText;
    private JTextField leftBorderText;
    private JTextField rightBorderText;
    private JButton computeIntegralButton;
    private JTextArea outputText;
    private JTextField splitsText;
    private JLabel functionLabel;
    private JLabel splitsLabel;
    private JLabel leftBorderLabel;
    private JLabel rightBorderLabel;
    private JLabel helpText;
    private JTextArea logList;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MainForm(final ViewModel viewModel) {
        this.model = viewModel;

        try {
            viewModel.setLogger(new FileLogger("numerical_integration.log"));
        } catch (IOException e) {
            e.printStackTrace();
            exit(EXIT_CODE_GENERAL_ERROR);
        }

        suppressUnused(mainPanel);
        suppressUnused(functionText);
        suppressUnused(leftBorderText);
        suppressUnused(rightBorderText);
        suppressUnused(computeIntegralButton);
        suppressUnused(outputText);
        suppressUnused(splitsText);
        suppressUnused(functionLabel);
        suppressUnused(splitsLabel);
        suppressUnused(leftBorderLabel);
        suppressUnused(rightBorderLabel);
        suppressUnused(helpText);
        suppressUnused(logList);


        computeIntegralButton.addActionListener(e -> {
            bind();
            model.compute();
            backBind();
        });

        KeyAdapter messageUpdater = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                backBind();
            }
        };
        functionText.addKeyListener(messageUpdater);
        leftBorderText.addKeyListener(messageUpdater);
        rightBorderText.addKeyListener(messageUpdater);
        splitsText.addKeyListener(messageUpdater);

        backBind();
    }

    private void bind() {
        model.setFunction(functionText.getText());
        model.setLeftBorderValue(leftBorderText.getText());
        model.setRightBorderValue(rightBorderText.getText());
        model.setSplitsNumber(splitsText.getText());
    }

    private void backBind() {
        functionText.setText(model.getFunctionText());
        leftBorderText.setText(model.getLeftBorderValue());
        rightBorderText.setText(model.getRightBorderValue());
        splitsText.setText(model.getSplitsNumber());
        computeIntegralButton.setEnabled(model.canComputeFunction());
        outputText.setText(model.getOutputMessage());
        logList.setText(String.join("\n", model.getLogMessages()));
    }

    private static Object suppressUnused(final Object o) {
        return o;
    }
}
