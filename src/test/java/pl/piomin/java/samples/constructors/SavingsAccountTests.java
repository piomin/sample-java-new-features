package pl.piomin.java.samples.constructors;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingsAccountTests {

    private static final BigDecimal DEPOSIT = new BigDecimal("1000");
    private static final BigDecimal RATE = new BigDecimal("0.05");

    @Test
    void shouldAddBonusToBalance() {
        var account = new SavingsAccount("John Smith", DEPOSIT, RATE);
        assertEquals("John Smith", account.getOwner());
        assertEquals(0, new BigDecimal("1050.00").compareTo(account.getBalance()));
        assertEquals(RATE, account.getInterestRate());
    }

    @Test
    void shouldRejectBlankOwner() {
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(" ", DEPOSIT, RATE));
    }

    @Test
    void shouldRejectNonPositiveDeposit() {
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount("John Smith", BigDecimal.ZERO, RATE));
    }

    @Test
    void shouldBehaveLikeOldImplementation() {
        var oldAccount = new OldSavingsAccount("John Smith", DEPOSIT, RATE);
        var newAccount = new SavingsAccount("John Smith", DEPOSIT, RATE);
        assertEquals(0, oldAccount.getBalance().compareTo(newAccount.getBalance()));
    }

    @Test
    void shouldRejectBlankOwnerInOldImplementation() {
        assertThrows(IllegalArgumentException.class, () -> new OldSavingsAccount(null, DEPOSIT, RATE));
    }
}
