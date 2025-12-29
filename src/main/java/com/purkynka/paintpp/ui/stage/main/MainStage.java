package com.purkynka.paintpp.ui.stage.main;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainStage {
    private final Stage stage;

    public MainStage(Stage stage) {
        this.stage = stage;

        this.stage.setTitle("Paint++");
        this.stage.setMinWidth(1200);
        this.stage.setMinHeight(800);

        var scene = new Scene(new MainRoot());
        scene.getStylesheets().addAll(
                this.getStyleSheet("/colors.css"),
                this.getStyleSheet("/main_root.css"),
                this.getStyleSheet("/action_bar.css"),
                this.getStyleSheet("/status_bar.css")
        );

        this.stage.setScene(scene);
    }

    private String getStyleSheet(String relativePath) {
        return Objects.requireNonNull(MainStage.class.getResource("/com/purkynka/paintpp/css" + relativePath)).toExternalForm();
    }

    public void show() {
        this.stage.show();
    }
}
