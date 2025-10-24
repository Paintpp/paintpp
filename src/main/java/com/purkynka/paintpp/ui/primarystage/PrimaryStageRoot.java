package com.purkynka.paintpp.ui.primarystage;

import com.purkynka.paintpp.ui.primarystage.mainview.MainView;
import com.purkynka.paintpp.ui.primarystage.menubar.MenuBar;
import javafx.scene.layout.VBox;

public class PrimaryStageRoot extends VBox {
    private MenuBar menuBar;
    private MainView mainView;

    public PrimaryStageRoot() {
        menuBar = new MenuBar();
        mainView = new MainView();

        getChildren().addAll(menuBar, mainView);
    }
}
