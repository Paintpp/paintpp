package com.purkynka.paintpp.ui.popup.about;

import com.purkynka.paintpp.Paintpp;
import com.purkynka.paintpp.ui.shared.Title;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;

public class AboutPopupRoot extends PopupBaseRoot {
    private final Stage stage;
    protected final Title popupTitle;
    protected final GridPane gridPane;
    protected final PopupAboutButton popupAboutButton;

    public AboutPopupRoot(Stage stage) {
        super();
        this.stage = stage;
        gridPane = new GridPane();

        setPadding(new Insets(24));

        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new Title("About", new FontIcon(MaterialDesignH.HELP_CIRCLE));
        popupAboutButton = new PopupAboutButton("Close", this::onClose);

        getChildren().addAll(popupTitle, gridPane, popupAboutButton);

        // row 1
        gridPane.add(new Label("Made by Painpp team <3"), 0, 1, 2, 1);

        // row 2
        var githubLink = new Hyperlink("https://github.com/Paintpp/paintpp");
        gridPane.add(new Label("Github:"), 0, 2, 1, 1);
        gridPane.add(githubLink, 1, 2, 2, 1);

        githubLink.setOnAction(_ -> Paintpp.hostServices.showDocument(githubLink.getText()));
        stage.sceneProperty().addListener((_, _, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case KeyCode.ENTER, KeyCode.ESCAPE -> onClose();
                    }
                });
            }
        });
    }

    public void onClose() {
        stage.close();
    }
}