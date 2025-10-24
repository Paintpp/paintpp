package com.purkynka.paintpp.ui.shared.form;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.image.ImageSize;
import com.purkynka.paintpp.ui.shared.textformatter.PositiveIntegerTextFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;

public class SizeInput {
    private Label sizeLabel;

    private Label widthLabel;
    private PositiveIntegerInput widthInput;

    private Button flipButton;

    private Label heightLabel;
    private PositiveIntegerInput heightInput;

    public SizeInput(GridPane parent) {
        var rowOffset = parent.getRowCount();

        sizeLabel = new Label("Size:");

        widthLabel = createMutedLabel("Width");
        widthInput = new PositiveIntegerInput("400");

        flipButton = new Button("", new FontIcon(MaterialDesignA.ARROW_LEFT_RIGHT));

        heightLabel = createMutedLabel("Height");
        heightInput = new PositiveIntegerInput("400");

        parent.add(widthLabel, 1, rowOffset);
        parent.add(heightLabel, 3, rowOffset);

        parent.add(sizeLabel, 0, rowOffset + 1);
        parent.add(widthInput, 1, rowOffset + 1);
        parent.add(flipButton, 2, rowOffset + 1);
        parent.add(heightInput, 3, rowOffset + 1);
    }

    private Label createMutedLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add(Styles.TEXT_SUBTLE);

        return label;
    }

    public ImageSize getSize() {
        var width = widthInput.getValue();
        var height = heightInput.getValue();

        return new ImageSize(width, height);
    }
}
