package com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar;

import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.colorpicker.ColorPicker;
import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.layerhierarchy.LayerHierarchy;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

/**
 * {@link SplitPane} containing the right sidebar contents.
 */
public class RightSidebar extends SplitPane {
    private final ColorPicker colorPicker;
    private final LayerHierarchy layerHierarchy;

    /**
     * Constructs a new {@link RightSidebar}.
     */
    public RightSidebar() {
        super();

        setOrientation(Orientation.VERTICAL);

        colorPicker = new ColorPicker();
        layerHierarchy = new LayerHierarchy();

        getItems().addAll(colorPicker, layerHierarchy);
    }
}
