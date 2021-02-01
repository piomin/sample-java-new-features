package pl.piomin.java.samples.optional;

import pl.piomin.java.samples.records.Person;

import java.util.Optional;

public class PersonRepository {

    public Optional<Person> findById(Long id) {
        return Optional.of(new Person("TestName", 30));
    }
}
