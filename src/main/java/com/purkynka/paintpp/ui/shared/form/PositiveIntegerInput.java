package com.purkynka.paintpp.ui.shared.form;

import com.purkynka.paintpp.ui.shared.textformatter.PositiveIntegerTextFormatter;
import javafx.scene.control.TextField;

/**
 * Wrapper class for a {@link TextField}.
 * <p>
 * Sets up a {@link PositiveIntegerTextFormatter} for the {@link TextField}.
 */
public class PositiveIntegerInput extends TextField {
    private final PositiveIntegerTextFormatter textFormatter;

    /**
     * Constructs a new {@link PositiveIntegerInput}.
     * @param defaultValue The default value of the text field
     */
    public PositiveIntegerInput(String defaultValue) {
        super();

        textFormatter = new PositiveIntegerTextFormatter();
        setTextFormatter(textFormatter);

        setText(defaultValue);
    }

    /**
     * Returns the currently written number.
     * @return The written number
     */
    public int getValue() {
        return textFormatter.getValue();
    }
}
