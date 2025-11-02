package com.purkynka.paintpp.ui.popup.generateimage;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
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
    private final SizeInput sizeInput;
    private final ChoiceInput<ImageGenerationType> generationTypeInput;

    /**
     * Constructs a new {@link GenerateImagePopupRoot}, with the assigned parent {@link PopupStage}.
     * @param stage The parent popup stage
     */
    public GenerateImagePopupRoot(Stage stage) {
        super(stage, "Generate Image", "Cancel", "Generate");
        
        sizeInput = new SizeInput(gridPane);
        generationTypeInput = new ChoiceInput<>(gridPane, "Generation Type:", ImageGenerationType.class);
        GridPane.setColumnSpan(generationTypeInput.getChoiceBox(), 3);

        if (ConfigManager.isRememberGenerationType()) {
            generationTypeInput.setValue(ConfigManager.getRememberedGenerationType());
            rememberGenerationTypeEventHandlers();
        } else {
            generationTypeInput.setValue(ConfigManager.getPreferredGenerationType());
        }

        if (ConfigManager.isRememberImageSize()) {
            var rememberedImageSize = ConfigManager.getRememberedImageSize();
            sizeInput.getWidthInput().setValue(
                    rememberedImageSize.width()
            );
            sizeInput.getHeightInput().setValue(
                    rememberedImageSize.height()
            );
            rememberSizeEventHandlers();
        }
    }

    @Override
    protected void onCancel() {
        stage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        var maxImageSize = ConfigManager.getMaxImageSize();
        if (imageSize.width() < 1 || imageSize.height() < 1 || imageSize.width() > maxImageSize.width() || imageSize.height() > maxImageSize.height())
            return;
        
        var generationType = generationTypeInput.getValue();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new GeneratedImageProvider(imageSize, generationType));

        stage.close();
    }

    private void rememberGenerationTypeEventHandlers() {
        generationTypeInput.getChoiceBox().setOnAction(_ -> {
            var value = generationTypeInput.getChoiceBox().getValue();
            ConfigManager.setRememberedGenerationType(value);
        });
    }

    private void rememberSizeEventHandlers() {
        var widthInput = sizeInput.getWidthInput();
        var heightInput = sizeInput.getHeightInput();

        widthInput.getValueProperty().addListener(_ -> {
            if (widthInput.isInputValid()) {
                ConfigManager.setRememberedImageWidth(widthInput.getValue());
            }
        });
        heightInput.getValueProperty().addListener(_ -> {
            if (heightInput.isInputValid()) {
                ConfigManager.setRememberedImageHeight(heightInput.getValue());
            }
        });
    }
}
