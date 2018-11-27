package ru.unn.agile.numbersinwords.view;

import ru.unn.agile.numbersinwords.viewmodel.NumbersInWordsViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Converter {
    private NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();
    private JPanel mainPanel;
    private JButton convertButton;
    private JTextField number;
    private JLabel errorStatus;
    private JLabel numbersInWords;

    public static void main(final String[] args) {
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
    }
}
