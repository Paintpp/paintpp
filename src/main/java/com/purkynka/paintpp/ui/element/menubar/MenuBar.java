package com.purkynka.paintpp.ui.element.menubar;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.image.ImageIO;
import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.image.provider.LoadedImageProvider;
import com.purkynka.paintpp.ui.stage.imagegenerator.ImageGeneratorPopupStage;
import com.purkynka.paintpp.ui.stage.main.MainStage;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

import java.io.IOException;

public class MenuBar extends ToolBar {
    public MenuBar() {
        super();

        var fileMenu = new MenuButton("File", new FontIcon(MaterialDesignF.FILE));

        var generateImageButton = new MenuItem("Generate Image", new FontIcon(MaterialDesignF.FILE_PLUS));
        generateImageButton.setOnAction((_) -> this.onGenerateImage());

        var loadImageButton = new MenuItem("Load Image", new FontIcon(MaterialDesignF.FILE_UPLOAD));
        loadImageButton.setOnAction((_) -> this.onLoadImage());

        var saveImageButton = new MenuItem("Save Image", new FontIcon(MaterialDesignF.FILE_DOWNLOAD));
        saveImageButton.setOnAction((_) -> this.onSaveImage());

        fileMenu.getItems().addAll(
                generateImageButton,
                loadImageButton,
                new SeparatorMenuItem(),
                saveImageButton
        );

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        var aboutButton = new Button("About", new FontIcon(MaterialDesignI.INFORMATION_VARIANT));
        aboutButton.getStyleClass().add("menu-bar-about-button");

        this.getItems().addAll(fileMenu, filler, aboutButton);
    }

    private void onGenerateImage() {
        var imageGeneratorPopupStage = new ImageGeneratorPopupStage();
        imageGeneratorPopupStage.open();
    }

    private void onLoadImage() {
        var chosenImagePath = ImageIO.openImageURI();
        if (chosenImagePath == null) return;

        ImageManager.IMAGE_PROVIDER.set(new LoadedImageProvider(chosenImagePath));
    }

    private void onSaveImage() {
        var currentFilteredImage = FilterManager.FILTERED_IMAGE.get();

        try {
            ImageIO.saveImage(currentFilteredImage.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onAbout() {}
}
