package pl.piomin.java.samples.records;

public class RecordPatterns {

    // simple record pattern with instanceof
    public static String describePerson(Object o) {
        if (o instanceof Person(var name, var age)) {
            return name + " (" + age + ")";
        }
        return "unknown";
    }

    // nested record pattern with instanceof, unnamed pattern (_) ignores the street
    public static String cityOf(Object o) {
        if (o instanceof PersonWithAddress(var name, Address(var city, _))) {
            return name + " lives in " + city;
        }
        return "unknown";
    }

    // nested deconstruction in a switch with a guard
    public static String describe(Object o) {
        return switch (o) {
            case PersonWithAddress(var name, Address(var city, _)) when city.equals("Warsaw") ->
                    name + " lives in the capital";
            case PersonWithAddress(var name, Address(var city, var street)) ->
                    name + " lives in " + city + ", " + street;
            case Person(var name, var age) when age >= 18 -> name + " is an adult";
            case Person(var name, _) -> name + " is a minor";
            case null -> "null";
            default -> "unknown";
        };
    }

}
