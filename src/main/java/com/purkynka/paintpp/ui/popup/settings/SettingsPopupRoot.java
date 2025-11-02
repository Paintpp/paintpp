package com.purkynka.paintpp.ui.popup.settings;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.ui.popup.settings.components.sidebar.Sidebar;
import com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages.SettingPages;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SettingsPopupRoot extends PopupBaseRoot {
    protected final GridPane gridPane;
    protected final Sidebar sidebar;

    protected final StackPane pagePanel;
    protected final Button popupCloseButton;


    public SettingsPopupRoot(Stage stage, String title, SettingPages settingPages) {
        super(stage);
        stage.setTitle(title);

        gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        pagePanel = new StackPane();
        pagePanel.setPadding(new Insets(16, 16, 0, 8));

        pagePanel.prefWidthProperty().bind(stage.widthProperty());
        pagePanel.prefHeightProperty().bind(stage.heightProperty());

        sidebar = new Sidebar(settingPages, pagePanel);

        popupCloseButton = new Button("Close");
        popupCloseButton.setOnMouseClicked(_ -> onClose());
        popupCloseButton.getStyleClass().add(Styles.ACCENT);
        popupCloseButton.setAlignment(Pos.CENTER);
        popupCloseButton.setPrefWidth(100);
        popupCloseButton.setPrefHeight(30);
        GridPane.setMargin(popupCloseButton, new Insets(0, 8, 8, 0));
        GridPane.setHalignment(popupCloseButton, HPos.RIGHT);
        GridPane.setValignment(popupCloseButton, VPos.CENTER);

        gridPane.add(sidebar, 0, 0, 1, 3);
        gridPane.add(pagePanel, 1, 0);
        gridPane.add(popupCloseButton, 1, 2, 1, 1);

        getChildren().add(gridPane);
        
        stage.setWidth(750);
        stage.setHeight(500);

        stage.sceneProperty().addListener((_, _, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) onClose();
                });
            }
        });
    }

    protected void onClose() {
        stage.close();
    }
}
