package pl.piomin.java.samples.helper;

import java.util.List;

public record Organization(String name, List<Employee> employees) {
}
