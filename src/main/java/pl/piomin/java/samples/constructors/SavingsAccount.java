package pl.piomin.java.samples.constructors;

import java.math.BigDecimal;

/**
 * Flexible constructor bodies (JEP 513), final in Java 25.
 * <p>
 * What is new compared to Java 24 and earlier: a constructor body is split into a <em>prologue</em> (statements before
 * {@code super(...)} / {@code this(...)}) and an <em>epilogue</em> (everything after). The prologue may contain
 * ordinary statements: validation, local variables, {@code if}, {@code try}, so the arguments for the superclass can be
 * checked and computed in a straightforward way instead of through static helper methods
 * (compare {@link OldSavingsAccount}).
 * <p>
 * Restrictions that still apply in the prologue: it must not reference {@code this} (no instance methods, no reading
 * fields of the instance under construction) and must not call {@code super} methods. It may, however, assign fields
 * declared in this class, so they are initialized before the superclass constructor runs. This is also the fix for
 * the classic problem of a superclass constructor calling an overridable method that sees an uninitialized field.
 */
public class SavingsAccount extends Account {

    private final BigDecimal interestRate;

    public SavingsAccount(String owner, BigDecimal deposit, BigDecimal interestRate) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (deposit.signum() <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        var bonus = deposit.multiply(interestRate);
        this.interestRate = interestRate;
        super(owner, deposit.add(bonus));
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }
}
