package com.purkynka.paintpp.ui.element;

import javafx.css.PseudoClass;
import javafx.scene.Node;

public class ElementOrder {
    private static final PseudoClass FIRST = PseudoClass.getPseudoClass("first");
    private static final PseudoClass LAST = PseudoClass.getPseudoClass("last");
    private static final PseudoClass ONLY = PseudoClass.getPseudoClass("only");

    public static void applyPseudoclass(Node element, int index, int length) {
        var first = index == 0;
        var last = index == length - 1;

        if (first && last) element.pseudoClassStateChanged(ElementOrder.ONLY, true);
        else if (first) element.pseudoClassStateChanged(ElementOrder.FIRST, true);
        else if (last) element.pseudoClassStateChanged(ElementOrder.LAST, true);
    }
}
