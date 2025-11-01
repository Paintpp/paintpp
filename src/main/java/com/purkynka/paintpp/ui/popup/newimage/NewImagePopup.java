package com.purkynka.paintpp.ui.popup.newimage;

import com.purkynka.paintpp.ui.shared.popup.PopupConfirmationRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;

/**
 * {@link PopupStage} shown when the user wants to create a new empty {@link Image}.
 */
public class NewImagePopup extends PopupStage {
    @Override
    protected PopupConfirmationRoot constructRoot(PopupStage popupStage) {
        return new NewImagePopupConfirmationRoot(popupStage);
    }
}
