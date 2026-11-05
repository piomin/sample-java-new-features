package pl.piomin.java.samples.moduleimports;

import module java.base;
import module java.desktop;

// Both java.base (java.util.List) and java.desktop (java.awt.List) export a type named List,
// so the simple name would be ambiguous. A single-type import shadows on-demand module imports.
import java.util.List;

/**
 * Ambiguity resolution for module imports.
 */
public class AmbiguousImportExample {

    public List<String> names() {
        return List.of("a", "bb", "ccc");
    }

    public java.awt.List awtList() {
        return new java.awt.List();
    }

    // Color comes only from java.desktop (java.awt.Color), so no ambiguity here
    public Color red() {
        return Color.RED;
    }
}
