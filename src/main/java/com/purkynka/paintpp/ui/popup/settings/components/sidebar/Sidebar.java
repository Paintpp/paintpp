package com.purkynka.paintpp.ui.popup.settings.components.sidebar;


import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages.SettingPages;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sidebar extends ListView<String> {
    protected final Pane pagePane;

    public Sidebar(SettingPages selectedPage, Pane pagePane) {
        setStyle(
                "-color-cell-bg-selected: -color-accent-emphasis;" +
                        "-color-cell-fg-selected: -color-fg-emphasis;" +
                        "-color-cell-bg-selected-focused: -color-accent-emphasis;" +
                        "-color-cell-fg-selected-focused: -color-fg-emphasis;"
        );

        this.pagePane = pagePane;

        var pages = FXCollections.observableArrayList(
                Arrays.stream(SettingPages.values())
                        .map(v -> capitalize(v.toString()))
                        .collect(Collectors.toList())
        );
        setItems(pages);

        getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                onPageSelected(newValue);
            }
        });

        getSelectionModel().select(capitalize(selectedPage.name()));
        setMinWidth(8 * ConfigManager.getFontSize());
        setPrefWidth(8 * ConfigManager.getFontSize());

        ConfigManager.FONT_SIZE_CHANGED.addEventListener(this::onFontSizeChanged);
    }

    private void onFontSizeChanged(int fontSize) {
        setMinWidth(8 * ConfigManager.getFontSize());
        setPrefWidth(8 * ConfigManager.getFontSize());
    }

    private void onPageSelected(String pageName) {
        pagePane.getChildren().clear();

        for (SettingPages page : SettingPages.values()) {
            if (capitalize(page.name()).equals(pageName)) {
                pagePane.getChildren().add(page.getPage());
            }
        }
    }

    private static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
