package com.purkynka.paintpp.logic.observable;

import com.purkynka.paintpp.logic.event.ConsumerEvent;

import java.util.function.Consumer;

public class ObservableValue<T> {
    private final ConsumerEvent<T> updateEvent;
    private T currentValue;

    public ObservableValue(T initialValue) {
        this.updateEvent = new ConsumerEvent<>();
        this.currentValue = initialValue;
    }

    public ObservableValue() {
        this(null);
    }

    public void set(T newValue) {
        this.currentValue = newValue;
        updateEvent.invoke(this.currentValue);
    }

    public T get() {
        return this.currentValue;
    }

    public void addUpdateListener(Consumer<T> listener) {
        this.updateEvent.addEventListener(listener);
    }
}
