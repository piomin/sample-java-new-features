package pl.piomin.java.samples.moduleimports;

import module java.base;

/**
 * Module import declarations (JEP 511, final in Java 25): a single {@code import module java.base;}
 * replaces separate imports of {@code java.util.*}, {@code java.util.stream.*}, {@code java.util.function.*}, etc.
 */
public class ModuleImportExample {

    public List<String> toUpperCase(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<String>> groupByLength(List<String> names) {
        return names.stream().collect(Collectors.groupingBy(String::length));
    }

    public Optional<String> findLongest(List<String> names) {
        return names.stream().max(Comparator.comparingInt(String::length));
    }
}
