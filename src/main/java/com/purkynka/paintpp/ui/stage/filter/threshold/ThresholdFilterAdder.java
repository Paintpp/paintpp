package com.purkynka.paintpp.ui.stage.filter.threshold;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.ThresholdFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ThresholdFilterAdder extends PopupStage {
    private final FormContext<ThresholdFilterFormValue> formContext = new FormContext<>(new ThresholdFilterFormValue());

    private final ThresholdFilter thresholdFilter = new ThresholdFilter(1);
    private FilterPreview filterPreview;

    public ThresholdFilterAdder() {
        super();

        this.setPopupTitle("Add Threshold Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.thresholdFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.threshold == null) return;

            this.thresholdFilter.setThreshold(v.threshold);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.thresholdFilter);

        var thresholdSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Threshold")
                .setSnapIncrement(5)
                .setMinorTickCount(3)
                .setRange(0, 255)
                .setFormValueSetter((curr, v) -> curr.threshold = v);

        return new VBox(this.filterPreview, thresholdSlider);
    }
}
