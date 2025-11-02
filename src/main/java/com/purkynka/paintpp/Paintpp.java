package com.purkynka.paintpp;

import atlantafx.base.theme.CupertinoDark;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

public class Paintpp extends Application {
    public static HostServices hostServices;
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        hostServices = getHostServices();

        var primaryStage = new PrimaryStage(stage);
        primaryStage.show();
    }
}
