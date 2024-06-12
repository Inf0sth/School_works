package Account;

import java.util.UUID;

import Transaction;
import Enums.TransactionEnum;
import Classes.Abstract.AbstractAccount;
//Define la clase SavingsAccount extendiendo AbstractAccount
public class SavingsAccount extends AbstractAccount {

 // Constantes para límites y tarifas específicas de SavingsAccount
 private static final int MAX_WITHDRAWALS = 2;
 private static final double MAX_WITHDRAWAL_AMOUNT = 500.0;
 private static final double INTEREST_RATE = 0.15;
 private static final double OPEN_FEE = 0.0;
 private static final double MAINTENANCE_FEE = 0.0;

 // Constructor para inicializar la cuenta sin tarifas de apertura y mantenimiento
 public SavingsAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException {
     createAccount(name, email, phoneNumber, initialBalance);
 }

 @Override
 public void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException {
     setAccountNumber(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18));
     setName(name);
     setEmail(email);
     setPhoneNumber(phoneNumber);
     setAccountLimit(MAX_WITHDRAWAL_AMOUNT);
     setOpenFee(OPEN_FEE);
     setMaintenanceFee(MAINTENANCE_FEE);
     updateBalance(initialBalance);
 }

 @Override
 public double withdraw(double amount) throws AccountException {
     if (getMovements().size() >= MAX_WITHDRAWALS) {
         throw new AccountException("Maximum number of withdrawals reached");
     }
     if (amount > MAX_WITHDRAWAL_AMOUNT) {
         throw new AccountException("Withdrawal amount exceeds the maximum limit of $500");
     }
     updateBalance(-amount);
     Transaction transaction = new Transaction(TransactionEnum.RETIRO, "Retiro", amount);
     addMovement(transaction.getId(), transaction);
     return getBalance();
 }


 @Override
 public double transfer(double amount, String concept) throws AccountException {
     throw new AccountException("Transfers are not allowed in Savings Account");
 }

 // Método para estimar el interés después de n días
 public double estimateInterest(int days) {
     return getBalance() * INTEREST_RATE * (days / 365.0);
 }

 @Override
 public String toString() {
     return "Savings Account Details:\n" + super.toString();
 }
}
