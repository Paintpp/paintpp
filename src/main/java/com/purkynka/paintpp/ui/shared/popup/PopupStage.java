package com.purkynka.paintpp.ui.shared.popup;

import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class PopupStage extends Stage {
    public PopupStage(Stage ownerStage) {
        super();

        initOwner(ownerStage);
        initModality(Modality.APPLICATION_MODAL);

        var root = constructRoot(this);
        var scene = new Scene(root);
        setScene(scene);

        setResizable(false);

        showAndWait();
    }

    public PopupStage() {
        this(PrimaryStage.PRIMARY_STAGE);
    }

    protected abstract PopupRoot constructRoot(PopupStage popupStage);
}
