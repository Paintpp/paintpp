package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;

public class IntegerTextField<C> extends TextField<C, Integer> {
    public IntegerTextField(FormContext<C> formContext) {
        super(formContext);
    }

    @Override
    protected Integer parseValue(String stringValue) {
        try {
            return Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
