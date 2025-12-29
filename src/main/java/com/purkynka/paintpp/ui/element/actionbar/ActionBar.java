package com.purkynka.paintpp.ui.element.actionbar;

import com.purkynka.paintpp.ui.CommonCSS;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;

public class ActionBar extends VBox {
    public static final double ACTION_BAR_WIDTH = 96d;
    private static final double ACTUAL_ACTION_BAR_WIDTH = ACTION_BAR_WIDTH + CommonCSS.BORDER_WIDTH;

    public ActionBar() {
        super();

        this.getStyleClass().add("action-bar");

        this.setMinWidth(ACTUAL_ACTION_BAR_WIDTH);
        this.setMaxWidth(ACTUAL_ACTION_BAR_WIDTH);

        var filler = new Pane();
        VBox.setVgrow(filler, Priority.ALWAYS);

        this.getChildren().addAll(
                new ActionBarButton(MaterialDesignF.FUNCTION_VARIANT, "Add Filter"),
                filler,
                new ActionBarButton(MaterialDesignC.COG, "Settings"),
                new ActionBarButton(MaterialDesignH.HELP, "Help")
        );
    }
}
