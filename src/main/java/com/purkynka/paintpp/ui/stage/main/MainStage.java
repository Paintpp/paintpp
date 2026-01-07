package com.purkynka.paintpp.ui.stage.main;

import com.purkynka.paintpp.ui.CommonCSS;
import com.purkynka.paintpp.ui.element.filterloadingscreen.FilterLoadingScreen;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainStage {
    public static Stage MAIN_STAGE;

    public MainStage(Stage stage) {
        MainStage.MAIN_STAGE = stage;

        MainStage.MAIN_STAGE.setTitle("Paint++");
        MainStage.MAIN_STAGE.setMinWidth(1200);
        MainStage.MAIN_STAGE.setMinHeight(800);

        var wrapper = new StackPane(new MainRoot(), new FilterLoadingScreen());

        var scene = new Scene(wrapper);

        MainStage.MAIN_STAGE.setScene(scene);

        CommonCSS.setTheme(false);
        CommonCSS.LIGHT_THEME.addUpdateListener(_ -> CommonCSS.addStylesheetsToScene(scene));
    }

    public void show() {
        MainStage.MAIN_STAGE.show();
    }
}
