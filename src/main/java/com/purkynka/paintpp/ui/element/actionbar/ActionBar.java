package com.purkynka.paintpp.ui.element.actionbar;

import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.ui.CommonCSS;
import com.purkynka.paintpp.ui.stage.about.AboutPopupStage;
import com.purkynka.paintpp.ui.stage.filteradder.FilterAdderPopupStage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

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

        var addFilterButton = new ActionBarButton(MaterialDesignF.FUNCTION_VARIANT, "Add Filter");
        addFilterButton.setDisable(true);
        addFilterButton.setOnMouseClicked(_ -> {
            var filterAdderPopupStage = new FilterAdderPopupStage();
            filterAdderPopupStage.open();
        });

        ImageManager.IMAGE_PROVIDER.addUpdateListener(imageProvider -> {
            addFilterButton.setDisable(imageProvider == null);
        });

        var aboutButton = new ActionBarButton(MaterialDesignI.INFORMATION_VARIANT, "About");
        aboutButton.setOnMouseClicked(_ -> new AboutPopupStage().open());

        this.getChildren().addAll(
                addFilterButton,
                filler,
                aboutButton
        );
    }
}
