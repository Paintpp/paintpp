package com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.logic.configmanager.Fonts;
import com.purkynka.paintpp.logic.configmanager.Themes;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.IntegerInput;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class AppearancePage extends SettingsPage {
    private final ChoiceInput<Themes> themeSelection;
    private final ChoiceInput<Fonts> fontSelection;
    private final Label fontSizeLabel;
    private final IntegerInput fontSizeSelection;

    public AppearancePage() {
        super();
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.TOP_LEFT);
        setVgap(10);
        setHgap(10);

        themeSelection = new ChoiceInput<>(this, "Theme:", Themes.class);
        themeSelection.setValue(ConfigManager.getTheme());
        setColumnSpan(themeSelection.getChoiceBox(), 3);
        themeSelection.getChoiceBox().setPrefWidth(250);

        fontSelection = new ChoiceInput<>(this, "Font family:", Fonts.class);
        fontSelection.setValue(ConfigManager.getFont());
        fontSelection.getChoiceBox().setPrefWidth(250);

        fontSizeLabel = new Label("Font size:");
        fontSizeSelection = new IntegerInput(8, 24, ConfigManager.getFontSize());
        fontSizeSelection.setMaxWidth(50);
        add(fontSizeLabel, 0, 2);
        add(fontSizeSelection, 1, 2);
        
        setupEventListeners();
    }

    private void setupEventListeners() {
        themeSelection.getChoiceBox().valueProperty().addListener((_, _, newTheme) -> {
            if (newTheme != null) {
                ConfigManager.setTheme(newTheme);
            }
        });

        fontSelection.getChoiceBox().valueProperty().addListener((_, _, newFont) -> {
            if (newFont != null) {
                ConfigManager.setFont(newFont);
            }
        });

        fontSizeSelection.getValueProperty().addListener(_ -> {
            if (fontSizeSelection.isInputValid()) {
                ConfigManager.setFontSize(fontSizeSelection.getValue());
                for (Node node : getChildren()) {
                    node.autosize();
                }
            }
        });
    }
}
