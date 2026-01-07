package com.purkynka.paintpp;

import com.purkynka.paintpp.ui.CommonCSS;
import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

public class Paintpp extends Application {
    public static HostServices HOST_SERVICES;

    @Override
    public void start(Stage stage) {
        var mainStage = new MainStage(stage);
        mainStage.show();

        Paintpp.HOST_SERVICES = this.getHostServices();
    }
}
