package com.purkynka.paintpp.ui.stage.main;

import com.purkynka.paintpp.ui.element.actionbar.ActionBar;
import com.purkynka.paintpp.ui.element.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.element.sidebar.Sidebar;
import com.purkynka.paintpp.ui.element.statusbar.StatusBar;
import javafx.application.Platform;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainRoot extends HBox {
    private final ActionBar actionBar;
    private final SplitPane contentSplitPane;

    private final ImageViewer imageViewer;
    private final StatusBar statusBar;
    private final VBox centerVBox;

    private final Sidebar sidebar;

    public MainRoot() {
        super();

        this.actionBar = new ActionBar();

        this.imageViewer = new ImageViewer();
        this.statusBar = new StatusBar();
        this.centerVBox = new VBox(this.imageViewer, this.statusBar);

        this.sidebar = new Sidebar();

        this.contentSplitPane = new SplitPane(this.centerVBox, this.sidebar);
        this.contentSplitPane.getStyleClass().add("content-split-pane");

        HBox.setHgrow(this.actionBar, Priority.NEVER);
        HBox.setHgrow(this.contentSplitPane, Priority.ALWAYS);

        getChildren().addAll(this.actionBar, this.contentSplitPane);
        Platform.runLater(() -> this.contentSplitPane.setDividerPositions(0));
    }
}
