package com.purkynka.paintpp.ui.stage.main;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainStage {
    public static Stage MAIN_STAGE;

    public MainStage(Stage stage) {
        MainStage.MAIN_STAGE = stage;

        MainStage.MAIN_STAGE.setTitle("Paint++");
        MainStage.MAIN_STAGE.setMinWidth(1200);
        MainStage.MAIN_STAGE.setMinHeight(800);

        var scene = new Scene(new MainRoot());
        scene.getStylesheets().addAll(
                this.getStyleSheet("/colors.css"),
                this.getStyleSheet("/menu_bar.css"),
                this.getStyleSheet("/main_root.css"),
                this.getStyleSheet("/action_bar.css"),
                this.getStyleSheet("/status_bar.css")
        );

        MainStage.MAIN_STAGE.setScene(scene);
    }

    private String getStyleSheet(String relativePath) {
        return Objects.requireNonNull(MainStage.class.getResource("/com/purkynka/paintpp/css" + relativePath)).toExternalForm();
    }

    public void show() {
        MainStage.MAIN_STAGE.show();
    }
}
