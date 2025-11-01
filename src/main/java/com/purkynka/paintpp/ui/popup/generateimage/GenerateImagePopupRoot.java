package com.purkynka.paintpp.ui.popup.generateimage;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.imageprovider.GeneratedImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import com.purkynka.paintpp.ui.shared.popup.confirmation.PopupConfirmationRoot;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Element root of the {@link GenerateImagePopup}.
 */
public class GenerateImagePopupRoot extends PopupConfirmationRoot {
    private final Stage stage;

    private final SizeInput sizeInput;
    private final ChoiceInput<ImageGenerationType> generationTypeInput;

    /**
     * Constructs a new {@link GenerateImagePopupRoot}, with the assigned parent {@link PopupStage}.
     * @param stage The parent popup stage
     */
    public GenerateImagePopupRoot(Stage stage) {
        super(stage, "Generate Image", "Cancel", "Generate");
        this.stage = stage;
        
        sizeInput = new SizeInput(gridPane);
        generationTypeInput = new ChoiceInput<>(gridPane, "Generation Type:", ImageGenerationType.class);
        GridPane.setColumnSpan(generationTypeInput.getChoiceBox(), 3);
    }

    @Override
    protected void onCancel() {
        stage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        if (imageSize.width() <= 0 || imageSize.height() <= 0) return;
        
        var generationType = generationTypeInput.getValue();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new GeneratedImageProvider(imageSize, generationType));

        stage.close();
    }
}
