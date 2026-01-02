package com.purkynka.paintpp.ui.element.form.input.sliderfield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.textfield.IntegerTextField;
import com.purkynka.paintpp.ui.element.form.input.validator.RequiredValidator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class IntegerSliderField<C> extends HBox {
    private IntegerTextField<C> integerTextField;
    private Slider slider;

    private int min;
    private int max;

    private FormContext<C> formContext;

    public IntegerSliderField(FormContext<C> formContext) {
        super(16);

        this.formContext = formContext;

        this.setupContainer();
        this.setupElements();
        this.setupHandlers();

        this.setSnapIncrement(1);
        this.setRange(1, 2);

        this.getChildren().addAll(this.integerTextField, this.slider);
    }

    private void setupContainer() {
        this.setAlignment(Pos.CENTER);
    }

    private void setupElements() {
        this.setupTextField();
        this.setupSlider();
    }

    private void setupTextField() {
        this.integerTextField = new IntegerTextField<>(this.formContext)
                .setWidth(128)
                .addValidators(new RequiredValidator());
    }

    private void setupSlider() {
        this.slider = new Slider();
        this.slider.setMinWidth(512);
        this.slider.setSnapToTicks(true);
        this.slider.setMinorTickCount(0);
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        this.slider.setPadding(new Insets(20, 0, 0, 0));
    }

    private void setupHandlers() {
        this.slider.valueProperty().addListener((_, _, v) -> {
            if ((v.intValue() - min) % this.slider.getMajorTickUnit() != 0) return;

            var newValue = Math.clamp(v.intValue(), this.min, this.max);
            this.integerTextField.setValue(newValue);
        });

        this.integerTextField.getObservableValue().addUpdateListener(v -> {
            if (v == null) return;

            var newValue = Math.clamp(v, this.min, this.max);

            this.integerTextField.setValue(newValue);
            this.slider.setValue(newValue);
        });
    }

    public IntegerSliderField<C> setLabel(String label) {
        this.integerTextField.setLabel(label);
        return this;
    }

    public IntegerSliderField<C> setSnapIncrement(int increment) {
        this.slider.setMajorTickUnit(increment);
        this.slider.setBlockIncrement(increment);

        return this;
    }

    public IntegerSliderField<C> setRange(int min, int max) {
        this.min = min;
        this.max = max;

        this.slider.setMin(min);
        this.slider.setMax(max);

        this.integerTextField.setValuePostprocessor((v) -> Math.clamp(v, this.min, this.max));

        this.integerTextField.setValue(min);
        this.slider.setValue(min);
        return this;
    }

    public IntegerSliderField<C> setFormValueSetter(FormValueSetter<C, Integer> formValueSetter) {
        this.integerTextField.setFormValueSetter(formValueSetter);
        return this;
    }

    public IntegerSliderField<C> setMinorTickCount(int minorTickCount) {
        this.slider.setMinorTickCount(minorTickCount);
        return this;
    }

    public IntegerSliderField<C> setValue(int value) {
        this.integerTextField.setValue(value);
        return this;
    }
}
