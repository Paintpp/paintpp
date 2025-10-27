package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Represents an event that sends a single value of type {@link T}.
 * @param <T> The type of the value sent in the event
 */
public class ConsumerEventHandler<T> {
    private final ArrayList<Consumer<T>> listeners = new ArrayList<>();

    /**
     * Adds an event listener to this event.
     * @param consumer The callback {@link Consumer} to run when this event is fired
     */
    public void addEventListener(Consumer<T> consumer) {
        listeners.add(consumer);
    }

    /**
     * Fires all registered event listeners with the provided value.
     * @param value The value to send to event listeners
     */
    public void send(T value) {
        for (Consumer<T> listener : listeners) {
            listener.accept(value);
        }
    }
}
