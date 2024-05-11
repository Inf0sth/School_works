package org.bookyourflight.flights;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFlight implements FlightInterface {
	// Flight parameters
	private String Id;
	private String DepartureGate;
	private LocalDateTime DepartureDate;
	private List<char[]> FlightMap;
	private Map<String, Ticket> Tickets = new HashMap<String, Ticket>();
	private Destination Origin;
	private Destination Destination;
	private final String FlightRegex;
	private final String GateRegex;
	
	// Constructor for the abstract class
	public AbstractFlight(Destination org, Destination dest, String fligthRegex, String gateRegex) {
		this.Origin = org;
		this.Destination = dest;
		this.FlightRegex = fligthRegex;
		this.GateRegex = gateRegex;
	}
	
	@Override
	public String getId() {
		return this.Id;
	}
	
	/**
	 * Sets the id and verifies it follows the standard
	 * @throws FlightException 
	 * */
	@Override
	public AbstractFlight setId(String id) throws FlightException {
		// Creates the string pattern from the regex
		Pattern pattern = Pattern.compile(this.FlightRegex);
		// Create the matcher object
		Matcher matcher = pattern.matcher(id);
		// Find the regex in the string
		if(matcher.find()) {
			this.Id = id;
		} else {
			throw new FlightException("Id is not a valid flight id");
		}
		return this;
	}
	
	@Override
	public LocalDateTime getDepatureDate() {
		return this.DepartureDate;
	}
	
	@Override
	public AbstractFlight setDepatureDate(LocalDateTime dep) {
		this.DepartureDate = dep;
		return this;
	}
	
	@Override
	public String getDepartueGate() {
		return this.DepartureGate;
	}
	
	@Override
	public AbstractFlight setDepartueGate(String gate) throws FlightException {
		// Creates the string pattern from the regex
		Pattern pattern = Pattern.compile(this.GateRegex);
		// Create the matcher object
		Matcher matcher = pattern.matcher(gate);
		// Find the regex in the string
		if(matcher.find()) {
			this.DepartureGate = gate;
		} else {
			throw new FlightException("The provided gate is not valid");
		}
		this.DepartureGate = gate;
		return this;
	}
	
	@Override
	public Destination getOrigin() {
		return this.Origin;
	}
	
	@Override
	public Destination getDestination() {
		return this.Destination;
	}
	
	@Override
	public List<char[]> getFlightMap() {
		return this.FlightMap;
	}
	
	@Override
	public void setFlightMap(List<char[]> map) {
		this.FlightMap = map;
	}
	
	@Override
	public boolean isInternational() {
		return this.Origin.getRegion().equals(Destination.getRegion());
	}
}