package com.purkynka.paintpp.ui.popup.newimage;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.logic.image.imageprovider.NewImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ColorInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import com.purkynka.paintpp.ui.shared.popup.confirmation.PopupConfirmationRoot;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Element root of the {@link NewImagePopup}.
 */
public class NewImagePopupRoot extends PopupConfirmationRoot {
    private final SizeInput sizeInput;
    private final ColorInput fillColorInput;

    /**
     * Constructs a new {@link NewImagePopup}, with the assigned parent {@link PopupStage}.
     * @param stage The parent popup stage
     */
    public NewImagePopupRoot(Stage stage) {
        super(stage, "New Image");

        sizeInput = new SizeInput(gridPane);
        fillColorInput = new ColorInput(gridPane);
        GridPane.setColumnSpan(fillColorInput.getColorPicker(), 3);

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

        if (ConfigManager.isRememberBackgroundColor()) {
            fillColorInput.getColorPicker().setValue(
                    ConfigManager.getRememberedBackgroundColor()
            );
            rememberColorEventHandlers();
        }
    }

    private void rememberColorEventHandlers() {
        fillColorInput.getColorPicker().setOnAction(_ -> {
            var color = fillColorInput.getColorPicker().getValue();
            ConfigManager.setRememberedBackgroundColor(color);
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

        var fillColor = fillColorInput.getColor();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new NewImageProvider(imageSize, fillColor));

        stage.close();
    }
}
