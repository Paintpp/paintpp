package com.purkynka.paintpp.ui.element;

import atlantafx.base.util.BBCodeParser;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChoiceListButton extends Button {
    private VBox labelContainer;

    public ChoiceListButton(String title, String description) {
        super();

        this.setupButton();
        this.setupElements(title, description);

        this.setGraphic(this.labelContainer);
    }

    private void setupButton() {
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(40);

        var styleClass = this.getStyleClass();

        styleClass.add("choice-list-choice");
    }

    private void setupElements(String title, String description) {
        var parsedTitle = BBCodeParser.createFormattedText(title);
        parsedTitle.getChildren().getFirst().getStyleClass().add("choice-list-choice-title");

        var parsedDescription = BBCodeParser.createFormattedText("[color=-color-fg-muted]" + description + "[/color]");
        parsedDescription.getChildren().getFirst().getStyleClass().add("choice-list-choice-description");

        var titleContainer = new HBox(parsedTitle);
        var descriptionContainer = new HBox(parsedDescription);

        this.labelContainer = new VBox(titleContainer, descriptionContainer);
    }
}
