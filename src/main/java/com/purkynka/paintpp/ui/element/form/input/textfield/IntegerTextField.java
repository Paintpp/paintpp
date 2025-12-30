package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.validator.TextFieldValidator;
import javafx.beans.property.ReadOnlyDoubleProperty;

import java.util.Collections;

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

    @Override
    public IntegerTextField<C> setLabel(String labelText) {
        super.setLabel(labelText);
        return this;
    }

    @Override
    public IntegerTextField<C> setPlaceholder(String placeholderText) {
        super.setPlaceholder(placeholderText);
        return this;
    }

    @Override
    public IntegerTextField<C> setWidth(int width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public IntegerTextField<C> bindWidth(ReadOnlyDoubleProperty width) {
        super.bindWidth(width);
        return this;
    }

    @Override
    public IntegerTextField<C> addValidators(TextFieldValidator... validators) {
        super.addValidators(validators);
        return this;
    }

    @Override
    public IntegerTextField<C> setFormValueSetter(FormValueSetter<C, Integer> formValueSetter) {
        super.setFormValueSetter(formValueSetter);
        return this;
    }

    @Override
    public IntegerTextField<C> setStringValue(String value) {
        this.textField.setText(value);
        return this;
    }

    @Override
    public IntegerTextField<C> setValue(Integer value) {
        super.setValue(value);
        return this;
    }
}
