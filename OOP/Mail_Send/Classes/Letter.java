package Examen_2.Classes;

import Examen_2.Enums.DistributionPoints;

public class Letter extends Package {

    public Letter(User sender, User recipient) {
        super(sender, recipient);
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
}

