package com.purkynka.paintpp.ui.primarystage.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class FileMenu extends Menu {
    private MenuItem newImageItem;
    private MenuItem loadImageItem;
    private MenuItem generateImageItem;

    private MenuItem saveItem;
    private MenuItem saveAsItem;

    private MenuItem exitItem;

    public FileMenu() {
        super("File");

        newImageItem = new MenuItem("New Image");
        loadImageItem = new MenuItem("Load Image");
        generateImageItem = new MenuItem("Generate Image");

        saveItem = new MenuItem("Save");
        saveAsItem = new MenuItem("Save As");

        exitItem = new MenuItem("Exit");

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
}
