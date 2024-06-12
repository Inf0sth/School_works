package Account;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import Transactions.Transaction;


// Definición de la clase abstracta que implementa AccountInterface
public abstract class AbstractAccount implements AccountInterface {

    // Parámetros privados como se especificó
    private double accountLimit;
    private double openFee;
    private double maintenanceFee;
    private HashMap<String, Transaction> movements;

    // Otros miembros variables necesarios
    private String accountNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private double balance;

    // Constructor para inicializar el mapa de movimientos
    public AbstractAccount() {
        this.movements = new HashMap<>();
    }

    // Método para obtener el número de cuenta
    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    // Método para establecer el correo electrónico con validación
    @Override
    public boolean setEmail(String email) throws AccountException {
        if (email == null || !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            throw new AccountException("Formato de correo electrónico inválido");
        }
        this.email = email;
        return true;
    }

    // Método para obtener el correo electrónico
    @Override
    public String getEmail() throws AccountException {
        return email;
    }

    // Método para establecer el número de teléfono con validación
    @Override
    public boolean setPhoneNumber(String phoneNumber) throws AccountException {
        if (phoneNumber == null || !Pattern.matches("^\\+?[0-9]{10,15}$", phoneNumber)) {
            throw new AccountException("Formato de número de teléfono inválido");
        }
        this.phoneNumber = phoneNumber;
        return true;
    }

    // Método para obtener el número de teléfono
    @Override
    public String getPhoneNumber() throws AccountException {
        return phoneNumber;
    }

    // Método para establecer el nombre con validación
    @Override
    public boolean setName(String name) throws AccountException {
        if (name == null || name.isEmpty()) {
            throw new AccountException("El nombre no puede ser nulo o vacío");
        }
        this.name = name;
        return true;
    }

    // Método para obtener el nombre
    @Override
    public String getName() throws AccountException {
        return name;
    }

    // Método para obtener el saldo
    @Override
    public double getBalance() {
        return balance;
    }

    // Método para consultar una transacción por su ID
    @Override
    public String consultTransaction(String id) throws AccountException {
        if (!movements.containsKey(id)) {
            throw new AccountException("Transacción no encontrada");
        }
        return movements.get(id).toString();
    }

    // Métodos abstractos de la interfaz que deben ser implementados por las subclases
    @Override
    public abstract void createAccount(String name, String email, String phoneNumber, double initialBalance) throws AccountCreationException, AccountException;

    @Override
    public abstract double withdraw(double amount) throws AccountException;

    @Override
    public abstract double transfer(double amount, String concept) throws AccountException;

    // Métodos y setters para los parámetros privados
    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public double getOpenFee() {
        return openFee;
    }

    public void setOpenFee(double openFee) {
        this.openFee = openFee;
    }

    public double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    // Método de utilidad para agregar un movimiento
    protected void addMovement(String id, Transaction transaction) {
        movements.put(id, transaction);
    }

    // Método de utilidad para actualizar el saldo
    protected void updateBalance(double amount) {
        this.balance += amount;
    }

    // Método para establecer el número de cuenta con validación
    protected void setAccountNumber(String accountNumber) {
        if (accountNumber.length() != 18) {
            throw new IllegalArgumentException("El número de cuenta debe tener 18 dígitos");
        }
        this.accountNumber = accountNumber;
    }

    // Sobrescritura del método toString para devolver los detalles de la cuenta
    @Override
    public String toString() {
        return "Número de cuenta: " + accountNumber + "\n" +
               "Nombre: " + name + "\n" +
               "Correo electrónico: " + email + "\n" +
               "Número de teléfono: " + phoneNumber + "\n" +
               "Saldo: " + balance + "\n" +
               "Tarifa de apertura: " + openFee + "\n" +
               "Tarifa de mantenimiento: " + maintenanceFee + "\n" +
               "Límite de cuenta: " + accountLimit;
    }

    public HashMap<String, Transaction> getMovements() {
        return movements;
    }

}
