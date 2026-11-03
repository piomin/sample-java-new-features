package pl.piomin.java.samples.constructors;

/**
 * Before Java 25 {@code super(...)} had to be the first statement of a constructor,
 * so validation had to be done in static helper methods called inside its arguments.
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
