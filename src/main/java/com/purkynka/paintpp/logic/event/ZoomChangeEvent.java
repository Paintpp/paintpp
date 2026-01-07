package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;

public class ZoomChangeEvent {
    private final ArrayList<ZoomChangeListener> listeners;

    public ZoomChangeEvent() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(ZoomChangeListener listener) {
        listeners.add(listener);
    }

    public void invoke(double currentZoom, double currentMinZoom, double currentMaxZoom) {
        for (ZoomChangeListener listener : listeners) {
            listener.onChange(currentZoom, currentMinZoom, currentMaxZoom);
        }
    }

    @FunctionalInterface
    public interface ZoomChangeListener {
        void onChange(double currentZoom, double currentMinZoom, double currentMaxZoom);
    }
}
