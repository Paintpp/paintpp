package com.purkynka.paintpp.ui.stage.filter.colorizer;

import com.purkynka.paintpp.logic.filter.ColorizerFilter;
import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderfield.IntegerSliderField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.awt.*;

public class ColorizerFilterAdder extends PopupStage {
    private final FormContext<ColorizerFormValue> formContext = new FormContext<>(new ColorizerFormValue());

    private final ColorizerFilter colorizerFilter = new ColorizerFilter(0, 0, 0);
    private FilterPreview filterPreview;

    public ColorizerFilterAdder() {
        super();

        this.setPopupTitle("Add Noise Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.colorizerFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.redAmount == null || v.greenAmount == null || v.blueAmount == null) return;

            this.colorizerFilter.setRedAmount(v.redAmount);
            this.colorizerFilter.setGreenAmount(v.greenAmount);
            this.colorizerFilter.setBlueAmount(v.blueAmount);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.colorizerFilter);

        var redAmountSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Red Amount")
                .setSnapIncrement(1)
                .setRange(0, 255)
                .setFormValueSetter((curr, v) -> curr.redAmount = v);

        var greenAmountSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Green Amount")
                .setSnapIncrement(1)
                .setRange(0, 255)
                .setFormValueSetter((curr, v) -> curr.greenAmount = v);

        var blueAmountSlider = new IntegerSliderField<>(this.formContext)
                .setLabel("Blue Amount")
                .setSnapIncrement(1)
                .setRange(0, 255)
                .setFormValueSetter((curr, v) -> curr.blueAmount = v);

        return new VBox(16, this.filterPreview, redAmountSlider, greenAmountSlider, blueAmountSlider);
    }
}
