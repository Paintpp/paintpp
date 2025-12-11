package com.purkynka.paintpp.ui.popup.help;

import com.purkynka.paintpp.ui.shared.Title;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;

import java.util.ArrayList;
import java.util.List;

public class HelpPopupRoot extends PopupBaseRoot {

    private final List<HelpSection> sections = new ArrayList<>();

    private final VBox menuBox;
    private final VBox contentBox;
    private final Label contentTitle;
    private final Label contentText;

    private int selectedIndex = 0;

    public HelpPopupRoot(Stage stage) {
        super(stage);
        stage.setTitle("Help Window");

        setPadding(new Insets(24));
        setSpacing(16);
        initializeSections();
        Title popupTitle = new Title("Help", new FontIcon(MaterialDesignH.HELP_CIRCLE));
        HBox mainLayout = new HBox(16);
        mainLayout.setPrefHeight(300);
        mainLayout.setPrefWidth(500);
        VBox.setVgrow(mainLayout, Priority.ALWAYS);

        // Left menu
        menuBox = createMenuBox();

        // Right menu
        contentTitle = new Label();
        contentTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        contentText = new Label();
        contentText.setWrapText(true);
        contentText.setStyle("-fx-font-size: 14px;");
        contentBox = new VBox(12);
        contentBox.setPadding(new Insets(16));
        contentBox.setStyle("-fx-background-color: -color-bg-subtle; -fx-background-radius: 8;");
        contentBox.getChildren().addAll(contentTitle, contentText);

        // ScrollPane
        ScrollPane contentScrollPane = new ScrollPane(contentBox);
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        HBox.setHgrow(contentScrollPane, Priority.ALWAYS);

        mainLayout.getChildren().addAll(menuBox, contentScrollPane);

        // Close B
        Button closeButton = new Button("Zavřít");
        closeButton.setOnAction(_ -> stage.close());
        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-alignment: center-right;");
        buttonBox.getChildren().add(closeButton);

        // UI
        getChildren().addAll(popupTitle, mainLayout, buttonBox);
        showSection(0);
    }

    // Sekce
    private void initializeSections() {
        sections.add(new HelpSection(
                "Začátek",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \
                        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.
                        
                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore."""));

        sections.add(new HelpSection(
                "Nástroje",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Praesent euismod, nisl eget ultricies tincidunt, nisl nisl aliquam nisl.
                        
                        Nulla facilisi. Sed euismod, nisl eget ultricies tincidunt."""));

        sections.add(new HelpSection(
                "Filtry",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Vestibulum ante ipsum primis in faucibus orci luctus et ultrices.
                        
                        Posuere cubilia curae; Donec velit neque, auctor sit amet aliquam vel."""));

        sections.add(new HelpSection(
                "Barvy",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Curabitur aliquet quam id dui posuere blandit. Vivamus magna justo.
                        
                        Lacinia eget consectetur sed, convallis at tellus."""));

        sections.add(new HelpSection(
                "Vrstvy",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Pellentesque in ipsum id orci porta dapibus. Cras ultricies ligula.
                        
                        Sed porttitor lectus nibh. Nulla quis lorem ut libero malesuada feugiat."""));

        sections.add(new HelpSection(
                "Klávesové zkratky",
                """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. \
                        Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a.
                        
                        Quisque velit nisi, pretium ut lacinia in, elementum id enim."""));
    }

    // Levo
    private VBox createMenuBox() {
        VBox menu = new VBox(4);
        menu.setPadding(new Insets(8));
        menu.setStyle("-fx-background-color: -color-bg-subtle; -fx-background-radius: 8;");
        menu.setMinWidth(150);

        for (int i = 0; i < sections.size(); i++) {
            final int index = i;
            HelpSection section = sections.get(i);

            Button menuButton = new Button(section.getName());
            menuButton.setMaxWidth(Double.MAX_VALUE);
            menuButton.setOnAction(_ -> showSection(index));

            menu.getChildren().add(menuButton);
        }

        return menu;
    }

    // Pravo
    private void showSection(int index) {
        if (index < 0 || index >= sections.size())
            return;

        selectedIndex = index;
        HelpSection section = sections.get(index);

        // Update obsahu
        contentTitle.setText(section.getName());
        contentText.setText(section.getContent());
        updateMenuSelection();
    }

    private void updateMenuSelection() {
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Button button = (Button) menuBox.getChildren().get(i);

            if (i == selectedIndex) {
                button.setStyle("-fx-background-color: -color-accent-emphasis; -fx-text-fill: white;");
            } else {
                button.setStyle("");
            }
        }
    }
}