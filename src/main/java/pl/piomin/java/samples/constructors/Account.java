package pl.piomin.java.samples.constructors;

public class Account {

    private final String owner;
    private final int balance;

    public Account(String owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }
}
