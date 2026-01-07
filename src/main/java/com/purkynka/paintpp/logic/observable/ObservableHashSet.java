package com.purkynka.paintpp.logic.observable;

import com.purkynka.paintpp.logic.event.ConsumerEvent;

import java.util.HashSet;
import java.util.function.Consumer;

public class ObservableHashSet<V> extends HashSet<V> {
    private final ConsumerEvent<HashSet<V>> changeEvent = new ConsumerEvent<>();

    @Override
    public boolean add(V v) {
        var changed = super.add(v);
        if (changed) changeEvent.invoke(this);

        return changed;
    }

    @Override
    public boolean remove(Object o) {
        var changed = super.remove(o);
        if (changed) changeEvent.invoke(this);

        return changed;
    }

    public void addListener(Consumer<HashSet<V>> listener) {
        changeEvent.addEventListener(listener);
    }
}
