package ru.unn.agile.numbersinwords.view;

import ru.unn.agile.numbersinwords.viewmodel.NumbersInWordsViewModel;
import ru.unn.agile.numbersinwords.infrastructure.TxtLogger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

public class Converter {
    private static TxtLogger logger;
    private static NumbersInWordsViewModel viewModel;
    private JPanel mainPanel;
    private JButton convertButton;
    private JTextField number;
    private JLabel errorStatus;
    private JLabel numbersInWords;
    private JTextArea logArea;

    private static final String LOG_NAME = "Converter.log";

    public static void main(final String[] args) {
        try {
            logger = new TxtLogger(LOG_NAME);
            viewModel = new NumbersInWordsViewModel(logger);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Converter() {
        bind();
        convertButton.addActionListener(e -> {
            backBind();
            viewModel.convert();
            bind();
        });

        number.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
               backBind();
               bind();
            }
        });

    }

    private void backBind() {
        viewModel.setNumber(number.getText());
    }

    private void bind() {
        convertButton.setEnabled(viewModel.isConvertButtonEnabled());
        numbersInWords.setText(viewModel.getNumberInWords());
        errorStatus.setText(viewModel.getErrorMessage());
        logArea.setText(String.join("\r\n", viewModel.getLogMessages()));
    }
}
