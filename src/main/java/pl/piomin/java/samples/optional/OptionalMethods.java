package pl.piomin.java.samples.optional;

import pl.piomin.java.samples.records.Person;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalMethods {

    private PersonRepository repository = new PersonRepository();

    public Person getPersonById(Long id) {
        Optional<Person> personOpt = repository.findById(id);
        return personOpt.orElseThrow();
    }

    public Person getPersonByIdOldWay(Long id) {
        Optional<Person> personOpt = repository.findById(id);
        if (personOpt.isPresent())
            return personOpt.get();
        else
            throw new NoSuchElementException();
    }

    public void printPersonById(Long id) {
        Optional<Person> personOpt = repository.findById(id);
        personOpt.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Person not found")
        );
    }

    public void printPersonByIdOldWay(Long id) {
        Optional<Person> personOpt = repository.findById(id);
        if (personOpt.isPresent())
            System.out.println(personOpt.get());
        else
            System.out.println("Person not found");
    }

}
