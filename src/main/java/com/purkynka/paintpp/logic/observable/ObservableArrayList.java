package com.purkynka.paintpp.logic.observable;

import com.purkynka.paintpp.logic.event.ConsumerEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class ObservableArrayList<T> extends ArrayList<T> {
    private final ConsumerEvent<ArrayList<T>> changeEvent = new ConsumerEvent<>();
    private boolean suppressingUpdates;

    public ObservableArrayList() {
        super();
    }

    public void addListener(Consumer<ArrayList<T>> listener) {
        this.changeEvent.addEventListener(listener);
    }

    public void swap(int first, int second) {
        Collections.swap(this, first, second);
        this.changeEvent.invoke(this);
    }

    public void suppressUpdates() {
        this.suppressingUpdates = true;
    }

    public void unsuppressUpdates() {
        this.suppressingUpdates = false;
    }

    public void manualUpdate() {
        this.changeEvent.invoke(this);
    }

    @Override
    public boolean add(T t) {
        var result = super.add(t);
        if (!this.suppressingUpdates) this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public boolean remove(Object o) {
        var result = super.remove(o);
        if (!this.suppressingUpdates) this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public T remove(int index) {
        var result = super.remove(index);
        if (!this.suppressingUpdates) this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public void clear() {
        super.clear();
        if (!this.suppressingUpdates) this.changeEvent.invoke(this);
    }
}
