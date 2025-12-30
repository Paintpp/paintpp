package com.purkynka.paintpp.ui.element.form.input.textfield;

import com.purkynka.paintpp.logic.observable.ObservableHashSet;
import com.purkynka.paintpp.logic.observable.ObservableValue;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.validator.TextFieldValidator;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public abstract class TextField<C, V> extends Pane {
    protected static final double TEXT_FIELD_HEIGHT = 40d;

    protected static final PseudoClass INVALID = PseudoClass.getPseudoClass("invalid");
    protected static final PseudoClass VALID = PseudoClass.getPseudoClass("valid");

    protected javafx.scene.control.TextField textField;
    protected Label fieldLabel;

    protected FontIcon errorIcon;
    protected Tooltip errorTooltip;
    protected ArrayList<TextFieldValidator> validators = new ArrayList<>();
    protected boolean hasErrors;

    protected String elementUUID = UUID.randomUUID().toString();
    protected ObservableValue<C> observableFormValue;
    protected ObservableHashSet<String> observableFormErrors;
    protected FormValueSetter<C, V> formValueSetter;

    protected ObservableValue<String> observableStringValue = new ObservableValue<>(null);
    protected ObservableValue<V> observableValue = new ObservableValue<>(null);

    public TextField(FormContext<C> formContext) {
        super();

        this.setupFormObservables(formContext);

        this.setupContainer();
        this.setupElements();
        this.setupListeners();

        this.onValueChanged();

        this.getChildren().addAll(this.textField, this.fieldLabel, this.errorIcon);
    }

    private void setupFormObservables(FormContext<C> formContext) {
        this.observableFormValue = formContext.getObservableFormValue();
        this.observableFormErrors = formContext.getObservableFormErrors();
    }

    private void setupContainer() {
        this.setMinHeight(TextField.TEXT_FIELD_HEIGHT);
        this.setMaxHeight(TextField.TEXT_FIELD_HEIGHT);

        this.setWidth(256);
        this.getStyleClass().add("text-field");
    }

    private void setupElements() {
        this.setupTextField();
        this.setupFieldLabel();
        this.setupErrorElements();
    }

    private void setupTextField() {
        this.textField = new javafx.scene.control.TextField();

        this.textField.setPadding(new Insets(0, 12, 0, 12));
        this.textField.minWidthProperty().bind(this.widthProperty());
        this.textField.maxWidthProperty().bind(this.widthProperty());
        this.textField.minHeightProperty().bind(this.heightProperty());
        this.textField.maxHeightProperty().bind(this.heightProperty());

        this.textField.getStyleClass().add("text-field-input");
    }

    private void setupFieldLabel() {
        this.fieldLabel = new Label();

        this.fieldLabel.setPadding(new Insets(0, 4, 0, 4));
        this.fieldLabel.setMouseTransparent(true);
        this.fieldLabel.relocate(9, -10);
        this.fieldLabel.getStyleClass().add("text-field-label");
    }

    private void setupErrorElements() {
        this.errorIcon = new FontIcon(MaterialDesignA.ALERT_BOX);

        this.errorIcon.setMouseTransparent(true);
        this.errorIcon.setIconSize(24);
        this.errorIcon.layoutXProperty().bind(this.widthProperty().subtract(24 + 8));
        this.errorIcon.setLayoutY(29);
        this.errorIcon.getStyleClass().add("text-field-error-icon");

        this.errorTooltip = new Tooltip();
    }

    private void setupListeners() {
        this.textField.textProperty().addListener((_, _, v) -> this.onValueChanged(v));
    }

    private void onValueChanged(String stringValue) {
        this.observableStringValue.set(stringValue);

        var value = this.parseValue(stringValue);
        this.observableValue.set(value);

        this.recheckValidity(stringValue, value);
        this.setFormValue(value);
    }

    private void onValueChanged() {
        this.onValueChanged(this.observableStringValue.get());
    }

    private void recheckValidity(String stringValue, V value) {
        var errors = new ArrayList<String>();

        if (value == null) errors.add("Invalid value format!");

        for (var validator : validators) {
            var returnedError = validator.validateTextField(stringValue, value);
            if (returnedError != null) errors.add(returnedError);
        }

        this.hasErrors = !errors.isEmpty();

        if (this.hasErrors) {
            this.pseudoClassStateChanged(TextField.INVALID, true);
            this.pseudoClassStateChanged(TextField.VALID, false);

            this.errorTooltip.setText(String.join("\n", errors));
            Tooltip.install(this.textField, this.errorTooltip);

            this.observableFormErrors.add(this.elementUUID);
        } else {
            this.pseudoClassStateChanged(TextField.INVALID, false);
            this.pseudoClassStateChanged(TextField.VALID, true);

            Tooltip.uninstall(this.textField, this.errorTooltip);

            this.observableFormErrors.remove(this.elementUUID);
        }
    }

    private void recheckValidity() {
        this.recheckValidity(this.observableStringValue.get(), this.observableValue.get());
    }

    private void setFormValue(V value) {
        if (this.formValueSetter == null) return;

        var formValue = this.observableFormValue.get();
        this.formValueSetter.setValue(formValue, this.hasErrors ? null : value);
        this.observableFormValue.set(formValue);
    }

    private void setFormValue() {
        this.setFormValue(this.observableValue.get());
    }

    protected abstract V parseValue(String stringValue);

    public TextField<C, V> setLabel(String labelText) {
        this.fieldLabel.setText(labelText);

        return this;
    }

    public TextField<C, V> setPlaceholder(String placeholderText) {
        this.textField.setPromptText(placeholderText);

        return this;
    }

    public TextField<C, V> setWidth(int width) {
        this.setMinWidth(width);
        this.setMaxWidth(width);

        return this;
    }

    public TextField<C, V> addValidators(TextFieldValidator... validators) {
        Collections.addAll(this.validators, validators);
        this.recheckValidity();

        return this;
    }

    public TextField<C, V> setFormValueSetter(FormValueSetter<C, V> formValueSetter) {
        this.formValueSetter = formValueSetter;
        this.setFormValue();

        return this;
    }

    public TextField<C, V> setStringValue(String value) {
        this.textField.setText(value);
        return this;
    }

    public TextField<C, V> setValue(V value) {
        this.textField.setText(value == null ? null : value.toString());

        return this;
    }

    public String getStringValue() {
        return this.observableStringValue.get();
    }

    public V getValue() {
        return this.observableValue.get();
    }
}
