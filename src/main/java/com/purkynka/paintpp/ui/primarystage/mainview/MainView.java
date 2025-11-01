package com.purkynka.paintpp.ui.primarystage.mainview;

import com.purkynka.paintpp.ui.primarystage.PrimaryStageRoot;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.LeftSidebar;
import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.RightSidebar;
import javafx.application.Platform;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * {@link SplitPane} containing the columns of the {@link PrimaryStageRoot}.
 */
public class MainView extends SplitPane {
    private final LeftSidebar leftSidebar;
    private final ImageViewer imageViewer;
    private final RightSidebar rightSidebar;

    /**
     * Constructs a new {@link MainView}.
     */
    public MainView() {
        super();

        VBox.setVgrow(this, Priority.ALWAYS);

        leftSidebar = new LeftSidebar();
        imageViewer = new ImageViewer();
        rightSidebar = new RightSidebar();

        getItems().addAll(leftSidebar, imageViewer, rightSidebar);
        
        widthProperty().addListener(_ -> Platform.runLater(() ->
                setDividerPositions(0.2, 0.8)
        ));
        
        heightProperty().addListener(_ -> Platform.runLater(() ->
                setDividerPositions(0.2, 0.8)
        ));
        
        Platform.runLater(() -> setDividerPositions(0.2, 0.8));
    }
}
