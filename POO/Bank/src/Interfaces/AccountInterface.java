package Account;

public interface AccountInterface {
    void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException;
    String getAccountNumber();
    boolean setEmail(String email) throws AccountException;
    String getEmail() throws AccountException;
    boolean setPhoneNumber(String phoneNumber) throws AccountException;
    String getPhoneNumber() throws AccountException;
    boolean setName(String name) throws AccountException;
    String getName() throws AccountException;
    double getBalance();
    double withdraw(double amount) throws AccountException;
    double transfer(double amount, String concept) throws AccountException;
    String consultTransaction(String id) throws AccountException;
}
