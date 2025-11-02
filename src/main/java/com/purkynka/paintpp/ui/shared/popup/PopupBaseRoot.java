package com.purkynka.paintpp.ui.shared.popup;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public abstract class PopupBaseRoot extends VBox {
    public Stage stage;

    public PopupBaseRoot(Stage stage) {
        super();
        this.stage = stage;

        setAlignment(Pos.TOP_LEFT);
    }
}
