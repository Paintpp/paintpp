package com.purkynka.paintpp.ui.element.form.input.sizefield;

import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.textfield.IntegerTextField;
import com.purkynka.paintpp.ui.element.form.input.validator.PositiveIntegerValidator;
import com.purkynka.paintpp.ui.element.form.input.validator.RequiredValidator;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;

public class SizeField<C> extends HBox {
    private final IntegerTextField<C> widthInput;
    private final IntegerTextField<C> heightInput;

    public SizeField(FormContext<C> formContext, FormValueSetter<C, Integer> widthFormValueSetter, FormValueSetter<C, Integer> heightFormValueSetter) {
        super(8);

        this.widthInput = new IntegerTextField<>(formContext)
                .setWidth(128)
                .setLabel("Width")
                .setPlaceholder("256...")
                .addValidators(new RequiredValidator(), new PositiveIntegerValidator())
                .setFormValueSetter(widthFormValueSetter)
                .setValue(256);

        var swapButton = this.createSwapButton();

        this.heightInput = new IntegerTextField<>(formContext)
                .setWidth(128)
                .setLabel("Height")
                .setPlaceholder("256...")
                .addValidators(new RequiredValidator(), new PositiveIntegerValidator())
                .setFormValueSetter(heightFormValueSetter)
                .setValue(256);

        this.getChildren().addAll(widthInput, swapButton, heightInput);
    }

    private Button createSwapButton() {
        var swapButtonIcon = new FontIcon(MaterialDesignS.SWAP_HORIZONTAL);
        swapButtonIcon.getStyleClass().add("size-field-swap-button-icon");
        swapButtonIcon.setIconSize(24);

        var swapButton = new Button("", swapButtonIcon);
        swapButton.minHeightProperty().bind(this.heightProperty());
        swapButton.setOnAction((_) -> {
            var width = this.widthInput.getStringValue();
            var height = this.heightInput.getStringValue();

            this.widthInput.setStringValue(height);
            this.heightInput.setStringValue(width);
        });

        return swapButton;
    }
}
