package com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages;

import com.purkynka.paintpp.logic.configmanager.ConfigManager;
import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.ui.popup.settings.components.CheckBoxOption;
import com.purkynka.paintpp.ui.shared.form.ChoiceInput;
import com.purkynka.paintpp.ui.shared.form.ColorInput;
import com.purkynka.paintpp.ui.shared.form.SizeInput;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;


public class GeneralPage extends SettingsPage {
    private final ChoiceInput<MaxSizes> maxSizeSelection;

    private final CheckBoxOption useLastUsedSize;
    private final SizeInput preferredSizeInput;

    private final CheckBoxOption useLastUsedColor;
    private final ColorInput preferredColorInput;

    private final CheckBoxOption useLastUsedGenerationType;
    private final ChoiceInput<ImageGenerationType> preferredGenerationTypeSelection;

    public GeneralPage() {
        super();
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.TOP_LEFT);
        setVgap(10);
        setHgap(10);

        maxSizeSelection = new ChoiceInput<>(this, "Max image size:", MaxSizes.class);
        setColumnSpan(maxSizeSelection.getChoiceBox(), 3);
        maxSizeSelection.setValue(MaxSizes.getClosest(ConfigManager.getMaxImageSize()));

        useLastUsedSize = new CheckBoxOption("Use last used size:");
        add(useLastUsedSize, 0, 2, 3, 1);
        useLastUsedSize.getCheckBox().setSelected(ConfigManager.isRememberImageSize());

        preferredSizeInput = new SizeInput(this, "Preferred size:", true);
        preferredSizeInput.setDisabled(ConfigManager.isRememberImageSize());

        useLastUsedColor = new CheckBoxOption("Use last used color:");
        add(useLastUsedColor, 0, 5, 3, 1);
        useLastUsedColor.getCheckBox().setSelected(ConfigManager.isRememberBackgroundColor());

        preferredColorInput = new ColorInput(this, "Preferred color:");
        setColumnSpan(preferredColorInput.getColorPicker(), 3);
        preferredColorInput.setDisabled(ConfigManager.isRememberBackgroundColor());

        useLastUsedGenerationType = new CheckBoxOption("Use last used generation type:");
        add(useLastUsedGenerationType, 0, 7, 3, 1);
        useLastUsedGenerationType.getCheckBox().setSelected(ConfigManager.isRememberGenerationType());

        preferredGenerationTypeSelection = new ChoiceInput<>(this, "Generation type:", ImageGenerationType.class);
        preferredGenerationTypeSelection.setValue(ConfigManager.getPreferredGenerationType());
        preferredGenerationTypeSelection.setDisabled(ConfigManager.isRememberGenerationType());

        this.getColumnConstraints().add(new ColumnConstraints(150));
        setupEventListeners();
    }

    private void setupEventListeners() {
        maxSizeSelection.getChoiceBox().valueProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                ConfigManager.setMaxImageSize(newValue.getImageSize());
            }
        });

        useLastUsedSize.getCheckBox().selectedProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                ConfigManager.setRememberImageSize(newValue);
                preferredSizeInput.setDisabled(ConfigManager.isRememberImageSize());
            }
        });
        preferredSizeInput.imageSizeChanged.addEventListener(ConfigManager::setPreferredImageSize);


        useLastUsedColor.getCheckBox().selectedProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                ConfigManager.setRememberBackgroundColor(newValue);
                preferredColorInput.setDisabled(ConfigManager.isRememberBackgroundColor());
            }
        });
        preferredColorInput.getColorPicker().valueProperty().addListener((_, _, newColor) -> {
            if (newColor != null) {
                ConfigManager.setPreferredBackgroundColor(newColor);
            }
        });

        useLastUsedGenerationType.getCheckBox().selectedProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                ConfigManager.setRememberGenerationType(newValue);
                preferredGenerationTypeSelection.setDisabled(ConfigManager.isRememberGenerationType());
            }
        });
        preferredGenerationTypeSelection.getChoiceBox().valueProperty().addListener((_, _, newGenerationType) -> {
            if (newGenerationType != null) {
                ConfigManager.setPreferredGenerationType(newGenerationType);
            }
        });
    }
}
