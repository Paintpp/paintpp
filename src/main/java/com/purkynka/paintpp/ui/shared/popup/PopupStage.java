package com.purkynka.paintpp.ui.shared.popup;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Wrapper {@link Stage} class for a popup window.
 */
public abstract class PopupStage extends Stage {
    /**
     * Constructs a new {@link PopupStage}, owned by the provided {@link Stage}.
     * @param ownerStage The owner stage of this popup
     */
    public PopupStage(Stage ownerStage) {
        super();

        initOwner(ownerStage);
        initModality(Modality.APPLICATION_MODAL);

        setResizable(false);

        var root = constructRoot(this);
        var scene = new Scene(root);
        setScene(scene);
        ConfigManager.applyFont(this);
        
        showAndWait();

    }

    /**
     * Constructor overload that uses the {@link PrimaryStage#PRIMARY_STAGE} as the owner stage.
     */
    public PopupStage() {
        this(PrimaryStage.PRIMARY_STAGE);
    }

    protected abstract PopupBaseRoot constructRoot(Stage stage);
}
