package pl.piomin.java.samples.unmodifiablecollections;

import pl.piomin.java.samples.privatemethods.ExampleInterface;

import java.util.*;

public class CollectionsCreator {

    private List<String> fruits = List.of("apple", "banana", "orange");
    private Map<Integer, String> numbers = Map.of(1, "one", 2,"two", 3, "three");


    public List<String> fruits() {
        List<String> fruitsTmp = new ArrayList<>();
        fruitsTmp.add("apple");
        fruitsTmp.add("banana");
        fruitsTmp.add("orange");
        return Collections.unmodifiableList(fruitsTmp);
    }

    public List<String> fruitsFromArray() {
        String[] fruitsArray = {"apple", "banana", "orange"};
        return Arrays.asList(fruitsArray);
    }

    public Map<Integer, String> numbers() {
        Map<Integer, String> numbersTmp = new HashMap<>();
        numbersTmp.put(1, "one");
        numbersTmp.put(2, "two");
        numbersTmp.put(3, "three");
        return Collections.unmodifiableMap(numbersTmp);
    }
}
