
import java.util.EnumMap;
import java.util.UUID;

import Transaction;
import Enums.TransactionEnum;
import Classes.Abstract.AbstractAccount;

//Define la clase BusinessAccount extendiendo AbstractAccount
public class BusinessAccount extends AbstractAccount {

 // Constantes para límites y tarifas específicas de BusinessAccount
 private static final double MIN_BALANCE = 5000.0;
 private static final double OPEN_FEE = 100.0;
 private static final double MAINTENANCE_FEE = 50.0;

 // Enum para las categorías de transacciones comerciales
 public enum BusinessCategory {
     PAYMENTS,
     MAINTENANCE,
     // Agrega más categorías según sea necesario
 }

 // Constructor para inicializar la cuenta con tarifas de apertura y mantenimiento
 public BusinessAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException {
     createAccount(name, email, phoneNumber, initialBalance);
 }

 @Override
 public void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException {
     if (initialBalance < MIN_BALANCE + OPEN_FEE + MAINTENANCE_FEE) {
         throw new AccountCreationException("Saldo inicial insuficiente para cubrir las tarifas de apertura y mantenimiento");
     }
     setAccountNumber(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18));
     setName(name);
     setEmail(email);
     setPhoneNumber(phoneNumber);
     setAccountLimit(Double.POSITIVE_INFINITY); // Sin límite de retiro
     setOpenFee(OPEN_FEE);
     setMaintenanceFee(MAINTENANCE_FEE);
     updateBalance(initialBalance - OPEN_FEE - MAINTENANCE_FEE);
 }

 @Override
 public double withdraw(double amount) throws AccountException {
     if (getBalance() - amount < MIN_BALANCE) {
         throw new AccountException("No se puede retirar el monto especificado debido al saldo mínimo requerido");
     }
     Transaction transaction = new Transaction(TransactionEnum.PAYMENTS, "Retiro", amount);
     addMovement(transaction.getId(), transaction);
     updateBalance(-amount);
     return getBalance();
 }

 @Override
 public double transfer(double amount, String concept) throws AccountException {
     throw new AccountException("Transfers are not allowed in Business Account");
 }

 @Override
 public String toString() {
     return "Business Account Details:\n" + super.toString();
 }
}
