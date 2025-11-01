package com.purkynka.paintpp.ui.popup.newimage;

import com.purkynka.paintpp.logic.image.imageprovider.NewImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ColorInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import com.purkynka.paintpp.ui.shared.popup.confirmation.PopupConfirmationRoot;
import javafx.stage.Stage;

/**
 * Element root of the {@link NewImagePopup}.
 */
public class NewImagePopupRoot extends PopupConfirmationRoot {
    private final Stage stage;

    private final SizeInput sizeInput;
    private final ColorInput fillColorInput;

    /**
     * Constructs a new {@link NewImagePopup}, with the assigned parent {@link PopupStage}.
     * @param stage The parent popup stage
     */
    public NewImagePopupRoot(Stage stage) {
        super(stage, "New Image");

        this.stage = stage;

        sizeInput = new SizeInput(gridPane);
        fillColorInput = new ColorInput(gridPane);
    }

    @Override
    protected void onCancel() {
        stage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        var fillColor = fillColorInput.getColor();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new NewImageProvider(imageSize, fillColor));

        stage.close();
    }
}
