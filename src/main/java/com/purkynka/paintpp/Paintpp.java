package com.purkynka.paintpp;

import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Paintpp extends Application {
    @Override
    public void start(Stage stage) {
        var primaryStage = new PrimaryStage(stage);
        primaryStage.show();
    }
}
