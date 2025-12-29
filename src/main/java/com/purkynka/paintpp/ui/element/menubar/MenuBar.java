package com.purkynka.paintpp.ui.element.menubar;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

public class MenuBar extends ToolBar {
    public MenuBar() {
        super();

        var fileMenu = new MenuButton("File", new FontIcon(MaterialDesignF.FILE));
        fileMenu.getItems().addAll(
                new MenuItem("Generate Image", new FontIcon(MaterialDesignF.FILE_PLUS)),
                new MenuItem("Load Image", new FontIcon(MaterialDesignF.FILE_UPLOAD)),
                new SeparatorMenuItem(),
                new MenuItem("Save Image", new FontIcon(MaterialDesignF.FILE_DOWNLOAD)),
                new MenuItem("Save As", new FontIcon(MaterialDesignF.FILE_DOWNLOAD))
        );

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        var aboutButton = new Button("About", new FontIcon(MaterialDesignI.INFORMATION_VARIANT));
        aboutButton.getStyleClass().add("menu-bar-about-button");

        var exitButton = new Button("", new FontIcon(MaterialDesignC.CLOSE));
        exitButton.getStyleClass().add("menu-bar-exit-button");

        this.getItems().addAll(fileMenu, filler, aboutButton, exitButton);
    }
}
