package com.purkynka.paintpp.ui.stage.filter.threshold;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.ThresholdFilter;
import com.purkynka.paintpp.logic.util.ColorUtil;
import com.purkynka.paintpp.logic.util.ImageUtil;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.checkboxfield.CheckboxField;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.awt.*;

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

    private int getImageAverage() {
        var currentImage = ImageUtil.getCurrentImage();
        var imageSize = currentImage.getImageSize();
        var intBuffer = currentImage.getPixelIntBuffer();

        var averageColor = 0;

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));
            var averagedColor = ColorUtil.getAveragedColor(color);
            averageColor += averagedColor.getRed();
        }

        return averageColor / imageSize.totalPixels();
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

        var automaticThresholdCheckbox = new CheckboxField<>()
                .setLabel("Automatic Threshold Value");

        automaticThresholdCheckbox.setAlignment(Pos.CENTER);

        automaticThresholdCheckbox.getObservableValue().addUpdateListener(v -> {
            thresholdSlider.setDisable(v);
            if (v) thresholdSlider.setValue(this.getImageAverage());
        });

        return new VBox(16, this.filterPreview, thresholdSlider, automaticThresholdCheckbox);
    }
}
