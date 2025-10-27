package com.purkynka.paintpp.ui.shared.form;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;

/**
 * Form element containing a {@link Label} and two {@link PositiveIntegerInput PositiveIntegerInput} fields for a size.
 * <p>
 * Also contains a {@link Button} to flip the current width and height values.
 */
public class SizeInput {
    private Label sizeLabel;

    private Label widthLabel;
    private PositiveIntegerInput widthInput;

    private Button flipButton;

    private Label heightLabel;
    private PositiveIntegerInput heightInput;

    /**
     * Constructs a new {@link SizeInput} inside the provided {@link GridPane},
     * labeled as the provided {@link String}.
     * @param parent The parent to put the elements inside of
     */
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

    /**
     * Helper method to create a {@link Label} with the {@link Styles#TEXT_SUBTLE} style class.
     * @param text The text inside the label
     * @return The created label
     */
    private Label createMutedLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add(Styles.TEXT_SUBTLE);

        return label;
    }

    /**
     * Returns the currently written out {@link ImageSize}.
     * @return The written out image size
     */
    public ImageSize getSize() {
        var width = widthInput.getValue();
        var height = heightInput.getValue();

        return new ImageSize(width, height);
    }
}
