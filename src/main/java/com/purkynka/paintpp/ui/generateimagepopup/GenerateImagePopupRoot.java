package com.purkynka.paintpp.ui.generateimagepopup;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.imageprovider.GeneratedImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;

/**
 * Element root of the {@link GenerateImagePopup}.
 */
public class GenerateImagePopupRoot extends PopupRoot {
    private final PopupStage popupStage;

    private final SizeInput sizeInput;
    private final ChoiceInput<ImageGenerationType> generationTypeInput;

    /**
     * Constructs a new {@link GenerateImagePopupRoot}, with the assigned parent {@link PopupStage}.
     * @param popupStage The parent popup stage
     */
    public GenerateImagePopupRoot(PopupStage popupStage) {
        super("Generate Image", "Cancel", "Generate");

        this.popupStage = popupStage;

        sizeInput = new SizeInput(gridPane);
        generationTypeInput = new ChoiceInput<>(gridPane, "Generation Type:", ImageGenerationType.class);
    }

    @Override
    protected void onCancel() {
        popupStage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        var generationType = generationTypeInput.getValue();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new GeneratedImageProvider(imageSize, generationType));

        popupStage.close();
    }
}
