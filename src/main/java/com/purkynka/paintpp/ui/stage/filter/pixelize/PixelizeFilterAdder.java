package com.purkynka.paintpp.ui.stage.filter.pixelize;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.PixelizeFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.sliderinput.IntegerSliderInput;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class PixelizeFilterAdder extends PopupStage {
    private final FormContext<PixelizeFilterFormValue> formContext = new FormContext<>(new PixelizeFilterFormValue());

    private final PixelizeFilter pixelizeFilter = new PixelizeFilter(1);
    private FilterPreview filterPreview;

    public PixelizeFilterAdder() {
        super();

        this.setPopupTitle("Add Pixelize Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.pixelizeFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (v.step == null) return;

            this.pixelizeFilter.setStep(v.step);
            this.filterPreview.refreshImage();
        });
    }

    @Override
    protected Parent createRoot() {
        this.filterPreview = new FilterPreview(this.pixelizeFilter);

        var stepSlider = new IntegerSliderInput<>(this.formContext)
                .setLabel("Step")
                .setSnapIncrement(1)
                .setRange(1, 20)
                .setFormValueSetter((curr, v) -> curr.step = v);

        return new VBox(this.filterPreview, stepSlider);
    }
}
