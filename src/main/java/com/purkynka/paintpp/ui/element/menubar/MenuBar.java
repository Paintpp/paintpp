package com.purkynka.paintpp.ui.element.menubar;

import com.purkynka.paintpp.logic.image.ImageIO;
import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.image.provider.LoadedImageProvider;
import com.purkynka.paintpp.ui.stage.imagegenerator.ImageGeneratorPopupStage;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

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

        var saveAsButton = new MenuItem("Save As", new FontIcon(MaterialDesignF.FILE_DOWNLOAD));
        saveAsButton.setOnAction((_) -> this.onSaveAs());

        fileMenu.getItems().addAll(
                generateImageButton,
                loadImageButton,
                new SeparatorMenuItem(),
                saveImageButton,
                saveAsButton
        );

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        var aboutButton = new Button("About", new FontIcon(MaterialDesignI.INFORMATION_VARIANT));
        aboutButton.getStyleClass().add("menu-bar-about-button");

        var exitButton = new Button("", new FontIcon(MaterialDesignC.CLOSE));
        exitButton.getStyleClass().add("menu-bar-exit-button");

        this.getItems().addAll(fileMenu, filler, aboutButton, exitButton);
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

    private void onSaveImage() {}

    private void onSaveAs() {}

    private void onAbout() {}

    private void onExit() {}
}
