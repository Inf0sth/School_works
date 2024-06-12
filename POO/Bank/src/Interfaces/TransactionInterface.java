package Transactions;

//Define the TransactionInterface with the necessary getters
public interface TransactionInterface {
 String getId();
 TransactionEnum getTransactionType();
 String getConcept();
 double getAmount();
}
