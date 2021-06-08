package pl.piomin.java.samples.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.piomin.java.samples.helper.PersonEntity;
import pl.piomin.java.samples.records.Person;

public class MapperTests {

    @Test
    void mappingPerson() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFullName("Piotr Minkowski");
        personEntity.setAge(10);

        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person person = mapper.mapToDto(personEntity);
        Assertions.assertNotNull(person);
        Assertions.assertEquals(10, person.age());
    }
}
