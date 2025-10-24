package com.purkynka.paintpp.ui.newimagepopup;

import com.purkynka.paintpp.ui.shared.popup.PopupRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;

public class NewImagePopup extends PopupStage {
    @Override
    protected PopupRoot constructRoot(PopupStage popupStage) {
        return new NewImagePopupRoot(popupStage);
    }
}
