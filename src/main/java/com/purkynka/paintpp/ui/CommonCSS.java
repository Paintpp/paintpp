package com.purkynka.paintpp.ui;

import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.Objects;

public class CommonCSS {
    public static final double BORDER_WIDTH = 2;

    public static void addStylesheetsToScene(Scene scene) {
        scene.getStylesheets().addAll(
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
                CommonCSS.getStyleSheet("/choice_list.css")
        );
    }

    private static String getStyleSheet(String relativePath) {
        return Objects.requireNonNull(MainStage.class.getResource("/com/purkynka/paintpp/css" + relativePath)).toExternalForm();
    }
}
