package com.purkynka.paintpp;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.ui.popup.help.HelpPopup;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Paintpp extends Application {
    public static HostServices hostServices;

    @Override
    public void start(Stage stage) {
        hostServices = getHostServices();
        Application.setUserAgentStylesheet(ConfigManager.getTheme().getUserAgentStylesheet());

        var primaryStage = new PrimaryStage(stage);
        primaryStage.show();

        ConfigManager.applyFont();

        Platform.runLater(HelpPopup::new);
    }
}
