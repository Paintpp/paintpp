package com.purkynka.paintpp.ui.popup.generateimage;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.imageprovider.GeneratedImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupConfirmationRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.layout.GridPane;

/**
 * Element root of the {@link GenerateImagePopup}.
 */
public class GenerateImagePopupConfirmationRoot extends PopupConfirmationRoot {
    private final PopupStage popupStage;

    private final SizeInput sizeInput;
    private final ChoiceInput<ImageGenerationType> generationTypeInput;

    /**
     * Constructs a new {@link GenerateImagePopupConfirmationRoot}, with the assigned parent {@link PopupStage}.
     * @param popupStage The parent popup stage
     */
    public GenerateImagePopupConfirmationRoot(PopupStage popupStage) {
        super(popupStage, "Generate Image", "Cancel", "Generate");
        this.popupStage = popupStage;
        
        sizeInput = new SizeInput(gridPane);
        generationTypeInput = new ChoiceInput<>(gridPane, "Generation Type:", ImageGenerationType.class);
        GridPane.setColumnSpan(generationTypeInput.getChoiceBox(), 3);
    }

    @Override
    protected void onCancel() {
        popupStage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        if (imageSize.width() <= 0 || imageSize.height() <= 0) return;
        
        var generationType = generationTypeInput.getValue();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new GeneratedImageProvider(imageSize, generationType));

        popupStage.close();
    }
}
