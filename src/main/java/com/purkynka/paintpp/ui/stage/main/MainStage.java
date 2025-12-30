package com.purkynka.paintpp.ui.stage.main;

import com.purkynka.paintpp.ui.CommonCSS;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage {
    public static Stage MAIN_STAGE;

    public MainStage(Stage stage) {
        MainStage.MAIN_STAGE = stage;

        MainStage.MAIN_STAGE.setTitle("Paint++");
        MainStage.MAIN_STAGE.setMinWidth(1200);
        MainStage.MAIN_STAGE.setMinHeight(800);

        var scene = new Scene(new MainRoot());
        CommonCSS.addStylesheetsToScene(scene);

        MainStage.MAIN_STAGE.setScene(scene);
    }

    public void show() {
        MainStage.MAIN_STAGE.show();
    }
}
