package banking.interfaces;

import banking.exceptions.AccountException;

public interface AccountInterface {
    void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountException;
    String getAccountNumber();
    boolean setEmail(String email) throws AccountException;
    String getEmail();
    boolean setPhoneNumber(String phoneNumber) throws AccountException;
    String getPhoneNumber();
    boolean setName(String name) throws AccountException;
    String getName();
    double getBalance();
    double withdraw(double amount) throws AccountException;
    double transfer(double amount, String concept) throws AccountException;
    double consultTransaction(String id) throws AccountException;
}
