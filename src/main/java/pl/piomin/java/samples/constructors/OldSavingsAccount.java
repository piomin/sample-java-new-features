package pl.piomin.java.samples.constructors;

import java.math.BigDecimal;

/**
 * Before Java 25 a constructor had to call {@code super(...)} or {@code this(...)} as its very first statement.
 * <p>
 * Anything that had to happen before that call (argument validation, preparing the values passed to the superclass)
 * needed a workaround: static helper methods invoked inline in the {@code super(...)} arguments. It works,
 * but the validation is scattered across helpers and a value needed twice has to be computed twice.
 */
public class OldSavingsAccount extends Account {

    private final BigDecimal interestRate;

    public OldSavingsAccount(String owner, BigDecimal deposit, BigDecimal interestRate) {
        super(requireOwner(owner), withBonus(requirePositive(deposit), interestRate));
        this.interestRate = interestRate;
    }

    private static String requireOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required");
        }
        return owner;
    }

    private static BigDecimal requirePositive(BigDecimal deposit) {
        if (deposit.signum() <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        return deposit;
    }

    private static BigDecimal withBonus(BigDecimal deposit, BigDecimal interestRate) {
        return deposit.add(deposit.multiply(interestRate));
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }
}
