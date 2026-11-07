// Compact source file (JEP 512, final in Java 25): no class declaration, instance main method.
// Run with: java src/main/source-launcher/Hello.java
String greeting() {
    return "Hello from a compact source file!";
}

void main() {
    IO.println(greeting());
}
