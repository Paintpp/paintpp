package com.purkynka.paintpp.ui.popup.newimage;

import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * {@link PopupStage} shown when the user wants to create a new empty {@link Image}.
 */
public class NewImagePopup extends PopupStage {
    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new NewImagePopupRoot(stage);
    }
}
