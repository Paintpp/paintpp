package com.purkynka.paintpp.ui.stage.filter.custommatrix;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.kernelfilter.CustomMatrixFilter;
import com.purkynka.paintpp.ui.element.FilterPreview;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.checkboxfield.CheckboxField;
import com.purkynka.paintpp.ui.element.form.input.textfield.DoubleTextField;
import com.purkynka.paintpp.ui.element.form.input.validator.RequiredValidator;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import java.util.ArrayList;

public class CustomMatrixFilterAdder extends PopupStage {
    private final FormContext<CustomMatrixFormValue> formContext = new FormContext<>(new CustomMatrixFormValue());

    private final CustomMatrixFilter customMatrixFilter = new CustomMatrixFilter(formContext.getObservableFormValue().get().kernel, true);
    private final FilterPreview filterPreview = new FilterPreview(this.customMatrixFilter);

    private int currentWidth = 3;
    private int currentHeight = 3;

    private GridPane inputGridPane;
    private Button addColumnButton;
    private Button addRowButton;

    private CheckboxField<CustomMatrixFormValue> normalizeMatrixField;

    public CustomMatrixFilterAdder() {
        super();

        this.setPopupTitle("Add Custom Matrix Filter");
        this.setShowTitle(true);
        this.addDefaultButtons(() -> {
            FilterManager.FILTERS.add(this.customMatrixFilter);
            this.close();
        });

        var submitButton = this.buttons.get(1);
        this.formContext.getObservableFormErrors().addListener(hashset -> {
            submitButton.setDisable(!hashset.isEmpty());
        });

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            if (!this.formContext.getObservableFormErrors().isEmpty()) return;
            if (v.normalizeKernel == null) return;

            customMatrixFilter.setProvidedKernel(v.kernel);
            customMatrixFilter.setNormalizeKernel(v.normalizeKernel);
            this.filterPreview.refreshImage();
        });
    }

    private void setupFields() {
        this.inputGridPane = new GridPane(8, 8);
        this.inputGridPane.setAlignment(Pos.CENTER);

        for (var y = 0; y < this.currentHeight; y++) {
            for (var x = 0; x < this.currentWidth; x++) {
                this.inputGridPane.add(this.createTextField(x, y), x, y);
            }
        }

        this.normalizeMatrixField = new CheckboxField<>(this.formContext)
                .setLabel("Normalize Matrix")
                .setValue(true)
                .setFormValueSetter((curr, v) -> curr.normalizeKernel = v);
        this.normalizeMatrixField.setAlignment(Pos.CENTER);
    }

    private DoubleTextField<CustomMatrixFormValue> createTextField(int x, int y) {
        return new DoubleTextField<>(this.formContext)
                .setWidth(64)
                .removeLabel()
                .addValidators(new RequiredValidator())
                .setValue(0d)
                .setFormValueSetter(
                        (curr, v) -> curr.kernel.get(y).set(x, v)
                );
    }

    private void setupAddButtons() {
        if (this.addColumnButton == null && this.addRowButton == null) {
            this.addColumnButton = this.createAddButton();
            this.addColumnButton.setMaxHeight(Double.MAX_VALUE);
            this.addColumnButton.setOnAction(_ -> this.addColumn());

            this.addRowButton = this.createAddButton();
            this.addRowButton.setMaxWidth(Double.MAX_VALUE);
            this.addRowButton.setOnAction(_ -> this.addRow());
        } else {
            var gridPaneChildren = this.inputGridPane.getChildren();

            gridPaneChildren.remove(this.addColumnButton);
            gridPaneChildren.remove(this.addRowButton);
        }

        this.inputGridPane.add(this.addColumnButton, this.currentWidth, 0, 1, this.currentHeight);
        this.inputGridPane.add(this.addRowButton, 0, this.currentHeight, this.currentWidth, 1);
    }

    private Button createAddButton() {
        return new Button("", new FontIcon(MaterialDesignP.PLUS));
    }

    private void addColumn() {
        var formValue = formContext.getObservableFormValue().get();

        for (var y = 0; y < formValue.kernel.size(); y++) {
            var line = formValue.kernel.get(y);
            line.add(0d);
            line.add(0d);
        }

        formContext.getObservableFormValue().set(formValue);

        for (var y = 0; y < formValue.kernel.size(); y++) {
            this.inputGridPane.add(this.createTextField(this.currentWidth - 1, y), this.currentWidth, y);
            this.inputGridPane.add(this.createTextField(this.currentWidth, y), this.currentWidth + 1, y);
        }

        this.currentWidth += 2;

        this.setupAddButtons();

        this.sizeToScene();
    }

    private void addRow() {
        var formValue = formContext.getObservableFormValue().get();

        for (var i = 0; i < 2; i++) {
            var newLine = new ArrayList<Double>();
            for (var x = 0; x < this.currentWidth; x++) {
                newLine.add(0d);
            }
            formValue.kernel.add(newLine);
        }

        formContext.getObservableFormValue().set(formValue);

        for (var x = 0; x < this.currentWidth; x++) {
            this.inputGridPane.add(this.createTextField(x, this.currentHeight - 1), x, this.currentHeight);
            this.inputGridPane.add(this.createTextField(x, this.currentHeight), x, this.currentHeight + 1);
        }

        this.currentHeight += 2;

        this.setupAddButtons();

        this.sizeToScene();
    }

    @Override
    protected Parent createRoot() {
        this.setupFields();
        this.setupAddButtons();

        return new VBox(16, this.filterPreview, this.inputGridPane, this.normalizeMatrixField);
    }
}
