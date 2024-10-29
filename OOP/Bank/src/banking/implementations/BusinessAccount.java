package banking.implementations;

import banking.abstracts.AbstractAccount;
import banking.exceptions.AccountException;
import banking.transactions.BusinessCategoryEnum;
import banking.transactions.Transaction;
import banking.transactions.TransactionEnum;

public class BusinessAccount extends AbstractAccount {
    private static final double MIN_BALANCE = 5000.0;

    public BusinessAccount() {
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
        setBalance(initialBalance - 100); // Deduct open fee
        setAccountLimit(100000.0);
        setOpenFee(100.0);
        setMaintenanceFee(50.0);
    }

    @Override
    public double withdraw(double amount) throws AccountException {
        if (getBalance() - amount < MIN_BALANCE) {
            throw new AccountException("Balance cannot go below the minimum required balance of $5000");
        }
        return super.withdraw(amount);
    }

    @Override
    public double transfer(double amount, String concept) throws AccountException {
        if (!isValidBusinessConcept(concept)) {
            throw new AccountException("Invalid transaction concept for business account");
        }
        return super.transfer(amount, concept);
    }

    private boolean isValidBusinessConcept(String concept) {
        try {
            BusinessCategoryEnum.valueOf(concept.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "BusinessAccount [accountNumber=" + getAccountNumber() + ", name=" + getName() + ", email=" + getEmail() +
                ", phoneNumber=" + getPhoneNumber() + ", balance=" + getBalance() + "]";
    }
}
