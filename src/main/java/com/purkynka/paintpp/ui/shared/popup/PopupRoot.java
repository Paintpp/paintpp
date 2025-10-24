package com.purkynka.paintpp.ui.shared.popup;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class PopupRoot extends VBox {
    protected PopupTitle popupTitle;
    protected GridPane gridPane;
    protected PopupButtons popupButtons;

    public PopupRoot(String title, String cancelText, String submitText) {
        super();

        gridPane = new GridPane();

        setPadding(new Insets(24));

        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new PopupTitle(title);
        popupButtons = new PopupButtons(cancelText, this::onCancel, submitText, this::onSubmit);

        getChildren().addAll(popupTitle, gridPane, popupButtons);
    }

    public PopupRoot(String title) {
        this(title, "Cancel", "Submit");
    }

    protected abstract void onCancel();
    protected abstract void onSubmit();
}
