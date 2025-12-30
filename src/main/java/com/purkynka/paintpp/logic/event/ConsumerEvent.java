package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ConsumerEvent<T> {
    private final ArrayList<Consumer<T>> listeners;

    public ConsumerEvent() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(Consumer<T> listener) {
        listeners.add(listener);
    }

    public void invoke(T value) {
        for (Consumer<T> listener : listeners) {
            listener.accept(value);
        }
    }
}
