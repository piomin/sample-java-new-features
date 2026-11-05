// Multi-file source-code program (JEP 458, Java 22): Main.java uses Helper.java from the same directory.
// Run with: java src/main/source-launcher/multifile/Main.java
public class Main {
    public static void main(String[] args) {
        IO.println(Helper.message());
    }
}
