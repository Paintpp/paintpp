package com.purkynka.paintpp.ui.stage.main;

import com.purkynka.paintpp.ui.element.actionbar.ActionBar;
import com.purkynka.paintpp.ui.element.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.element.menubar.MenuBar;
import com.purkynka.paintpp.ui.element.sidebar.Sidebar;
import com.purkynka.paintpp.ui.element.statusbar.StatusBar;
import javafx.application.Platform;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainRoot extends VBox {
    private final MenuBar menuBar;

    private final HBox contentHBox;

    private final ActionBar actionBar;
    private final SplitPane contentSplitPane;

    private final ImageViewer imageViewer;
    private final StatusBar statusBar;
    private final VBox centerVBox;

    private final Sidebar sidebar;

    public MainRoot() {
        super();

        this.menuBar = new MenuBar();

        this.actionBar = new ActionBar();

        this.imageViewer = new ImageViewer();
        this.statusBar = new StatusBar();
        this.centerVBox = new VBox(this.imageViewer, this.statusBar);

        this.sidebar = new Sidebar();

        this.contentSplitPane = new SplitPane(this.centerVBox, this.sidebar);
        this.contentSplitPane.getStyleClass().add("content-split-pane");

        this.contentHBox = new HBox(this.actionBar, this.contentSplitPane);

        VBox.setVgrow(this.menuBar, Priority.NEVER);
        VBox.setVgrow(this.contentHBox, Priority.ALWAYS);

        HBox.setHgrow(this.actionBar, Priority.NEVER);
        HBox.setHgrow(this.contentSplitPane, Priority.ALWAYS);

        VBox.setVgrow(this.statusBar, Priority.NEVER);
        VBox.setVgrow(this.imageViewer, Priority.ALWAYS);

        this.getChildren().addAll(this.menuBar, this.contentHBox);

        Platform.runLater(() -> this.contentSplitPane.setDividerPositions(0));
    }
}
