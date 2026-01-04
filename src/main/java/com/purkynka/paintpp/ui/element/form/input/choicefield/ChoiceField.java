package com.purkynka.paintpp.ui.element.form.input.choicefield;

import com.purkynka.paintpp.logic.observable.ObservableHashSet;
import com.purkynka.paintpp.logic.observable.ObservableValue;
import com.purkynka.paintpp.ui.element.ChoiceListButton;
import com.purkynka.paintpp.ui.element.ElementOrder;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.context.FormValueSetter;
import com.purkynka.paintpp.ui.element.form.input.validator.ChoiceFieldValidator;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class ChoiceField<C, E extends Enum<E> & DescriptiveEnum> extends Pane {
    private static final double CHOICE_FIELD_HEIGHT = 40d;

    private static final PseudoClass EMPTY = PseudoClass.getPseudoClass("empty");
    private static final PseudoClass OPENED = PseudoClass.getPseudoClass("opened");
    private static final PseudoClass FOCUSED = PseudoClass.getPseudoClass("focused");
    private static final PseudoClass INVALID = PseudoClass.getPseudoClass("invalid");
    private static final PseudoClass VALID = PseudoClass.getPseudoClass("valid");
    private final ArrayList<ChoiceFieldValidator> validators = new ArrayList<>();
    private final String elementUUID = UUID.randomUUID().toString();
    private final ObservableValue<E> observableValue = new ObservableValue<>(null);
    private String placeholderText;
    private Label choiceField;
    private Label fieldLabel;
    private FontIcon openedIcon;
    private ScrollPane choiceScrollPane;
    private VBox choiceContainer;
    private boolean opened;
    private FontIcon errorIcon;
    private Tooltip errorTooltip;
    private boolean hasErrors;
    private ObservableValue<C> observableFormValue;
    private ObservableHashSet<String> observableFormErrors;
    private FormValueSetter<C, E> formValueSetter;
    private E[] availableValues;

    public ChoiceField(FormContext<C> formContext, Class<E> choicesEnumClass) {
        super();

        this.setupFormObservables(formContext);
        this.setupAvailableValues(choicesEnumClass);

        this.setupContainer();
        this.setupElements();
        this.setupListeners();

        this.onChoiceSelected();

        this.getChildren().addAll(this.choiceField, this.fieldLabel, this.openedIcon, this.choiceScrollPane, this.errorIcon);
    }

    private void setupFormObservables(FormContext<C> formContext) {
        this.observableFormValue = formContext.getObservableFormValue();
        this.observableFormErrors = formContext.getObservableFormErrors();
    }

    private void setupAvailableValues(Class<E> choicesEnumClass) {
        this.availableValues = choicesEnumClass.getEnumConstants();
    }

    private void setupContainer() {
        this.setMinHeight(ChoiceField.CHOICE_FIELD_HEIGHT);
        this.setMaxHeight(ChoiceField.CHOICE_FIELD_HEIGHT);

        this.setWidth(256);
        this.getStyleClass().addAll("choice-field");

        this.setViewOrder(-10);
    }

    private void setupElements() {
        this.setupChoiceField();
        this.setupFieldLabel();
        this.setupOpenedIcon();
        this.setupChoiceElements();
        this.setupErrorElements();
    }

    private void setupChoiceField() {
        this.choiceField = new Label();

        this.choiceField.setPadding(new Insets(0, 12, 0, 12));
        this.choiceField.minWidthProperty().bind(widthProperty());
        this.choiceField.maxWidthProperty().bind(widthProperty());
        this.choiceField.minHeightProperty().bind(heightProperty());
        this.choiceField.maxHeightProperty().bind(heightProperty());
        this.choiceField.setFocusTraversable(true);

        this.choiceField.getStyleClass().add("choice-field-input");
    }

    private void setupFieldLabel() {
        this.fieldLabel = new Label();

        this.fieldLabel.setPadding(new Insets(0, 4, 0, 4));
        this.fieldLabel.setMouseTransparent(true);
        this.fieldLabel.relocate(9, -10);
        this.fieldLabel.getStyleClass().add("choice-field-label");
    }

    private void setupOpenedIcon() {
        this.openedIcon = new FontIcon(MaterialDesignC.CHEVRON_DOWN);

        this.openedIcon.setMouseTransparent(true);
        this.openedIcon.setIconSize(24);
        this.openedIcon.layoutXProperty().bind(widthProperty().subtract(24 + 8));
        this.openedIcon.setLayoutY(29);

        this.openedIcon.getStyleClass().add("choice-field-opened-icon");
    }

    private void setupChoiceElements() {
        this.choiceContainer = new VBox();
        this.choiceContainer.minWidthProperty().bind(this.widthProperty().subtract(16));
        this.choiceContainer.setFillWidth(true);

        this.choiceScrollPane = new ScrollPane(new Group(this.choiceContainer));
        this.choiceScrollPane.prefWidthProperty().bind(this.widthProperty());
        this.choiceScrollPane.setPrefHeight(128);
        this.choiceScrollPane.layoutYProperty().bind(this.heightProperty().add(4));

        // this.choiceContainer.setFillWidth(true);

        var children = this.choiceContainer.getChildren();
        for (var i = 0; i < this.availableValues.length; i++) {
            var choice = this.availableValues[i];
            var child = new ChoiceListButton(
                    choice.getName(),
                    choice.getDescription()
            );
            ElementOrder.applyPseudoclass(child, i, availableValues.length);

            int finalI = i;
            child.setOnAction(_ -> this.onChoiceSelected(this.availableValues[finalI]));

            children.add(child);
        }

        this.choiceScrollPane.setVisible(false);
        this.choiceContainer.getStyleClass().add("choice-field-choice-container");
    }

    private void setupErrorElements() {
        this.errorIcon = new FontIcon(MaterialDesignA.ALERT_BOX);

        this.errorIcon.setMouseTransparent(true);
        this.errorIcon.setIconSize(24);
        this.errorIcon.layoutXProperty().bind(widthProperty().subtract(24 + 8 + 24 + 4));
        this.errorIcon.setLayoutY(29);

        this.errorIcon.getStyleClass().add("choice-field-error-icon");

        this.errorTooltip = new Tooltip();
    }

    private void setupListeners() {
        this.choiceField.setOnMouseClicked(_ -> this.setOpened(!this.opened));
        this.choiceField.setOnKeyPressed(e -> {
            if (!this.choiceField.isFocused()) return;
            if (e.getCode() != KeyCode.SPACE && e.getCode() != KeyCode.ENTER) {
                return;
            }

            this.setOpened(!this.opened);
        });

        this.choiceField.focusedProperty().addListener((_, _, v) -> {
            this.pseudoClassStateChanged(ChoiceField.FOCUSED, v);
        });
    }

    private void setOpened(boolean opened) {
        this.opened = opened;

        this.choiceScrollPane.setVisible(this.opened);
        this.choiceContainer.getChildren().getFirst().requestFocus();
        pseudoClassStateChanged(ChoiceField.OPENED, this.opened);
    }

    private void onChoiceSelected(E choice) {
        this.observableValue.set(choice);
        this.updateFieldText(choice);
        this.setOpened(false);

        this.recheckValidity(choice);
        this.setFormValue(choice);
    }

    private void onChoiceSelected() {
        this.onChoiceSelected(this.observableValue.get());
    }

    private void updateFieldText(E choice) {
        if (choice == null) {
            this.choiceField.setText(this.placeholderText);
            this.pseudoClassStateChanged(ChoiceField.EMPTY, true);
        } else {
            this.choiceField.setText(choice.getName());
            this.pseudoClassStateChanged(ChoiceField.EMPTY, false);
        }
    }

    private void updateFieldText() {
        this.updateFieldText(this.observableValue.get());
    }

    private void recheckValidity(E choice) {
        var errors = new ArrayList<String>();

        for (var validator : this.validators) {
            var returnedError = validator.validateChoiceField(choice);
            if (returnedError != null) errors.add(returnedError);
        }

        this.hasErrors = !errors.isEmpty();

        if (this.hasErrors) {
            this.pseudoClassStateChanged(ChoiceField.INVALID, true);
            this.pseudoClassStateChanged(ChoiceField.VALID, false);

            this.errorTooltip.setText(String.join("\n", errors));
            Tooltip.install(this.choiceField, this.errorTooltip);

            this.observableFormErrors.add(this.elementUUID);
        } else {
            this.pseudoClassStateChanged(ChoiceField.INVALID, false);
            this.pseudoClassStateChanged(ChoiceField.VALID, true);

            Tooltip.uninstall(this.choiceField, this.errorTooltip);

            this.observableFormErrors.remove(this.elementUUID);
        }
    }

    private void recheckValidity() {
        this.recheckValidity(this.observableValue.get());
    }

    private void setFormValue(E choice) {
        if (this.formValueSetter == null) return;

        var formValue = this.observableFormValue.get();
        this.formValueSetter.setValue(formValue, this.hasErrors ? null : choice);
        this.observableFormValue.set(formValue);
    }

    private void setFormValue() {
        this.setFormValue(this.observableValue.get());
    }

    public ChoiceField<C, E> setLabel(String labelText) {
        this.fieldLabel.setText(labelText);

        return this;
    }

    public ChoiceField<C, E> setPlaceholder(String placeholderText) {
        this.placeholderText = placeholderText;
        this.updateFieldText();

        return this;
    }

    public ChoiceField<C, E> setWidth(int width) {
        this.setMinWidth(width);
        this.setMaxWidth(width);

        return this;
    }

    public ChoiceField<C, E> bindWidth(ReadOnlyDoubleProperty width) {
        this.minWidthProperty().bind(width);
        this.maxWidthProperty().bind(width);

        return this;
    }

    public ChoiceField<C, E> addValidators(ChoiceFieldValidator... validators) {
        Collections.addAll(this.validators, validators);
        this.recheckValidity();

        return this;
    }

    public ChoiceField<C, E> setFormValueSetter(FormValueSetter<C, E> formValueSetter) {
        this.formValueSetter = formValueSetter;
        this.setFormValue();

        return this;
    }
}
