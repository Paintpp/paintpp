package com.purkynka.paintpp.ui.stage.filter.noise;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.NoiseFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NoiseFilterAdder extends PopupStage {
    private final FormContext<NoiseFilterFormValue> formContext = new FormContext<>(new NoiseFilterFormValue());

    private final NoiseFilter noiseFilter = new NoiseFilter(0, 0);
    private FilterPreview filterPreview;

    public NoiseFilterAdder() {
        super();

        this.setPopupTitle("Add Noise Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.noiseFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.noiseAmount == null || v.noiseStrength == null) return;

            this.noiseFilter.setNoiseAmount(v.noiseAmount);
            this.noiseFilter.setNoiseStrength(v.noiseStrength);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.noiseFilter);

        var noiseAmountSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Amount")
                .setSnapIncrement(1)
                .setRange(0, 100)
                .setFormValueSetter((curr, v) -> curr.noiseAmount = v);

        var noiseStrengthSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Strength")
                .setSnapIncrement(1)
                .setRange(0, 100)
                .setFormValueSetter((curr, v) -> curr.noiseStrength = v);

        return new VBox(16, this.filterPreview, noiseAmountSlider, noiseStrengthSlider);
    }
}
