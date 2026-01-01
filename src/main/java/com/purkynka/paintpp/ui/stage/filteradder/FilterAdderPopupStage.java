package com.purkynka.paintpp.ui.stage.filteradder;

import com.purkynka.paintpp.logic.filter.FilterType;
import com.purkynka.paintpp.ui.element.ChoiceListButton;
import com.purkynka.paintpp.ui.element.ElementOrder;
import com.purkynka.paintpp.ui.element.form.context.FormContext;
import com.purkynka.paintpp.ui.element.form.input.textfield.StringTextField;
import com.purkynka.paintpp.ui.stage.popup.PopupStage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class FilterAdderPopupStage extends PopupStage {
    private final FormContext<FilterAdderFormValue> formContext = new FormContext<>(new FilterAdderFormValue());

    private ScrollPane filterAdderScrollPane = new ScrollPane();
    private final VBox filterAdderList = new VBox();

    public FilterAdderPopupStage() {
        super();

        this.setPopupTitle("Add Filter");

        this.formContext.getObservableFormValue().addUpdateListener(v -> {
            var filteredFilterTypes = this.getFilteredFilterTypes(v.filterQuery);
            var filterAdderListChildren = this.filterAdderList.getChildren();
            filterAdderListChildren.clear();

            for (int i = 0; i < filteredFilterTypes.length; i++) {
                var filterType = filteredFilterTypes[i];
                var filterButton = new ChoiceListButton(filterType.getName(), filterType.getDescription());
                filterButton.minWidthProperty().bind(this.filterAdderScrollPane.widthProperty().subtract(16));
                filterButton.setOnAction(_ -> {
                    filterType.addOrConfigure();
                    this.close();
                });
                ElementOrder.applyPseudoclass(filterButton, i, filteredFilterTypes.length);

                filterAdderListChildren.add(filterButton);
            }
        });
    }

    private FilterType[] getFilteredFilterTypes(String filterQuery) {
        if (filterQuery == null || filterQuery.isEmpty()) {
            return FilterType.values();
        }

        return Arrays.stream(FilterType.values())
                .filter(type -> type.name().toLowerCase().contains(filterQuery))
                .toArray(FilterType[]::new);
    }

    @Override
    protected Parent createRoot() {
        var container = new VBox(16);
        container.setMinWidth(384);

        var filterQueryInput = new StringTextField<>(this.formContext)
                .bindWidth(container.widthProperty())
                .setLabel("Filter Name")
                .setPlaceholder("Blur...")
                .setFormValueSetter((curr, v) -> curr.filterQuery = v == null ? null : v.trim().toLowerCase());

        this.filterAdderScrollPane.prefWidthProperty().bind(container.widthProperty());
        this.filterAdderScrollPane.setPrefHeight(192);
        this.filterAdderScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.filterAdderScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.filterAdderScrollPane.setContent(new Group(this.filterAdderList));

        container.getChildren().addAll(filterQueryInput, this.filterAdderScrollPane);

        return container;
    }
}
