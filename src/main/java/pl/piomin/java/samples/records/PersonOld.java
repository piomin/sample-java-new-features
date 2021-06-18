package pl.piomin.java.samples.records;

import java.util.Objects;

public class PersonOld {

    private final String name;
    private int age;

    public PersonOld(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        System.out.println("Age: " + this.age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOld personOld = (PersonOld) o;
        return age == personOld.age && name.equals(personOld.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "PersonOld{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
