package pl.piomin.java.samples.unmodifiablecollections;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.java.samples.helper.Employee;
import pl.piomin.java.samples.helper.Organization;
import pl.piomin.java.samples.records.PersonOld;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EclipseCollectionsTest {

    @Test
    void collectFlatEclipseColl() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Developer");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Developer");
        Organization o1 = new Organization("Test1", List.of(e1, e2));
        Organization o2 = new Organization("Test2", List.of(e3, e4));
        MutableList<Organization> organizations = Lists.mutable.of(o1, o2);
        organizations.flatCollect(Organization::employees);
        MutableList<Employee> employees = organizations
            .select(o -> o.name().equals("Test1"))
            .flatCollect(Organization::employees);
        Assertions.assertEquals(2, employees.size());
        MutableList<List<Employee>> employees2 = organizations
            .collectIf(o -> o.name().equals("Test1"), Organization::employees);
        Assertions.assertEquals(2, employees2.get(0).size());
    }

    @Test
    void collectFlatJava8Streams() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Developer");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Developer");
        Organization o1 = new Organization("Test1", List.of(e1, e2));
        Organization o2 = new Organization("Test2", List.of(e3, e4));
        List<Organization> organizations = List.of(o1, o2);
        List<Employee> employees = organizations
            .stream()
            .filter(o -> o.name().equals("Test1"))
            .map(Organization::employees)
            .flatMap(List::stream)
            .collect(Collectors.toList());
        Assertions.assertEquals(2, employees.size());

        List<List<Employee>> employees2 = organizations
            .stream()
            .filter(o -> o.name().equals("Test1"))
            .map(Organization::employees)
            .collect(Collectors.toList());
        Assertions.assertEquals(2, employees2.get(0).size());
    }

    @Test
    void countEclipseColl() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Architect");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Tester");
        MutableList<Employee> employees = Lists.mutable.of(e1, e2, e3, e4);
        int c = employees.count(emp -> emp.position().equals("Developer"));
        Assertions.assertEquals(2, c);

        Employee r1 = employees.minBy(Employee::id);
        Assertions.assertNotNull(r1);
        Assertions.assertEquals(1, r1.id());
    }

    @Test
    void countJava8Streams() {
        Employee e1 = new Employee(1, "Test1", "Developer");
        Employee e2 = new Employee(2, "Test2", "Architect");
        Employee e3 = new Employee(3, "Test3", "Developer");
        Employee e4 = new Employee(4, "Test4", "Tester");
        List<Employee> employees = List.of(e1, e2, e3, e4);
        long c = employees
            .stream()
            .filter(emp -> emp.position().equals("Developer"))
            .count();
        Assertions.assertEquals(2L, c);

        Employee r1 = employees
            .stream()
            .min(Comparator.comparing(Employee::id))
            .orElseThrow(NoSuchElementException::new);
        Assertions.assertEquals(1, r1.id());
    }

    @Test
    void lazyEclipseColl() {
        PersonOld p1 = new PersonOld("Test1", 20);
        PersonOld p2 = new PersonOld("Test2", 18);
        PersonOld p3 = new PersonOld("Test3", 24);
        PersonOld p4 = new PersonOld("Test4", 30);
        PersonOld p5 = new PersonOld("Test5", 35);
        MutableList<PersonOld> persons = Lists.mutable.with(p1, p2, p3, p4, p5);
        persons
            .asLazy()
            .tap(p -> p.setAge(p.getAge() + 2))
            .select(p -> p.getAge() > 20)
            .getFirst();

        System.out.println("---");

        persons
            .distinct()
            .tap(p -> p.setAge(p.getAge() + 2))
            .select(p -> p.getAge() > 20)
            .getFirst();

        System.out.println("---");

        List<PersonOld> ps = List.of(p1, p2, p3, p4, p5);
        ps.stream()
            .filter(p -> p.getAge() > 20)
            .findFirst()
            .orElseThrow();
    }
}
