package com.purkynka.paintpp.ui.primarystage;

import com.purkynka.paintpp.ui.primarystage.mainview.MainView;
import com.purkynka.paintpp.ui.primarystage.menubar.ToolBar;
import javafx.scene.layout.VBox;

/**
 * Element root of the {@link PrimaryStage}.
 */
public class PrimaryStageRoot extends VBox {
    private final ToolBar toolBar;
    private final MainView mainView;

    /**
     * Constructs a new {@link PrimaryStageRoot}.
     */
    public PrimaryStageRoot() {
        toolBar = new ToolBar();
        mainView = new MainView();

        getChildren().addAll(toolBar, mainView);
    }
}
