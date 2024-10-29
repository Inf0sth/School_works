package banking.implementations;

import banking.abstracts.AbstractAccount;
import banking.exceptions.AccountException;
import banking.transactions.Transaction;
import banking.transactions.TransactionEnum;

public class PersonalAccount extends AbstractAccount {
    private static final double MAX_WITHDRAWAL_LIMIT = 500.0;
    private static final double MIN_BALANCE = 1000.0;
    private static final double OPEN_FEE = 2.0;
    private static final double MAINTENANCE_FEE = 10.0;

    public PersonalAccount() {
        super();
        setOpenFee(OPEN_FEE);
        setMaintenanceFee(MAINTENANCE_FEE);
    }

    @Override
    public void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountException {
        if (initialBalance < MIN_BALANCE) {
            throw new AccountException("Initial balance must be at least $" + MIN_BALANCE);
        }
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAccountNumber(generateAccountNumber());
        setBalance(initialBalance - OPEN_FEE - MAINTENANCE_FEE);
    }

    @Override
    public double withdraw(double amount) throws AccountException {
        if (amount > MAX_WITHDRAWAL_LIMIT) {
            throw new AccountException("Withdrawal limit exceeded. Maximum allowed is $" + MAX_WITHDRAWAL_LIMIT);
        }
        if (getBalance() - amount < MIN_BALANCE) {
            throw new AccountException("Insufficient balance. Minimum balance must be $" + MIN_BALANCE);
        }
        Transaction transaction = new Transaction(TransactionEnum.WITHDRAWAL, "Withdrawal", amount);
        addTransaction(transaction);
        return super.withdraw(amount);
    }

    @Override
    public double transfer(double amount, String concept) throws AccountException {
        if (amount > MAX_WITHDRAWAL_LIMIT) {
            throw new AccountException("Transfer limit exceeded. Maximum allowed is $" + MAX_WITHDRAWAL_LIMIT);
        }
        Transaction transaction = new Transaction(TransactionEnum.CHARGES, concept, amount);
        addTransaction(transaction);
        return super.transfer(amount, concept);
    }

    private void addTransaction(Transaction transaction) {
        // TODO: Implementar el almacenamiento de la transacción
    }

    @Override
    public String toString() {
        return "PersonalAccount [accountNumber=" + getAccountNumber() + ", name=" + getName() + ", email=" + getEmail() +
                ", phoneNumber=" + getPhoneNumber() + ", balance=" + getBalance() + "]";
    }
}
