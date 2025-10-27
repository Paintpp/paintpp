package com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar;

import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.tooloptions.ToolOptions;
import com.purkynka.paintpp.ui.primarystage.mainview.leftsidebar.toolpicker.ToolPicker;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

/**
 * {@link SplitPane} containing the left sidebar contents.
 */
public class LeftSidebar extends SplitPane {
    private final ToolPicker toolPicker;
    private final ToolOptions toolOptions;

    /**
     * Constructs a new {@link LeftSidebar}.
     */
    public LeftSidebar() {
        super();

        setOrientation(Orientation.VERTICAL);

        toolPicker = new ToolPicker();
        toolOptions = new ToolOptions();

        getItems().addAll(toolPicker, toolOptions);
    }
}
