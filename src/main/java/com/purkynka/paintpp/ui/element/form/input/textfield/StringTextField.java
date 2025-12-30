package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.validator.TextFieldValidator;
import javafx.beans.property.ReadOnlyDoubleProperty;

public class StringTextField<C> extends TextField<C, String> {
    public StringTextField(FormContext<C> formContext) {
        super(formContext);
    }

    @Override
    protected String parseValue(String stringValue) {
        if (stringValue == null || stringValue.trim().isEmpty()) return null;
        return stringValue;
    }

    @Override
    public StringTextField<C> setLabel(String labelText) {
        super.setLabel(labelText);
        return this;
    }

    @Override
    public StringTextField<C> setPlaceholder(String placeholderText) {
        super.setPlaceholder(placeholderText);
        return this;
    }

    @Override
    public StringTextField<C> setWidth(int width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public StringTextField<C> bindWidth(ReadOnlyDoubleProperty width) {
        super.bindWidth(width);
        return this;
    }

    @Override
    public StringTextField<C> addValidators(TextFieldValidator... validators) {
        super.addValidators(validators);
        return this;
    }

    @Override
    public StringTextField<C> setFormValueSetter(FormValueSetter<C, String> formValueSetter) {
        super.setFormValueSetter(formValueSetter);
        return this;
    }

    @Override
    public StringTextField<C> setStringValue(String value) {
        this.textField.setText(value);
        return this;
    }

    @Override
    public StringTextField<C> setValue(String value) {
        super.setValue(value);
        return this;
    }
}
