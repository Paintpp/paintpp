package com.purkynka.paintpp.ui.newimagepopup;

import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;

/**
 * {@link PopupStage} shown when the user wants to create a new empty {@link Image}.
 */
public class NewImagePopup extends PopupStage {
    @Override
    protected PopupRoot constructRoot(PopupStage popupStage) {
        return new NewImagePopupRoot(popupStage);
    }
}
