package ru.unn.agile.Queue.view;

import ru.unn.agile.queue.infrastructure.FileLogger;
import ru.unn.agile.queue.viewmodel.ViewModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public final class QueueProvider {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JButton addElementButton;
    private JButton removeHeadButton;
    private JButton clearQueueButton;

    private JTextField inputNewElemField;
    private JTextArea queueTextArea;
    private JLabel outputStateLabel;
    private JList<String> logList;

    private QueueProvider(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                QueueProvider.this.viewModel.pushProcess();
                backBind();
            }
        });

        removeHeadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                QueueProvider.this.viewModel.popProcess();
                backBind();
            }
        });

        clearQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                QueueProvider.this.viewModel.clear();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                QueueProvider.this.viewModel.processingAddField();
                backBind();
            }
        };

        inputNewElemField.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("QueueProvider");
        FileLogger logger = new FileLogger("Queue.log");
        frame.setContentPane(new QueueProvider(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setInputElem(inputNewElemField.getText());
    }

    private void backBind() {
        addElementButton.setEnabled(viewModel.isAddButtonEnabled());
        removeHeadButton.setEnabled(viewModel.isRemoveButtonEnabled());
        clearQueueButton.setEnabled(viewModel.isClearButtonEnabled());

        queueTextArea.setText(viewModel.getQueueStringRepresentation());
        outputStateLabel.setText(viewModel.getCurrentState());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }
}
