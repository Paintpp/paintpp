package com.purkynka.paintpp.logic.observable;

import com.purkynka.paintpp.logic.event.ConsumerEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class ObservableArrayList<T> extends ArrayList<T> {
    private final ConsumerEvent<ArrayList<T>> changeEvent = new ConsumerEvent<>();

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

    @Override
    public boolean add(T t) {
        var result = super.add(t);
        this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public boolean remove(Object o) {
        var result = super.remove(o);
        this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public T remove(int index) {
        var result = super.remove(index);
        this.changeEvent.invoke(this);

        return result;
    }

    @Override
    public void clear() {
        super.clear();
        this.changeEvent.invoke(this);
    }
}
