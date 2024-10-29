package banking.transactions;

import banking.transactions.TransactionEnum;

public interface TransactionInterface {
    String getId();
    TransactionEnum getTransactionType();
    String getConcept();
    double getAmount();
}
