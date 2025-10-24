package com.purkynka.paintpp.ui.shared.popup;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PopupTitle extends Label {
    public PopupTitle(String text) {
        super(text);

        setFont(new Font(24));
        getStyleClass().add(Styles.TEXT_BOLD);
        setPadding(new Insets(0, 0, 16, 0));
    }
}
