package com.purkynka.paintpp.ui.shared.form;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Form element containing a {@link Label} and {@link ChoiceBox}.
 * @param <T> Enum representing the possible options
 */
public class ChoiceInput<T extends Enum<T>> {
    private Label label;
    private ChoiceBox<T> choiceBox;

    /**
     * Constructs a new {@link ChoiceInput} inside the provided {@link GridPane},
     * labeled as the provided {@link String} and with the options inside the {@link Class<T>} value.
     * <p>
     * By default, the first value of the provided enum is selected.
     *
     * @param parent Parent to put the elements inside of
     * @param labelText Text to label the choice box as
     * @param enumClass The class of the enum representing the options of the choice box
     */
    public ChoiceInput(GridPane parent, String labelText, Class<T> enumClass) {
        var rowOffset = parent.getRowCount();

        label = new Label(labelText);
        choiceBox = new ChoiceBox<>();

        var values = enumClass.getEnumConstants();
        choiceBox.getItems().addAll(values);
        choiceBox.setValue(values[0]);

        parent.add(label, 0, rowOffset);
        parent.add(choiceBox, 1, rowOffset);
    }

    /**
     * Returns the currently picked value inside the choice box.
     * @return The picked value
     */
    public T getValue() {
        return choiceBox.getValue();
    }
}
