package pl.piomin.java.samples.constructors;

/**
 * Flexible constructor bodies (JEP 513, Java 25): statements are allowed before {@code super(...)}, but cannot use {@code this}.
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
