package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIO {
    private static final FileChooser fileChooser = new FileChooser();

    static {
        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public static String openImageURI() {
        fileChooser.setTitle("Open Image");
        var chosenFile = fileChooser.showOpenDialog(MainStage.MAIN_STAGE);

        return chosenFile == null ? null : chosenFile.toURI().toString();
    }

    private static String getFileExtension(File file) {
        var filePath = file.getAbsolutePath();

        var lastDot = filePath.lastIndexOf('.');
        if (lastDot >= 0) {
            return filePath.substring(lastDot + 1);
        }

        return null;
    }

    public static void saveImage(Image image) throws IOException {
        fileChooser.setTitle("Save Image");

        var chosenFile = fileChooser.showSaveDialog(MainStage.MAIN_STAGE);
        if (chosenFile == null) return;

        var fileExtension = ImageIO.getFileExtension(chosenFile);
        if (fileExtension == null) fileExtension = "png";

        var bufferedImage = SwingFXUtils.fromFXImage(image, null);
        var bufferedImageNoAlpha = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImageNoAlpha.getGraphics().drawImage(bufferedImage, 0, 0, null);

        javax.imageio.ImageIO.write(
                bufferedImageNoAlpha,
                fileExtension,
                chosenFile
        );
    }
}
