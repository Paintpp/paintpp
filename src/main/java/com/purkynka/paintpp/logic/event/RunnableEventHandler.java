package com.purkynka.paintpp.logic.event;

import java.util.ArrayList;

public class RunnableEventHandler {
    private ArrayList<Runnable> listeners = new ArrayList<>();

    public void addEventListener(Runnable handler) {
        listeners.add(handler);
    }

    public void send() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
}
