package com.purkynka.paintpp.ui.primarystage.menubar;

import com.purkynka.paintpp.logic.image.imageprovider.LoadedImageProvider;
import com.purkynka.paintpp.ui.popup.generateimage.GenerateImagePopup;
import com.purkynka.paintpp.ui.popup.newimage.NewImagePopup;
import com.purkynka.paintpp.ui.primarystage.PrimaryStage;
import com.purkynka.paintpp.ui.primarystage.mainview.imageviewer.ImageViewer;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;

import java.io.File;

/**
 * {@link Menu} for the main file menu actions.
 * <p>
 * Includes creating a new empty image, loading one from disk and generating one from code.
 * <p>
 * Also includes saving the image to the same location or as a new image and exiting the editor.
 */
public class FileMenu extends MenuButton {
    private final MenuItem newImageItem;
    private final MenuItem loadImageItem;
    private final MenuItem generateImageItem;

    private final MenuItem saveItem;
    private final MenuItem saveAsItem;
    private final MenuItem exportAsItem;
    
    private final MenuItem exitItem;

    private final FileChooser fileChooser;

    /**
     * Constructs a new {@link FileMenu}.
     */
    public FileMenu() {
        super("_File", new FontIcon(MaterialDesignF.FILE));
        setMnemonicParsing(true);
        
        newImageItem = new MenuItem("_New Image", new FontIcon(MaterialDesignF.FILE_PLUS));
        newImageItem.setMnemonicParsing(true);
        newImageItem.setOnAction(_ -> new NewImagePopup());

        loadImageItem = new MenuItem("_Load Image", new FontIcon(MaterialDesignF.FILE_SEARCH));
        loadImageItem.setMnemonicParsing(true);
        loadImageItem.setOnAction(_ -> loadImage());

        generateImageItem = new MenuItem("_Generate Image", new FontIcon(MaterialDesignF.FILE_COG));
        generateImageItem.setMnemonicParsing(true);
        generateImageItem.setOnAction(_ -> new GenerateImagePopup());

        saveItem = new MenuItem("_Save", new FontIcon(MaterialDesignF.FILE_SYNC));
        saveItem.setMnemonicParsing(true);
        saveAsItem = new MenuItem("Save _As", new FontIcon(MaterialDesignF.FILE_MOVE));
        saveAsItem.setMnemonicParsing(true);
        exportAsItem = new MenuItem("_Export As", new FontIcon(MaterialDesignF.FILE_EXPORT));
        exportAsItem.setMnemonicParsing(true);

        exitItem = new MenuItem("E_xit", new FontIcon(MaterialDesignE.EXIT_TO_APP));
        exitItem.setMnemonicParsing(true);
        exitItem.setOnAction(_ -> Platform.exit());

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"),
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
                exportAsItem,
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
