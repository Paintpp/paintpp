package com.purkynka.paintpp.ui.primarystage;

import com.purkynka.paintpp.ui.primarystage.menubar.MenuBar;
import javafx.scene.layout.VBox;

public class PrimaryStageRoot extends VBox {
    private MenuBar menuBar;

    public PrimaryStageRoot() {
        menuBar = new MenuBar();

        getChildren().addAll(menuBar);
    }
}
