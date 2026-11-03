package pl.piomin.java.samples.constructors;

import java.math.BigDecimal;

public class Account {

    private final String owner;
    private final BigDecimal balance;

    public Account(String owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
