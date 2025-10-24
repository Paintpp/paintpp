package com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar;

import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.tooloptions.ToolOptions;
import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.toolpicker.ToolPicker;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

public class LeftSidebar extends SplitPane {
    private ToolPicker toolPicker;
    private ToolOptions toolOptions;

    public LeftSidebar() {
        super();

        setOrientation(Orientation.VERTICAL);

        toolPicker = new ToolPicker();
        toolOptions = new ToolOptions();

        getItems().addAll(toolPicker, toolOptions);
    }
}
