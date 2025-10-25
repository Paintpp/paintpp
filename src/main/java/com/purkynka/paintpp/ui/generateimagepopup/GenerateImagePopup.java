package com.purkynka.paintpp.ui.generateimagepopup;

import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;

public class GenerateImagePopup extends PopupStage {
    @Override
    protected PopupRoot constructRoot(PopupStage popupStage) {
        return new GenerateImagePopupRoot(popupStage);
    }
}
