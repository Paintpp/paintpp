package com.purkynka.paintpp.ui.popup.about;

import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.stage.Stage;

public class AboutPopup extends PopupStage {
    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new AboutPopupRoot(stage);
    }
}
