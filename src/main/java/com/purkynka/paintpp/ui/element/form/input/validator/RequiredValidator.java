package com.purkynka.paintpp.ui.element.form.input.validator;

public class RequiredValidator implements TextFieldValidator {
    @Override
    public String validateTextField(String stringValue, Object value) {
        return stringValue == null || stringValue.trim().isEmpty() ? "This field is required!" : null;
    }
}
