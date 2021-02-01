package pl.piomin.java.samples.privatemethods;

public interface ExampleInterface {

    private void printMsg(String methodName) {
        System.out.println("Calling interface");
        System.out.println("Interface method: " + methodName);
    }

    default void method1() {
        printMsg("method1");
    }

    default void method2() {
        printMsg("method2");
    }
}
