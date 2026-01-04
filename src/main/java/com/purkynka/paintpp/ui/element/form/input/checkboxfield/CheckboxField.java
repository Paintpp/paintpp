package com.purkynka.paintpp.ui.element.form.input.checkboxfield;

import com.purkynka.paintpp.logic.observable.ObservableValue;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CheckboxField<C> extends HBox {
    private final ObservableValue<Boolean> observableValue = new ObservableValue<>();
    private Label fieldLabel;
    private CheckBox checkBox;
    private FormContext<C> formContext;
    private ObservableValue<C> formContextValue;
    private FormValueSetter<C, Boolean> formValueSetter;

    public CheckboxField() {
        super(8);

        this.setupElements();
        this.setupHandlers();

        this.getChildren().addAll(this.fieldLabel, this.checkBox);
    }

    public CheckboxField(FormContext<C> formContext) {
        this();

        this.setupFormContext(formContext);
    }

    private void setupFormContext(FormContext<C> formContext) {
        this.formContext = formContext;
        this.formContextValue = formContext.getObservableFormValue();
    }

    private void setupElements() {
        this.setupLabel();
        this.setupCheckBox();
    }

    private void setupLabel() {
        this.fieldLabel = new Label();
    }

    private void setupCheckBox() {
        this.checkBox = new CheckBox();
    }

    private void setupHandlers() {
        this.checkBox.selectedProperty().addListener((_, _, v) -> {
            this.observableValue.set(v);

            if (this.formValueSetter != null) {
                var formValue = this.formContextValue.get();
                this.formValueSetter.setValue(formValue, v);
                this.formContextValue.set(formValue);
            }
        });
    }

    public CheckboxField<C> setLabel(String labelText) {
        this.fieldLabel.setText(labelText);
        return this;
    }

    public CheckboxField<C> setFormValueSetter(FormValueSetter<C, Boolean> formValueSetter) {
        this.formValueSetter = formValueSetter;
        return this;
    }

    public ObservableValue<Boolean> getObservableValue() {
        return this.observableValue;
    }

    public Boolean getValue() {
        return this.observableValue.get();
    }

    public CheckboxField<C> setValue(boolean value) {
        this.checkBox.setSelected(value);
        this.observableValue.set(value);

        return this;
    }
}
