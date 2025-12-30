package com.purkynka.paintpp.ui.element.form.input.choicefield;

import atlantafx.base.util.BBCodeParser;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChoiceFieldChoice extends Button {
    private static final PseudoClass FIRST = PseudoClass.getPseudoClass("first");
    private static final PseudoClass LAST =  PseudoClass.getPseudoClass("last");

    private VBox labelContainer;

    public ChoiceFieldChoice(String title, String description, ChoicePosition choicePosition) {
        super();

        this.setupButton(choicePosition);
        this.setupElements(title, description);

        this.setGraphic(this.labelContainer);
    }

    private void setupButton(ChoicePosition choicePosition) {
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(40);

        var styleClass = this.getStyleClass();

        styleClass.add("choice-field-choice");
        if (choicePosition == ChoicePosition.FIRST) this.pseudoClassStateChanged(FIRST, true);
        if (choicePosition == ChoicePosition.LAST) this.pseudoClassStateChanged(LAST, true);
    }

    private void setupElements(String title, String description) {
        var parsedTitle = BBCodeParser.createFormattedText(title);
        parsedTitle.getChildren().getFirst().getStyleClass().add("choice-field-choice-title");

        var parsedDescription = BBCodeParser.createFormattedText("[color=-color-fg-muted]" + description + "[/color]");
        parsedDescription.getChildren().getFirst().getStyleClass().add("choice-field-choice-description");

        var titleContainer = new HBox(parsedTitle);
        var descriptionContainer = new HBox(parsedDescription);

        this.labelContainer = new VBox(titleContainer, descriptionContainer);
    }
}
