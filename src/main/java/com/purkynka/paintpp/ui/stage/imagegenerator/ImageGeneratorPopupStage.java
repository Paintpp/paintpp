package com.purkynka.paintpp.ui.stage.imagegenerator;

import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.image.provider.GeneratedImageProvider;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.choicefield.ChoiceField;
import com.purkynka.paintpp.ui.element.form.input.sizefield.SizeField;
import com.purkynka.paintpp.ui.element.form.input.validator.RequiredValidator;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ImageGeneratorPopupStage extends PopupStage {
    private final FormContext<ImageGeneratorFormValue> formContext = new FormContext<>(new ImageGeneratorFormValue());

    public ImageGeneratorPopupStage() {
        super();

        this.setPopupTitle("Generate Image");
        this.setShowTitle(true);
        this.addDefaultButtons(this::onSubmit);

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });
    }

    private void onSubmit() {
        var formValue = this.formContext.getObservableFormValue().get();
        ImageManager.IMAGE_PROVIDER.set(new GeneratedImageProvider(formValue.generationType, formValue.imageSize));

        this.close();
    }

    @Override
    protected Parent createRoot() {
        var container = new VBox(16);
        container.setMinHeight(200);

        var imageSizeInput = new SizeField<>(this.formContext,
                (curr, width) -> curr.imageSize.width = width,
                (curr, height) -> curr.imageSize.height = height
        );

        var generationTypeInput = new ChoiceField<>(this.formContext, GenerationType.class)
                .bindWidth(container.widthProperty())
                .setLabel("Generation Type")
                .setPlaceholder("X Position...")
                .addValidators(new RequiredValidator())
                .setFormValueSetter((curr, v) -> curr.generationType = v);

        container.getChildren().addAll(imageSizeInput, generationTypeInput);

        return container;
    }
}
