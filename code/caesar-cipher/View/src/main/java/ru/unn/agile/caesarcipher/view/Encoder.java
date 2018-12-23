package ru.unn.agile.caesarcipher.view;

import ru.unn.agile.caesarcipher.infrastructure.TxtLogger;
import ru.unn.agile.caesarcipher.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public final class Encoder {

    private JPanel mainPanel;
    private JButton code;
    private JTextField inputTextBox;
    private JTextField offsetTextBox;
    private JTextField resultTextBox;
    private JTextField statusTextBox;
    private JTextArea loggerTextArea;
    private static TxtLogger logger;
    private static ViewModel viewModel;
    private static final String ENTER = "\r\n";

    public static void main(final String[] args) {
        logger = new TxtLogger("Encoder.log");
        viewModel = new ViewModel(logger);
        JFrame frame = new JFrame("Encoder");
        frame.setContentPane(new Encoder().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Encoder() {
        tie();
        code.addActionListener(e -> {
            tieback();
            viewModel.codeCaesar();
            loggerTextArea.setText(String.join(ENTER, viewModel.getLog()));
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
        inputTextBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent event) {
                tieback();
                tie();
            }
            @Override
            public void removeUpdate(final DocumentEvent event) {
                tieback();
                tie();
            }
            @Override
            public void changedUpdate(final DocumentEvent event) {
                tieback();
                tie();
            }
        });

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                tie();
                viewModel.logInputParams();
                loggerTextArea.setText(String.join(ENTER, viewModel.getLog()));
                tieback();
            }
        };
        inputTextBox.addFocusListener(focusLostListener);
        offsetTextBox.addFocusListener(focusLostListener);
    }

    private void tie() {
        code.setEnabled(viewModel.isCodeButtonEnabled());
        resultTextBox.setText(viewModel.getCaesarCipher());
        statusTextBox.setText(viewModel.getStatus());

    }

    private void tieback() {
        viewModel.setTextBoxInput(inputTextBox.getText());
        viewModel.setTextBoxOffset(offsetTextBox.getText());
    }
}
