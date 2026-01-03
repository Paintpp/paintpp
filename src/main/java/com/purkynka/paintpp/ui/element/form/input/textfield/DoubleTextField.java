package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.validator.TextFieldValidator;
import javafx.beans.property.ReadOnlyDoubleProperty;

import java.util.function.Function;

public class DoubleTextField<C> extends TextField<C, Double> {
    public DoubleTextField(FormContext<C> formContext) {
        super(formContext);
    }

    @Override
    protected Double parseValue(String stringValue) {
        if (stringValue == null) return null;

        try {
            return Double.parseDouble(stringValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public DoubleTextField<C> setLabel(String labelText) {
        super.setLabel(labelText);
        return this;
    }

    @Override
    public DoubleTextField<C> removeLabel() {
        super.removeLabel();
        return this;
    }

    @Override
    public DoubleTextField<C> setPlaceholder(String placeholderText) {
        super.setPlaceholder(placeholderText);
        return this;
    }

    @Override
    public DoubleTextField<C> setWidth(int width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public DoubleTextField<C> bindWidth(ReadOnlyDoubleProperty width) {
        super.bindWidth(width);
        return this;
    }

    @Override
    public DoubleTextField<C> addValidators(TextFieldValidator... validators) {
        super.addValidators(validators);
        return this;
    }

    @Override
    public DoubleTextField<C> setFormValueSetter(FormValueSetter<C, Double> formValueSetter) {
        super.setFormValueSetter(formValueSetter);
        return this;
    }

    @Override
    public DoubleTextField<C> setValuePostprocessor(Function<Double, Double> valuePostprocessor) {
        super.setValuePostprocessor(valuePostprocessor);
        return this;
    }

    @Override
    public DoubleTextField<C> setStringValue(String value) {
        this.textField.setText(value);
        return this;
    }

    @Override
    public DoubleTextField<C> setValue(Double value) {
        super.setValue(value);
        return this;
    }
}
