package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.observable.ObservableArrayList;
import com.purkynka.paintpp.logic.observable.ObservableValue;
import com.purkynka.paintpp.ui.element.filterloadingscreen.FilterLoadingScreen;
import com.purkynka.paintpp.ui.element.sidebar.filterlist.FilterApplyDuration;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class FilterManager {
    public static ObservableArrayList<ImageFilter> FILTERS = new ObservableArrayList<>();
    public static ObservableValue<BufferBackedImage> FILTERED_IMAGE = new ObservableValue<>();
    public static ObservableValue<FilterApplyTime> LAST_FILTER_APPLY_TIME = new ObservableValue<>(new FilterApplyTime(Duration.ZERO, Duration.ZERO));

    static {
        ImageManager.IMAGE_PROVIDER.addUpdateListener(
                imageProvider -> {
                    FilterManager.FILTERS.clear();
                    applyFilters(imageProvider.getBufferBackedImage(), false);
                }
        );

        FilterManager.FILTERS.addListener(
            _ -> {
                var newImage = new BufferBackedImage(ImageManager.IMAGE_PROVIDER.get().getBufferBackedImage());

                var task = new Task<FilterApplyTime>() {
                    @Override
                    protected FilterApplyTime call() {
                        return FilterManager.applyFilters(newImage, true);
                    }
                };

                task.setOnScheduled(_ -> FilterLoadingScreen.FILTER_LOADING_SCREEN.show());
                task.setOnSucceeded(_ -> {
                    FilterManager.FILTERED_IMAGE.set(newImage);
                    FilterLoadingScreen.FILTER_LOADING_SCREEN.hide();
                    LAST_FILTER_APPLY_TIME.set(task.getValue());
                });

                var thread = new Thread(task);
                thread.setDaemon(true);
                thread.start();
            }
        );
    }

    private static FilterApplyTime applyFilters(BufferBackedImage originalImage, boolean updateLoadingScreen) {
        var rawTotal = 0L;
        var actualTotal = 0L;

        var totalFilters = FilterManager.FILTERS.size();
        for (int i = 0; i < totalFilters; i++) {
            ImageFilter filter = FilterManager.FILTERS.get(i);

            if (updateLoadingScreen) {
                int finalI = i;
                Platform.runLater(() -> FilterLoadingScreen.FILTER_LOADING_SCREEN.updateFilterProgress(finalI, totalFilters, filter.getFilterType().getName()));
            }

            var usedCache = filter.copyCacheOrModifyPixelBuffer(originalImage);

            rawTotal += filter.getCalculationTime();
            actualTotal += usedCache ? filter.getCopyTime() : filter.getCalculationTime();
        }

        return new FilterApplyTime(Duration.ofNanos(rawTotal), Duration.ofNanos(actualTotal));
    }
}
