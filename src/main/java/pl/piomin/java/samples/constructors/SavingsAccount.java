package pl.piomin.java.samples.constructors;

/**
 * Flexible constructor bodies (JEP 513), final in Java 25.
 * Statements are now allowed before {@code super(...)}, so arguments can be validated
 * directly in the constructor. The code before {@code super(...)} cannot use {@code this}.
 */
public class SavingsAccount extends Account {

    public SavingsAccount(String owner, int deposit) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (deposit <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        super(owner, deposit);
    }
}
