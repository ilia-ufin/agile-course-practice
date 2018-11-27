package ru.unn.agile.ArraySorter.view;

import ru.unn.agile.ArraySorter.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class ArraySorterProvider {

    private ViewModel viewModel;
    private JPanel mainPanel;
    private JTextField inputValueField;
    private JButton addElementButton;
    private JButton clearArrayButton;
    private JButton sortArrayButton;
    private JTextArea sortedArrayArea;
    private JLabel statusLabel;
    private JLabel sourceArrayLabel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("ArraySorterProvider");
        frame.setContentPane(new ArraySorterProvider(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private ArraySorterProvider(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                ArraySorterProvider.this.viewModel.addProcess();
                backBind();
            }
        });

        sortArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                ArraySorterProvider.this.viewModel.sort();
                backBind();
            }
        });

        clearArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                ArraySorterProvider.this.viewModel.clearProcess();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                ArraySorterProvider.this.viewModel.processingAddField(e.getKeyCode());
                backBind();
            }
        };

        inputValueField.addKeyListener(keyListener);
    }

    private void bind() {
        viewModel.setInputValue(inputValueField.getText());
    }

    private void backBind() {
        addElementButton.setEnabled(viewModel.isAddButtonEnabled());
        sortArrayButton.setEnabled(viewModel.isSortButtonEnabled());
        clearArrayButton.setEnabled(viewModel.isClearButtonEnabled());

        sourceArrayLabel.setText(viewModel.getSortedArrayStringRepresentation());
        sortedArrayArea.setText(viewModel.getInputArrayStringRepresentation());
        statusLabel.setText(viewModel.getCurrentState());
    }
}
