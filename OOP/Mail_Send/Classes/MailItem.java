package Examen_2.Classes;

import Examen_2.Enums.DistributionPoints;

public class MailItem extends Package {
    private double weight;
    private String description; // Description of the content

    public MailItem(User sender, User recipient, double weight, String description) {
        super(sender, recipient);
        this.weight = weight;
        this.description = description;
    }

    @Override
    public double estimatePrice(String destinationState) {
        // Determine the cost per kg based on the destination state
        if ("Oaxaca".equalsIgnoreCase(destinationState)) {
            return DistributionPoints.OAXACA.getCostPerKgUSD();
        } else if ("QuintanaRoo".equalsIgnoreCase(destinationState)) {
            return DistributionPoints.QUINTANA_ROO.getCostPerKgUSD();
        } else if ("Morelos".equalsIgnoreCase(destinationState)) {
            return DistributionPoints.MORELOS.getCostPerKgUSD();
        } else if ("Queretaro".equalsIgnoreCase(destinationState)) {
            return DistributionPoints.QUERETARO.getCostPerKgUSD();
        } else if ("Sinaloa".equalsIgnoreCase(destinationState)) {
            return DistributionPoints.SINALOA.getCostPerKgUSD();
        } else {
            // Handle the case where the destination state is not recognized
            throw new IllegalArgumentException("Invalid destination state: " + destinationState);
        }
    }
    
    // Overload of the method to keep the original functionality without the need of an argument
    
    public double estimatePrice() {
            double costPerKg = getRecipientDistributionPoint().getCostPerKgUSD();
            return weight * costPerKg;
        }
    
    

    private DistributionPoints getRecipientDistributionPoint() {
        return DistributionPoints.OAXACA;
    }

    // Getters y Setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

