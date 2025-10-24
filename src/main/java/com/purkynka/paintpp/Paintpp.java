package com.purkynka.paintpp;

import atlantafx.base.theme.CupertinoDark;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Paintpp extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());

        var primaryStage = new PrimaryStage(stage);
        primaryStage.show();
    }
}
