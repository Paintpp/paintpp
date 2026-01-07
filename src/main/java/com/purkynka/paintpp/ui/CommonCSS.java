package com.purkynka.paintpp.ui;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import com.purkynka.paintpp.logic.observable.ObservableValue;
import com.purkynka.paintpp.logic.util.ResourceUtil;
import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.application.Application;
import javafx.scene.Scene;

public class CommonCSS {
    public static final double BORDER_WIDTH = 2;
    public static ObservableValue<Boolean> LIGHT_THEME = new ObservableValue<>(false);

    public static void setTheme(boolean lightTheme) {
        LIGHT_THEME.set(lightTheme);

        var theme = lightTheme ? new CupertinoLight() : new CupertinoDark();
        Application.setUserAgentStylesheet(theme.getUserAgentStylesheet());

        CommonCSS.addStylesheetsToScene(MainStage.MAIN_STAGE.getScene());
    }

    public static void toggleTheme() {
        CommonCSS.setTheme(!CommonCSS.LIGHT_THEME.get());
    }

    public static void addStylesheetsToScene(Scene scene) {
        scene.getStylesheets().setAll(
                CommonCSS.getStyleSheet("/colors.css"),
                CommonCSS.getStyleSheet("/menu_bar.css"),
                CommonCSS.getStyleSheet("/main_root.css"),
                CommonCSS.getStyleSheet("/action_bar.css"),
                CommonCSS.getStyleSheet("/status_bar.css"),
                CommonCSS.getStyleSheet("/popup.css"),
                CommonCSS.getStyleSheet("/text_field.css"),
                CommonCSS.getStyleSheet("/choice_field.css"),
                CommonCSS.getStyleSheet("/size_field.css"),
                CommonCSS.getStyleSheet("/sidebar.css"),
                CommonCSS.getStyleSheet("/choice_list.css"),
                CommonCSS.getStyleSheet("/filter_loading_screen.css"),
                CommonCSS.getStyleSheet("/about.css")
        );

        if (CommonCSS.LIGHT_THEME.get()) scene.getStylesheets().add(CommonCSS.getStyleSheet("/light_theme_overrides.css"));
    }

    private static String getStyleSheet(String relativePath) {
        return ResourceUtil.getResource("/css" + relativePath);
    }
}
