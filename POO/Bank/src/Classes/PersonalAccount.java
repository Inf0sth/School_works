package Account;

import java.util.UUID;

import Transaction;
import Enums.TransactionEnum;
import Abstract.AbstractAccount;
// Define la clase PersonalAccount extendiendo AbstractAccount
public class PersonalAccount extends AbstractAccount {

    // Constantes para límites y tarifas específicas de PersonalAccount
    private static final double LIMITE_RETIRO_MAXIMO = 500.0;
    private static final double SALDO_MINIMO = 1000.0;
    private static final double TARIFA_APERTURA = 2.0;
    private static final double TARIFA_MANTENIMIENTO = 10.0;

 // Constructor para inicializar la cuenta con tarifas deducidas del saldo inicial
    public PersonalAccount(String nombre, String email, String numeroTelefono, double saldoInicial) throws AccountCreationException, AccountException {
        createAccount(nombre, email, numeroTelefono, saldoInicial);
    }


    @Override
    public void createAccount(String nombre, String email, String numeroTelefono, double saldoInicial) throws AccountCreationException, AccountException {
        if (saldoInicial < TARIFA_APERTURA + TARIFA_MANTENIMIENTO) {
            throw new AccountCreationException("El saldo inicial no es suficiente para cubrir las tarifas");
        }
        setAccountNumber(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 18));
        setName(nombre);
        setEmail(email);
        setPhoneNumber(numeroTelefono);
        setAccountLimit(LIMITE_RETIRO_MAXIMO);
        setOpenFee(TARIFA_APERTURA);
        setMaintenanceFee(TARIFA_MANTENIMIENTO);
        updateBalance(saldoInicial - TARIFA_APERTURA - TARIFA_MANTENIMIENTO);
    }


    @Override
    public double withdraw(double cantidad) throws AccountException {
        if (cantidad > LIMITE_RETIRO_MAXIMO) {
            throw new AccountException("La cantidad de retiro excede el límite máximo de $500");
        }
        if (getBalance() - cantidad < SALDO_MINIMO) {
            throw new AccountException("El retiro reduciría el saldo por debajo del mínimo requerido de $1000");
        }
        updateBalance(-cantidad);
        Transaction transaccion = new Transaction(TransactionEnum.RETIRO, "Retiro", cantidad);
        addMovement(transaccion.getId(), transaccion);
        return getBalance();
    }

    @Override
    public double transfer(double cantidad, String concepto) throws AccountException {
        if (cantidad > LIMITE_RETIRO_MAXIMO) {
            throw new AccountException("La cantidad de transferencia excede el límite máximo de $500");
        }
        if (getBalance() - cantidad < SALDO_MINIMO) {
            throw new AccountException("La transferencia reduciría el saldo por debajo del mínimo requerido de $1000");
        }
        updateBalance(-cantidad);
        Transaction transaccion = new Transaction(TransactionEnum.TRANSFERENCIA, concepto, cantidad);
        addMovement(transaccion.getId(), transaccion);
        return getBalance();
    }

    @Override
    public String toString() {
        return "Detalles de la Cuenta Personal:\n" + super.toString();
    }
}
