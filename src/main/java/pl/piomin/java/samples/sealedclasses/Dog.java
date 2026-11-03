package pl.piomin.java.samples.sealedclasses;

public non-sealed class Dog extends Pet {

    private final int age;

    public Dog(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
