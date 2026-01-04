package com.purkynka.paintpp.ui;

import com.purkynka.paintpp.logic.util.ResourceUtil;
import javafx.scene.Scene;

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
                CommonCSS.getStyleSheet("/choice_list.css"),
                CommonCSS.getStyleSheet("/filter_loading_screen.css"),
                CommonCSS.getStyleSheet("/about.css")
        );
    }

    private static String getStyleSheet(String relativePath) {
        return ResourceUtil.getResource("/css" + relativePath);
    }
}
