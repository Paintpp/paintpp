package com.purkynka.paintpp.ui.popup.help;

import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.stage.Stage;

public class HelpPopup extends PopupStage {
    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new HelpPopupRoot(stage);
    }
}
