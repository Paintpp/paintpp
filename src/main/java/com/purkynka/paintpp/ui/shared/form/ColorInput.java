package com.purkynka.paintpp.ui.shared.form;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Form element containing a {@link Label} and {@link ColorPicker}.
 */
public class ColorInput {
    private final Label label;
    private final ColorPicker colorPicker;

    /**
     * Constructs a new {@link ColorInput} inside the provided {@link GridPane}, labeled as the
     * provided {@link String}.
     * @param parent The parent to put the elements inside of
     */
    public ColorInput(GridPane parent) {
        var rowOffset = parent.getRowCount();

        label = new Label("Color:");
        colorPicker = new ColorPicker();

        parent.add(label, 0, rowOffset);
        parent.add(colorPicker, 1, rowOffset);
    }

    /**
     * Returns the currently picked color of the {@link ColorPicker}.
     * @return The picked color
     */
    public Color getColor() {
        return colorPicker.getValue();
    }
}
