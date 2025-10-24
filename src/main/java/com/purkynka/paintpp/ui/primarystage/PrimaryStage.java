package com.purkynka.paintpp.ui.primarystage;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryStage {
    public static Stage PRIMARY_STAGE;

    public PrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Paint++");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        var primaryStageRoot = new PrimaryStageRoot();
        var scene = new Scene(primaryStageRoot);
        primaryStage.setScene(scene);

        PrimaryStage.PRIMARY_STAGE = primaryStage;
    }

    public void show() {
        PrimaryStage.PRIMARY_STAGE.show();
    }
}
