package com.purkynka.paintpp.ui.primarystage.menubar;

import com.purkynka.paintpp.logic.image.imageprovider.LoadedImageProvider;
import com.purkynka.paintpp.ui.generateimagepopup.GenerateImagePopup;
import com.purkynka.paintpp.ui.newimagepopup.NewImagePopup;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

/**
 * {@link Menu} for the main file menu actions.
 * <p>
 * Includes creating a new empty image, loading one from disk and generating one from code.
 * <p>
 * Also includes saving the image to the same location or as a new image and exiting the editor.
 */
public class FileMenu extends Menu {
    private MenuItem newImageItem;
    private MenuItem loadImageItem;
    private MenuItem generateImageItem;

    private MenuItem saveItem;
    private MenuItem saveAsItem;

    private MenuItem exitItem;

    private FileChooser fileChooser;

    /**
     * Constructs a new {@link FileMenu}.
     */
    public FileMenu() {
        super("File");

        newImageItem = new MenuItem("New Image");
        newImageItem.setOnAction(_ -> new NewImagePopup());

        loadImageItem = new MenuItem("Load Image");
        loadImageItem.setOnAction(_ -> loadImage());

        generateImageItem = new MenuItem("Generate Image");
        generateImageItem.setOnAction(_ -> new GenerateImagePopup());

        saveItem = new MenuItem("Save");
        saveAsItem = new MenuItem("Save As");

        exitItem = new MenuItem("Exit");

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PNG", "*.png"),
                new ExtensionFilter("JPG, JPEG", "*.jpg", "*.jpeg"),
                new ExtensionFilter("GIF", "*.gif"),
                new ExtensionFilter("BMP", "*.bmp"),
                new ExtensionFilter("Other", "*.*")
        );

        getItems().addAll(
                newImageItem,
                loadImageItem,
                generateImageItem,
                new SeparatorMenuItem(),
                saveItem,
                saveAsItem,
                new SeparatorMenuItem(),
                exitItem
        );
    }

    /**
     * Shows the {@link FileChooser} dialog to let the user choose a {@link File},
     * then runs the {@link ImageViewer#IMAGE_PROVIDER_CHANGED} event with a {@link LoadedImageProvider} of the file.
     */
    private void loadImage() {
        var file = fileChooser.showOpenDialog(PrimaryStage.PRIMARY_STAGE);
        if (file == null) return;

        ImageViewer.IMAGE_PROVIDER_CHANGED.send(new LoadedImageProvider(file));
    }
}
