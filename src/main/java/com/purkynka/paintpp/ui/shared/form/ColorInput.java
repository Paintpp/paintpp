package com.purkynka.paintpp.ui.shared.form;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColorInput {
    private Label label;
    private ColorPicker colorPicker;

    public ColorInput(GridPane parent) {
        var rowOffset = parent.getRowCount();

        label = new Label("Color:");
        colorPicker = new ColorPicker();

        parent.add(label, 0, rowOffset);
        parent.add(colorPicker, 1, rowOffset);
    }

    public Color getColor() {
        return colorPicker.getValue();
    }
}
