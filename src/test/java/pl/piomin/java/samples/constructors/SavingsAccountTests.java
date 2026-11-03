package pl.piomin.java.samples.constructors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingsAccountTests {

    private static final String OWNER = "John Smith";
    private static final int DEPOSIT = 1000;

    @Test
    void shouldCreateAccount() {
        SavingsAccount account = new SavingsAccount(OWNER, DEPOSIT);
        Assertions.assertEquals(OWNER, account.getOwner());
        Assertions.assertEquals(DEPOSIT, account.getBalance());
    }

    @Test
    void shouldNotCreateAccountWithoutOwner() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(" ", DEPOSIT));
    }

    @Test
    void shouldNotCreateOldAccountWithNegativeDeposit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingsAccountOld(OWNER, -1));
    }

}
