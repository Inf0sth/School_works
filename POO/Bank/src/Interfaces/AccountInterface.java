

public interface AccountInterface{
  public void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException;
  public String getAccountNumber();
  public boolean getEmail(String email) throws AccountException;
  public boolean setEmail(String email) throws AccountException;
  public boolean getPhoneNumber(String phoneNumber) throws AccountException;
  public boolean setPhoneNumber(String phoneNumber) throws AccountException;
  public boolean getName(String name) throws AccountException;
  public boolean setName(String name) throws AccountException;
  public double getBalance();
  public double withdraw(double amount) throws AccountException;
  public double transfer(double amount, String concept) throws AccountException;
  public double String consultTransaction(String Id) throws AccountException;
}

