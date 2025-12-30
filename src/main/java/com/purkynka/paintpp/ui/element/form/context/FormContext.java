package com.purkynka.paintpp.ui.element.form.context;

import com.purkynka.paintpp.logic.observable.ObservableHashSet;
import com.purkynka.paintpp.logic.observable.ObservableValue;

public class FormContext<V> {
    private final ObservableValue<V> observableFormValue;
    private final ObservableHashSet<String> observableFormErrors;

    public FormContext(V emptyFormValue) {
        this.observableFormValue = new ObservableValue<>(emptyFormValue);
        this.observableFormErrors = new ObservableHashSet<>();
    }

    public ObservableValue<V> getObservableFormValue() {
        return this.observableFormValue;
    }

    public ObservableHashSet<String> getObservableFormErrors() {
        return this.observableFormErrors;
    }
}
