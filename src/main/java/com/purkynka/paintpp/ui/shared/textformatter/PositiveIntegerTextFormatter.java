package com.purkynka.paintpp.ui.shared.textformatter;

import javafx.scene.control.TextFormatter;

/**
 * Wrapper {@link TextFormatter} that holds a {@link PositiveIntegerStringConverter}.
 */
public class PositiveIntegerTextFormatter extends TextFormatter<Integer> {
    /**
     * Constructs a new {@link PositiveIntegerTextFormatter}.
     */
    public PositiveIntegerTextFormatter() {
        super(new PositiveIntegerStringConverter());
    }
}
