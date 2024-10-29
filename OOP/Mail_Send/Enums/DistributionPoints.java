package Examen_2.Enums;

public enum DistributionPoints {
    OAXACA("OAX", 48.2, 2.50),
    QUINTANA_ROO("QROO", 36.5, 3.70),
    MORELOS("MOR", 18.4, 1.60),
    QUERETARO("QRO", 35.3, 3.0),
    SINALOA("SIN", 48.4, 4.0);

    // Properties of each enum constant
    private final String code; // Address code
    private final double arrivalTimeHours; // Arrive time
    private final double costPerKgUSD; // Cost by each kg in USD

    // Constructor
    DistributionPoints(String code, double arrivalTimeHours, double costPerKgUSD) {
        this.code = code;
        this.arrivalTimeHours = arrivalTimeHours;
        this.costPerKgUSD = costPerKgUSD;
    }

    // Getters to acces to the attributes
    public String getCode() {
        return code;
    }

    public double getArrivalTimeHours() {
        return arrivalTimeHours;
    }

    public double getCostPerKgUSD() {
        return costPerKgUSD;
    }
}
