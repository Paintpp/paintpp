package com.purkynka.paintpp.ui.element.form.context;

@FunctionalInterface
public interface FormValueSetter<C, V> {
    void setValue(C currentFormValue, V value);
}
