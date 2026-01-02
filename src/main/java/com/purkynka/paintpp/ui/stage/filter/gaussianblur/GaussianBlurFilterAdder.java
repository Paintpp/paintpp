package com.purkynka.paintpp.ui.stage.filter.gaussianblur;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.GaussianBlurFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class GaussianBlurFilterAdder extends PopupStage {
    private final FormContext<GaussianBlurFormValue> formContext = new FormContext<>(new GaussianBlurFormValue());

    private final GaussianBlurFilter gaussianBlurFilter = new GaussianBlurFilter(3, 1);
    private FilterPreview filterPreview;

    public GaussianBlurFilterAdder() {
        super();

        this.setPopupTitle("Add Gaussian Blur Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.gaussianBlurFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.blurStrength == null) return;

            this.gaussianBlurFilter.setKernelSize(3 + v.blurStrength * 2);
            this.gaussianBlurFilter.setSigma(v.blurStrength);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.gaussianBlurFilter);

        var blurStrengthSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Blur Strength")
                .setSnapIncrement(1)
                .setRange(1, 10)
                .setFormValueSetter((curr, v) -> curr.blurStrength = v);

        return new VBox(16, this.filterPreview, blurStrengthSlider);
    }
}