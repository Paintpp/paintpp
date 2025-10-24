package com.purkynka.paintpp.ui.shared.textformatter;

import javafx.util.converter.IntegerStringConverter;

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
