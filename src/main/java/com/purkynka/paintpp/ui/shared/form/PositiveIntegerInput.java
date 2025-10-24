package com.purkynka.paintpp.ui.shared.form;

import com.purkynka.paintpp.ui.shared.textformatter.PositiveIntegerTextFormatter;
import javafx.scene.control.TextField;

public class PositiveIntegerInput extends TextField {
    private PositiveIntegerTextFormatter textFormatter;

    public PositiveIntegerInput(String defaultValue) {
        super();

        textFormatter = new PositiveIntegerTextFormatter();
        setTextFormatter(textFormatter);

        setText(defaultValue);
    }

    public int getValue() {
        return textFormatter.getValue();
    }
}
