package ru.unn.agile.huffman.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.huffman.model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty txt = new SimpleStringProperty();
    private final StringProperty txtEncode = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final BooleanProperty encodingDisabled = new SimpleBooleanProperty();

    private final List<TxtChangeListener> txtChangeListener = new ArrayList<>();

    public ViewModel() {
        txt.set("");
        txtEncode.set("");
        status.set(Status.WAITING.toString());

        this.bindingEncoding();
    }

    public void encode() {
        if (encodingDisabled.get()) {
            return;
        }

        Huffman code = new Huffman(txt.get());

        txtEncode.set(code.getEncodedString());
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty txtProperty() {
        return txt;
    }

    public StringProperty txtEncodeProperty() {
        return txtEncode;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public BooleanProperty encodingDisabledProperty() {
        return encodingDisabled;
    }
    public final boolean isEncodingDisabled() {
        return encodingDisabled.get();
    }

    private Status getInputStatus() {
        if (txt.get().isEmpty()) {
            return Status.WAITING;
        } else {
            return Status.READY;
        }
    }

    private void bindingEncoding() {
        BooleanBinding couldEncode = new BooleanBinding() {
            {
                super.bind(txt);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        encodingDisabled.bind(couldEncode.not());

        final TxtChangeListener listener = new TxtChangeListener();
        txt.addListener(listener);
        txtChangeListener.add(listener);
    }

    private class TxtChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldTxt, final String newTxt) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Input text"),
    READY("Press the button \"Encode\""),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
