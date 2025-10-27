package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;

/**
 * Represents an event that doesn't send any value.
 */
public class RunnableEventHandler {
    private final ArrayList<Runnable> listeners = new ArrayList<>();

    /**
     * Adds an event listener to this event.
     * @param handler The callback {@link Runnable} to run when this event is fired
     */
    public void addEventListener(Runnable handler) {
        listeners.add(handler);
    }

    /**
     * Fires all registered event listeners.
     */
    public void send() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
}
