package pl.piomin.java.samples.constructors;

/**
 * Before Java 25 validation had to be done in static methods called inside {@code super(...)}.
 */
public class SavingsAccountOld extends Account {

    public SavingsAccountOld(String owner, int deposit) {
        super(requireOwner(owner), requirePositive(deposit));
    }

    private static String requireOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required");
        }
        return owner;
    }

    private static int requirePositive(int deposit) {
        if (deposit <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        return deposit;
    }
}
