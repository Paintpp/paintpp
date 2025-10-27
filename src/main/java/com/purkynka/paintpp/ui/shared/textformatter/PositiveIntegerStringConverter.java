package com.purkynka.paintpp.ui.shared.textformatter;

import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * {@link StringConverter} that only allows positive (without zero) numbers in a {@link TextField}.
 */
public class PositiveIntegerStringConverter extends IntegerStringConverter {
    @Override
    public Integer fromString(String value) {
        int result = super.fromString(value);
        if (result <= 0) {
            throw new RuntimeException();
        }

        return result;
    }

    @Override
    public String toString(Integer value) {
        if (value == null || value <= 0) {
            return "1";
        }

        return super.toString(value);
    }
}
