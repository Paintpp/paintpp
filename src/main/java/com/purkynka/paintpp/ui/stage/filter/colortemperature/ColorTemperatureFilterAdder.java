package com.purkynka.paintpp.ui.stage.filter.colortemperature;

import com.purkynka.paintpp.logic.filter.ColorTemperatureFilter;
import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ColorTemperatureFilterAdder extends PopupStage {
    private final FormContext<ColorTemperatureFormValue> formContext = new FormContext<>(new ColorTemperatureFormValue());

    private final ColorTemperatureFilter colorTemperatureFilter = new ColorTemperatureFilter(0);
    private FilterPreview filterPreview;

    public ColorTemperatureFilterAdder() {
        super();

        this.setPopupTitle("Add Color Temperature Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.colorTemperatureFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.temperatureModifier == null) return;

            this.colorTemperatureFilter.adjustModifiers(v.temperatureModifier);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.colorTemperatureFilter);

        var temperatureModifierSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Temperature")
                .setSnapIncrement(1)
                .setRange(-5, 5)
                .setValue(0)
                .setFormValueSetter((curr, v) -> curr.temperatureModifier = v);

        return new VBox(this.filterPreview, temperatureModifierSlider);
    }
}
