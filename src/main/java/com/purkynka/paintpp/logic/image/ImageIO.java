package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.stage.FileChooser;

public class ImageIO {
    private static final FileChooser fileChooser = new FileChooser();

    static {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public static String openImageURI() {
        fileChooser.setTitle("Open image");
        var chosenFile = fileChooser.showOpenDialog(MainStage.MAIN_STAGE);

        return chosenFile == null ? null : chosenFile.toURI().toString();
    }
}
