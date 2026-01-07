package com.purkynka.paintpp.ui.element.form.input.validator;

public class PositiveIntegerValidator implements TextFieldValidator {
    @Override
    public String validateTextField(String stringValue, Object value) {
        if (value == null) return null;

        if (value instanceof Integer integer) {
            if (integer > 0) return null;
            else return "Value needs to be a positive integer!";
        }

        return "Value is not an integer!";
    }
}
