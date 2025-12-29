package com.purkynka.paintpp;

import atlantafx.base.theme.CupertinoDark;
import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Paintpp extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());

        var mainStage = new MainStage(stage);
        mainStage.show();
    }
}
