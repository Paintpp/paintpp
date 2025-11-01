package com.purkynka.paintpp.ui.shared;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * Wrapper class for the title {@link Label} of a {@link PopupStage}.
 */
public class Title extends Label {
    /**
     * Constructs a new {@link Title} from the provided {@link String}.
     *
     * @param text The text inside the title
     */
    public Title(String text, FontIcon icon) {
        super(text, icon);

        setFont(new Font(24));
        getStyleClass().add(Styles.TEXT_BOLD);
        setPadding(new Insets(0, 0, 16, 0));
    }

    public Title(String text) {
        super(text);

        setFont(new Font(24));
        getStyleClass().add(Styles.TEXT_BOLD);
        setPadding(new Insets(0, 0, 16, 0));
    }
}
