package banking.implementations;

import banking.abstracts.AbstractAccount;
import banking.exceptions.AccountException;

public class SavingsAccount extends AbstractAccount {
    private static final double INTEREST_RATE = 0.15;
    private int withdrawalsCount = 0;
    private static final int MAX_WITHDRAWALS = 2;

    public SavingsAccount() {
        super();
    }

    @Override
    public void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountException {
        if (initialBalance < 0) {
            throw new AccountException("Initial balance cannot be negative");
        }
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAccountNumber(generateAccountNumber());
        setBalance(initialBalance);
        setAccountLimit(5000.0);
        setOpenFee(0.0);
        setMaintenanceFee(0.0);
    }

    @Override
    public double withdraw(double amount) throws AccountException {
        if (withdrawalsCount >= MAX_WITHDRAWALS) {
            throw new AccountException("Maximum withdrawals limit reached");
        }
        withdrawalsCount++;
        return super.withdraw(amount);
    }

    @Override
    public double transfer(double amount, String concept) throws AccountException {
        throw new AccountException("Transfers are not allowed for savings account");
    }

    public double estimateInterest(int days) {
        return getBalance() * Math.pow((1 + INTEREST_RATE / 365), days) - getBalance();
    }

    @Override
    public String toString() {
        return "SavingsAccount [accountNumber=" + getAccountNumber() + ", name=" + getName() + ", email=" + getEmail() +
                ", phoneNumber=" + getPhoneNumber() + ", balance=" + getBalance() + "]";
    }
}
