package com.purkynka.paintpp.ui.generateimagepopup;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.imageprovider.GeneratedImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;

public class GenerateImagePopupRoot extends PopupRoot {
    private PopupStage popupStage;

    private SizeInput sizeInput;
    private ChoiceInput<ImageGenerationType> generationTypeInput;

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
