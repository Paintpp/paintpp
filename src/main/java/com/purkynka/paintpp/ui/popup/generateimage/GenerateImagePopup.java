package com.purkynka.paintpp.ui.popup.generateimage;

import com.purkynka.paintpp.ui.shared.popup.PopupConfirmationRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;

/**
 * {@link PopupStage} shown when the user wants to generate a new {@link Image}.
 */
public class GenerateImagePopup extends PopupStage {
    @Override
    protected PopupConfirmationRoot constructRoot(PopupStage popupStage) {
        return new GenerateImagePopupConfirmationRoot(popupStage);
    }
}
