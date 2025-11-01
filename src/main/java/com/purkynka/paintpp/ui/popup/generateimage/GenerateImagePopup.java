package com.purkynka.paintpp.ui.popup.generateimage;

import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * {@link PopupStage} shown when the user wants to generate a new {@link Image}.
 */
public class GenerateImagePopup extends PopupStage {
    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new GenerateImagePopupRoot(stage);
    }
}
