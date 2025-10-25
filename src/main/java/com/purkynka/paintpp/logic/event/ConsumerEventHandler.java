package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ConsumerEventHandler<T> {
    private ArrayList<Consumer<T>> listeners = new ArrayList<>();

    public void addEventListener(Consumer<T> consumer) {
        listeners.add(consumer);
    }

    public void send(T value) {
        for (Consumer<T> listener : listeners) {
            listener.accept(value);
        }
    }
}
