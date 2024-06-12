package Transactions;

import java.util.Random;
import java.text.DecimalFormat;
import Interfaces.TransactionInterface;
// Define the Transaction class that implements the TransactionInterface
public class Transaction implements TransactionInterface {
    private String id;
    private TransactionEnum transactionType;
    private String concept;
    private double amount;

    // Constructor to initialize the transaction
    public Transaction(TransactionEnum transactionType, String concept, double amount) {
        this.id = generateId();
        this.transactionType = transactionType;
        this.concept = concept;
        setAmount(amount);
    }

    // Method to generate a unique ID of 16 digits, separated by - every 4 digits
    private String generateId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
            if ((i + 1) % 4 == 0 && i != 15) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    // Method to set the amount ensuring only 2 decimal places
    private void setAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.amount = Double.parseDouble(df.format(amount));
    }

    // Getter for the ID
    @Override
    public String getId() {
        return id;
    }

    // Getter for the transaction type
    @Override
    public TransactionEnum getTransactionType() {
        return transactionType;
    }

    // Getter for the concept
    @Override
    public String getConcept() {
        return concept;
    }

    // Getter for the amount
    @Override
    public double getAmount() {
        return amount;
    }
}
