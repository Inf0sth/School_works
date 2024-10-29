package banking.transactions;

import java.util.UUID;

public class Transaction implements TransactionInterface {
    private String id;
    private TransactionEnum transactionType;
    private String concept;
    private double amount;

    public Transaction(TransactionEnum transactionType, String concept, double amount) {
        this.id = generateId();
        this.transactionType = transactionType;
        this.concept = concept;
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    private String generateId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16).replaceAll("(.{4})", "$1-").substring(0, 19);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public TransactionEnum getTransactionType() {
        return transactionType;
    }

    @Override
    public String getConcept() {
        return concept;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
