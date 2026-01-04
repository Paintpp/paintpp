package com.purkynka.paintpp.ui.element.sidebar.imageswapper;

import com.purkynka.paintpp.logic.image.ImageManager;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

public class ImageSwapperButtonContainer extends VBox {
    private static final PseudoClass ACTIVE = PseudoClass.getPseudoClass("active");

    private Button originalImageButton;
    private Button modifiedImageButton;

    public ImageSwapperButtonContainer() {
        super();

        this.setFillWidth(true);

        this.setupElements();
        this.setupHandlers();

        this.getChildren().addAll(this.originalImageButton, this.modifiedImageButton);
    }

    private void setupElements() {
        this.originalImageButton = this.createImageSwapperButton(
                this,
                MaterialDesignI.IMAGE,
                "Original",
                "Original unmodified image."
        );
        this.originalImageButton.getStyleClass().add("original");

        this.modifiedImageButton = this.createImageSwapperButton(
                this,
                MaterialDesignI.IMAGE_EDIT,
                "Modified",
                "Image after applying filters."
        );
        this.modifiedImageButton.getStyleClass().add("modified");
        this.modifiedImageButton.pseudoClassStateChanged(ACTIVE, true);
    }

    private Button createImageSwapperButton(VBox container, Ikon icon, String titleText, String descriptionText) {
        var button = new Button();
        button.setPadding(new Insets(8));
        button.setMinHeight(64);
        button.setMaxWidth(Double.MAX_VALUE);
        // button.minWidthProperty().bind(container.widthProperty());
        button.getStyleClass().add("image-swapper-button");

        var buttonIcon = new FontIcon(icon);
        buttonIcon.setIconSize(32);
        buttonIcon.getStyleClass().add("image-swapper-button-icon");
        StackPane.setAlignment(buttonIcon, Pos.CENTER);

        var iconContainer = new StackPane(buttonIcon);
        iconContainer.setMinSize(64, 64);
        iconContainer.setMaxSize(64, 64);

        var titleTextLabel = new Label(titleText);
        titleTextLabel.getStyleClass().add("image-swapper-button-title");

        var descriptionTextLabel = new Label(descriptionText);
        descriptionTextLabel.getStyleClass().add("image-swapper-button-description");

        var textContainer = new VBox(titleTextLabel, descriptionTextLabel);
        textContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(textContainer, Priority.ALWAYS);

        var buttonContent = new HBox(8, iconContainer, textContainer);

        button.setGraphic(buttonContent);

        return button;
    }

    private void setupHandlers() {
        this.originalImageButton.setOnAction(_ -> {
            var displayingModifiedImage = ImageManager.DISPLAYING_MODIFIED_IMAGE.get();
            if (!displayingModifiedImage) return;

            ImageManager.DISPLAYING_MODIFIED_IMAGE.set(false);
        });

        this.modifiedImageButton.setOnAction(_ -> {
            var displayingModifiedImage = ImageManager.DISPLAYING_MODIFIED_IMAGE.get();
            if (displayingModifiedImage) return;

            ImageManager.DISPLAYING_MODIFIED_IMAGE.set(true);
        });

        ImageManager.DISPLAYING_MODIFIED_IMAGE.addUpdateListener(this::updateActiveButton);
    }

    private void updateActiveButton(boolean displayingModifiedImage) {
        this.originalImageButton.pseudoClassStateChanged(ACTIVE, !displayingModifiedImage);
        this.modifiedImageButton.pseudoClassStateChanged(ACTIVE, displayingModifiedImage);
    }
}
