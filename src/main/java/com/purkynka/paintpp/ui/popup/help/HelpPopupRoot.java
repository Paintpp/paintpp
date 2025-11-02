package com.purkynka.paintpp.ui.popup.help;

import com.purkynka.paintpp.ui.shared.Title;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;

public class HelpPopupRoot extends PopupBaseRoot {
    private final GridPane gridPane;
    private final Title popupTitle;
    private final HelpPopupButtons popupHelpButtons;

    public HelpPopupRoot(Stage stage) {
        super(stage);
        stage.setTitle("Help");

        setPadding(new Insets(24));

        gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new Title("Help", new FontIcon(MaterialDesignH.HELP_CIRCLE));
        popupHelpButtons = new HelpPopupButtons(this::onNext, this::onBack);

        getChildren().addAll(popupTitle, gridPane, popupHelpButtons);
    }

    public void onNext() {

    }

    public void onBack() {

    }
}