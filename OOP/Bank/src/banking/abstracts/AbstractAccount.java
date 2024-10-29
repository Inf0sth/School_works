package banking.abstracts;

import banking.interfaces.AccountInterface;
import banking.exceptions.AccountException;
import banking.transactions.Transaction;
import banking.transactions.TransactionEnum;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

public abstract class AbstractAccount implements AccountInterface {
    private double accountLimit;
    private double openFee;
    private double maintenanceFee;
    private HashMap<String, Transaction> movements;
    private String accountNumber;
    private String email;
    private String phoneNumber;
    private String name;
    private double balance;

    public AbstractAccount() {
        this.movements = new HashMap<>();
    }

    @Override
    public boolean setEmail(String email) throws AccountException {
        if (Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            this.email = email;
            return true;
        } else {
            throw new AccountException("Invalid email format");
        }
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean setPhoneNumber(String phoneNumber) throws AccountException {
        if (Pattern.matches("^\\+?[0-9]{10,13}$", phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return true;
        } else {
            throw new AccountException("Invalid phone number format");
        }
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public boolean setName(String name) throws AccountException {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            return true;
        } else {
            throw new AccountException("Name cannot be empty");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public double consultTransaction(String id) throws AccountException {
        if (movements.containsKey(id)) {
            return movements.get(id).getAmount();
        } else {
            throw new AccountException("Transaction ID not found");
        }
    }

    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected void addMovement(Transaction transaction) {
        movements.put(transaction.getId(), transaction);
    }

    protected void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    protected void setOpenFee(double openFee) {
        this.openFee = openFee;
    }

    protected void setMaintenanceFee(double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    protected String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 18);
    }

    @Override
    public double withdraw(double amount) throws AccountException {
        if (amount > balance) {
            throw new AccountException("Insufficient balance");
        }
        setBalance(balance - amount);
        addMovement(new Transaction(TransactionEnum.WITHDRAWAL, "Withdrawal", amount));
        return balance;
    }

    @Override
    public double transfer(double amount, String concept) throws AccountException {
        if (amount > balance) {
            throw new AccountException("Insufficient balance");
        }
        setBalance(balance - amount);
        addMovement(new Transaction(TransactionEnum.CHARGES, concept, amount));
        return balance;
    }
}
