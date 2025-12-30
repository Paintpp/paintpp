package com.purkynka.paintpp.ui.stage.popup;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.ui.CommonCSS;
import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;

public abstract class PopupStage extends Stage {
    private Parent root;

    private boolean showTitle;
    private String titleText;

    private ArrayList<Button> buttons = new ArrayList<>();

    public PopupStage() {
        this.initStyle(StageStyle.UNDECORATED);
        this.initOwner(MainStage.MAIN_STAGE);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
    }

    public void setPopupTitle(String titleText) {
        this.titleText = titleText;
        this.setTitle(this.titleText);
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void addButtons(Button... buttons) {
        Collections.addAll(this.buttons, buttons);
    }

    public void addDefaultButtons(
            String submitText,
            Runnable onSubmit,
            String cancelText
    ) {
        var submitButton = new Button(submitText);
        submitButton.setOnAction((_) -> onSubmit.run());
        submitButton.getStyleClass().add(Styles.SUCCESS);

        var cancelButton = new Button(cancelText);
        cancelButton.setOnAction((_) -> this.close());
        cancelButton.getStyleClass().add(Styles.DANGER);

        this.addButtons(cancelButton, submitButton);
    }

    public void addDefaultButtons(
            Runnable onSubmit
    ) {
        this.addDefaultButtons("Submit", onSubmit, "Cancel");
    }

    private void addTitle(ObservableList<Node> children, String titleText) {
        var label = new Label(titleText);
        label.getStyleClass().add("popup-title");

        children.add(label);
    }

    private void addPopupButtons(ObservableList<Node> children) {
        var buttonContainer = new HBox(4);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().addAll(this.buttons);

        children.add(buttonContainer);
    }

    public void open() {
        if (this.root == null) {
            this.root = this.createRoot();

            var container = new VBox(16);
            container.setPadding(new Insets(16));

            var containerChildren = container.getChildren();

            if (this.showTitle) this.addTitle(containerChildren, this.titleText);
            containerChildren.add(this.root);
            if (!this.buttons.isEmpty()) this.addPopupButtons(containerChildren);

            var scene = new Scene(container);
            CommonCSS.addStylesheetsToScene(scene);

            this.setScene(scene);
        }

        this.show();
    }

    protected abstract Parent createRoot();
}
