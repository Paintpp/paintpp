package com.purkynka.paintpp.ui.newimagepopup;

import com.purkynka.paintpp.logic.image.imageprovider.NewImageProvider;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.shared.form.ColorInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;

/**
 * Element root of the {@link NewImagePopup}.
 */
public class NewImagePopupRoot extends PopupRoot {
    private PopupStage popupStage;

    private SizeInput sizeInput;
    private ColorInput fillColorInput;

    /**
     * Constructs a new {@link NewImagePopup}, with the assigned parent {@link PopupStage}.
     * @param popupStage The parent popup stage
     */
    public NewImagePopupRoot(PopupStage popupStage) {
        super("New Image");

        this.popupStage = popupStage;

        sizeInput = new SizeInput(gridPane);
        fillColorInput = new ColorInput(gridPane);
    }

    @Override
    protected void onCancel() {
        popupStage.close();
    }

    @Override
    protected void onSubmit() {
        var imageSize = sizeInput.getSize();
        var fillColor = fillColorInput.getColor();

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new NewImageProvider(imageSize, fillColor));

        popupStage.close();
    }
}
