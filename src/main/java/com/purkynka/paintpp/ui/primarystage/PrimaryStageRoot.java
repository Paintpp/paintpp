package com.purkynka.paintpp.ui.primarystage;

import com.purkynka.paintpp.ui.primarystage.mainview.MainView;
import com.purkynka.paintpp.ui.primarystage.menubar.MenuBar;
import javafx.scene.layout.VBox;

/**
 * Element root of the {@link PrimaryStage}.
 */
public class PrimaryStageRoot extends VBox {
    private final MenuBar menuBar;
    private final MainView mainView;

    /**
     * Constructs a new {@link PrimaryStageRoot}.
     */
    public PrimaryStageRoot() {
        menuBar = new MenuBar();
        mainView = new MainView();

        getChildren().addAll(menuBar, mainView);
    }
}
