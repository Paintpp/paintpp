package com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar;

import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.colorpicker.ColorPicker;
import com.purkynka.paintpp.ui.primarystage.mainview.rightsidebar.layerhierarchy.LayerHierarchy;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

public class RightSidebar extends SplitPane {
    private ColorPicker colorPicker;
    private LayerHierarchy  layerHierarchy;

    public RightSidebar() {
        super();

        setOrientation(Orientation.VERTICAL);

        colorPicker = new ColorPicker();
        layerHierarchy = new LayerHierarchy();

        getItems().addAll(colorPicker, layerHierarchy);
    }
}
