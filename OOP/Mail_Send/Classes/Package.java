package Examen_2.Classes;

import java.time.LocalDateTime;

public abstract class Package {
    private User sender; // Who sends the package
    private User recipient; // Who recibes the package

    public Package(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    // Abstract metod to estimate the price
    public abstract double estimatePrice(String destinationState);

    // Metod to get the date and time of the arrival of the package
    public LocalDateTime getEstimateArrival() {
        // Get the actual date and time
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime estimatedArrivalTime = currentTime.plusHours(48);
        
        return estimatedArrivalTime;
    }

    // Getters y setters
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
