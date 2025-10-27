package com.purkynka.paintpp.ui.generateimagepopup;

import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;

/**
 * {@link PopupStage} shown when the user wants to generate a new {@link Image}.
 */
public class GenerateImagePopup extends PopupStage {
    @Override
    protected PopupRoot constructRoot(PopupStage popupStage) {
        return new GenerateImagePopupRoot(popupStage);
    }
}
