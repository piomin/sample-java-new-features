package pl.piomin.java.samples.unmodifiablecollections;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.java.samples.helper.Employee;
import pl.piomin.java.samples.helper.Organization;
import pl.piomin.java.samples.records.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsCreatorTest {

    @Test
    void listFormArray() {
        List<String> list = Arrays.asList("A", "B", "C");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add("D"));
    }

    @Test
    void unmodifiableList() {
        List<String> list = List.of("A", "B", "C");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add("D"));
    }

    @Test
    void unmodifiableList2() {
        ImmutableList<String> list = Lists.immutable.of("A", "B", "C");
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void select() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Developer");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Developer");
        Organization o1 = new Organization("Test1", List.of(e1, e2));
        Organization o2 = new Organization("Test2", List.of(e3, e4));
        MutableList<Organization> organizations = Lists.mutable.of(o1, o2);
        MutableList<List<Employee>> employees = organizations
                .select(o -> o.name().equals("Test1"))
                .collect(Organization::employees);
        Assertions.assertEquals(2, employees.getFirst().size());
    }

    @Test
    void selectStreams() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Developer");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Developer");
        Organization o1 = new Organization("Test1", List.of(e1, e2));
        Organization o2 = new Organization("Test2", List.of(e3, e4));
        List<Organization> organizations = List.of(o1, o2);
        List<List<Employee>> employees = organizations
                .stream()
                .filter(o -> o.name().equals("Test1"))
                .map(Organization::employees)
                .collect(Collectors.toList());
        Assertions.assertEquals(2, employees.get(0).size());
    }
}
