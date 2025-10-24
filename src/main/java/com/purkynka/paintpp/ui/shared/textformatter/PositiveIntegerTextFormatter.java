package com.purkynka.paintpp.ui.shared.textformatter;

import javafx.scene.control.TextFormatter;

public class PositiveIntegerTextFormatter extends TextFormatter<Integer> {
    public PositiveIntegerTextFormatter() {
        super(new PositiveIntegerStringConverter());
    }
}
