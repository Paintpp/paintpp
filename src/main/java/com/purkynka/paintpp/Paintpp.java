package com.purkynka.paintpp;

import atlantafx.base.theme.CupertinoDark;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Paintpp extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        var scene = new Scene(new HBox());

        stage.setTitle("Paint++");
        stage.setScene(scene);
        stage.show();
    }
}
