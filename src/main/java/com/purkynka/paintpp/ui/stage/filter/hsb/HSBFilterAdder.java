package com.purkynka.paintpp.ui.stage.filter.hsb;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.HSBFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class HSBFilterAdder extends PopupStage {
    private final FormContext<HSBFilterFormValue> formContext = new FormContext<>(new HSBFilterFormValue());

    private final HSBFilter hsbFilter = new HSBFilter(0, 0, 0);
    private FilterPreview filterPreview;

    public HSBFilterAdder() {
        super();

        this.setPopupTitle("Add HSB Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.hsbFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.hueModifier == null || v.saturationModifier == null || v.brightnessModifier == null) return;

            this.hsbFilter.setHueModifier(v.hueModifier);
            this.hsbFilter.setSaturationModifier(v.saturationModifier);
            this.hsbFilter.setBrightnessModifier(v.brightnessModifier);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.hsbFilter);

        var hueModifierSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Hue")
                .setSnapIncrement(1)
                .setRange(0, 360)
                .setFormValueSetter((curr, v) -> curr.hueModifier = v);

        var saturationModifierSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Saturation")
                .setSnapIncrement(1)
                .setRange(-100, 100)
                .setValue(0)
                .setFormValueSetter((curr, v) -> curr.saturationModifier = v);

        var brightnessModifierSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Brightness")
                .setSnapIncrement(1)
                .setRange(-100, 100)
                .setValue(0)
                .setFormValueSetter((curr, v) -> curr.brightnessModifier = v);

        return new VBox(16, this.filterPreview, hueModifierSlider, saturationModifierSlider, brightnessModifierSlider);
    }
}
