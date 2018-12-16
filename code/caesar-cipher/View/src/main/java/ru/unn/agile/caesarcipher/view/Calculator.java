package ru.unn.agile.caesarcipher.view;

import ru.unn.agile.caesarcipher.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class Calculator {
    private ViewModel viewModel = new ViewModel();

    private JPanel mainPanel;
    private JButton code;
    private JTextField inputTextBox;
    private JTextField offsetTextBox;
    private JTextField resultTextBox;
    private JTextField statusTextBox;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

        Calculator() {
            tie();
            code.addActionListener(e -> {
                tieback();
                viewModel.codeCaesar();
                tie();
            });

            offsetTextBox.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(final DocumentEvent e) {
                    tieback();
                    tie();
                }

                @Override
                public void removeUpdate(final DocumentEvent e) {
                    tieback();
                    tie();
                }

                @Override
                public void changedUpdate(final DocumentEvent e) {
                    tieback();
                    tie();
                }
            });
        }

    private void tie() {
        code.setEnabled(viewModel.isCodeButtonEnabled());
        resultTextBox.setText(viewModel.getCaesarCipher());
        statusTextBox.setText(viewModel.getStatus());
    }

    private void tieback() {
        viewModel.setTextBoxs(inputTextBox.getText(), offsetTextBox.getText());
    }


}
