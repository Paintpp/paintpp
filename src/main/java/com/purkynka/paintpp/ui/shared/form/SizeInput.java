package com.purkynka.paintpp.ui.shared.form;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;

/**
 * Form element containing a {@link Label} and two {@link IntegerInput PositiveIntegerInput} fields for a size.
 * <p>
 * Also contains a {@link Button} to flip the current width and height values.
 */
public class SizeInput {
    private final Label sizeLabel;

    private final Label widthLabel;
    private final IntegerInput widthInput;

    private final Button flipButton;

    private final Label heightLabel;
    private final IntegerInput heightInput;

    /**
     * Constructs a new {@link SizeInput} inside the provided {@link GridPane},
     * labeled as the provided {@link String}.
     * @param parent The parent to put the elements inside of
     */
    public SizeInput(GridPane parent) {
        var rowOffset = parent.getRowCount();

        sizeLabel = new Label("Size:");

        widthLabel = createMutedLabel("Width");
        widthInput = new IntegerInput(1, 4096, 400);

        flipButton = new Button("", new FontIcon(MaterialDesignA.ARROW_LEFT_RIGHT));
        flipButton.setOnAction(_ -> onFlipButtonClick());
        
        heightLabel = createMutedLabel("Height");
        heightInput = new IntegerInput(1, 4096, 400);

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
     * Flips the width and height values.
     */
    private void onFlipButtonClick() {
        var width = widthInput.getValue();
        widthInput.setValue(heightInput.getValue());
        heightInput.setValue(width);
        
        widthInput.checkInput();
        heightInput.checkInput();
    }
    
    /**
     * Returns the currently written out {@link ImageSize}.
     * @return The written out image size
     */
    public ImageSize getSize() {
        var width = Integer.parseInt(widthInput.getText());
        var height = heightInput.getValue();

        return new ImageSize(width, height);
    }
}
