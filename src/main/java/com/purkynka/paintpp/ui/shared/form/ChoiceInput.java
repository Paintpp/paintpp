package com.purkynka.paintpp.ui.shared.form;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ChoiceInput<T extends Enum<T>> {
    private Label label;
    private ChoiceBox<T> choiceBox;

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

    public T getValue() {
        return choiceBox.getValue();
    }
}
