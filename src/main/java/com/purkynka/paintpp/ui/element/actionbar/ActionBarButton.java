package com.purkynka.paintpp.ui.element.actionbar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class ActionBarButton extends VBox {
    public ActionBarButton(Ikon icon, String text) {
        super(4);

        this.setAlignment(Pos.CENTER);

        this.setMinWidth(ActionBar.ACTION_BAR_WIDTH);
        this.setMinHeight(ActionBar.ACTION_BAR_WIDTH);

        this.getStyleClass().add("action-bar-button");

        var buttonIcon = new FontIcon(icon);
        buttonIcon.getStyleClass().add("action-bar-button-icon");
        buttonIcon.setIconSize(28);

        var label = new Label(text);
        label.getStyleClass().add("action-bar-button-label");

        this.getChildren().addAll(buttonIcon, label);
    }
}
