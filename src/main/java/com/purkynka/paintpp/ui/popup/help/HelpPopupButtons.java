package com.purkynka.paintpp.ui.popup.help;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HelpPopupButtons extends HBox {
    private final CheckBox dontShowAgainCheckBox;
    private final Button backButton;
    private final Button nextButton;

    public HelpPopupButtons(Runnable onNext, Runnable onBack) {
        super(8);

        setPadding(new Insets(16, 0, 0, 0));

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        var centerVbox = new VBox();
        centerVbox.getChildren().add(
                dontShowAgainCheckBox = new CheckBox("Don't show again")
        );
        centerVbox.setAlignment(Pos.CENTER);

        backButton = new Button("Back");
        backButton.setOnAction(_ -> onNext.run());
        hideBackButton();

        nextButton = new Button("Next");
        nextButton.getStyleClass().add(Styles.ACCENT);
        nextButton.setOnAction(_ -> onBack.run());

        getChildren().addAll(filler, centerVbox, backButton, nextButton);
    }

    public void ChangeToFinishButton() {
        nextButton.setText("Finish");
    }

    public void ChangeToNextButton() {
        nextButton.setText("Next");
    }

    public void hideBackButton() {
        backButton.setVisible(false);
    }

    public void showBackButton() {
        backButton.setVisible(true);
    }
}
