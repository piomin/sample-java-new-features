package pl.piomin.java.samples.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.piomin.java.samples.helper.PersonEntity;
import pl.piomin.java.samples.records.Person;

@Mapper
public interface PersonMapper {

    @Mapping(source = "fullName", target = "name")
    Person mapToDto(PersonEntity personEntity);
}
