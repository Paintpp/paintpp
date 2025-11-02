package com.purkynka.paintpp.ui.primarystage;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main window of the editor.
 * <p>
 * Contains a static reference to the JavaFX {@link Stage} representing the window.
 */
public class PrimaryStage {
    public static Stage PRIMARY_STAGE;

    /**
     * Constructs a new {@link PrimaryStage} with the provided main window {@link Stage}.
     * @param primaryStage The main window stage
     */
    public PrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Paint++");
        primaryStage.setMinWidth(768);
        primaryStage.setMinHeight(512);

        var primaryStageRoot = new PrimaryStageRoot();
        var scene = new Scene(primaryStageRoot);
        primaryStage.setScene(scene);

        PrimaryStage.PRIMARY_STAGE = primaryStage;
    }

    /**
     * Wrapper method to show the main window.
     */
    public void show() {
        PrimaryStage.PRIMARY_STAGE.show();
    }
}
