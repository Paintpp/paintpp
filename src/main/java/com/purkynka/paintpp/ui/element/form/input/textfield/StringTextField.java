package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;

public class StringTextField<C> extends TextField<C, String> {
    public StringTextField(FormContext<C> formContext) {
        super(formContext);
    }

    @Override
    protected String parseValue(String stringValue) {
        if (stringValue == null || stringValue.trim().isEmpty()) return null;
        return stringValue;
    }
}
