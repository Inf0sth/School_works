package org.bookyourflight.flights;

// Enum is a special class
public enum Destination {
	// Country-CapitalCity-Region
	UK("United Kingdom","Londres","Europe"),
	MX("Mexico","CDMX","Latam"),
	USA("United States","Washington","America");
	
	private String Country;
	private String CapitalCity;
	private String Region;
	
	// We need a constructor
	private Destination(String country, String capitalCity, String region){
		this.Country = country;
		this.CapitalCity = capitalCity;
		this.Region = region;
	}
	
	/**
	 * Returns the country of the destination
	 * */
	public String getCountry() {
		return this.Country;
	}

	public String getCapitalCity() {
		return CapitalCity;
	}

	public String getRegion() {
		return Region;
	}
}
