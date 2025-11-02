package com.purkynka.paintpp.ui.popup.settings.components;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CheckBoxOption extends GridPane {
    private final Label label;
    private final CheckBox checkBox;

    public CheckBoxOption(String title) {
        setAlignment(Pos.TOP_LEFT);
        setHgap(10);

        label = new Label(title);
        checkBox = new CheckBox();

        add(label, 0, 0);
        add(checkBox, 1, 0);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
