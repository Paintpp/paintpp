package com.purkynka.paintpp.ui.primarystage.mainview;

import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.LeftSidebar;
import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.RightSidebar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainView extends SplitPane {
    private LeftSidebar leftSidebar;
    private ImageViewer imageViewer;
    private RightSidebar rightSidebar;

    public MainView() {
        super();

        VBox.setVgrow(this, Priority.ALWAYS);

        leftSidebar = new LeftSidebar();
        imageViewer = new ImageViewer();
        rightSidebar = new RightSidebar();

        getItems().addAll(leftSidebar, imageViewer, rightSidebar);

        setDividerPositions(0.2, 0.8);
    }
}
