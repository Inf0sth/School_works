package org.bookyourflight.flights;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightInterface {
	public String getId();
	public AbstractFlight setId(String id) throws FlightException;
	public LocalDateTime getDepatureDate();
	public AbstractFlight setDepatureDate(LocalDateTime dep);
	public String getDepartueGate();
	public AbstractFlight setDepartueGate(String gate) throws FlightException;
	public Destination getOrigin();
	public Destination getDestination();
	public Ticket getTicket();
	Ticket assignTicket();
	public List<char[]> getFlightMap();
	public void setFlightMap(List<char[]> map);
	public boolean isInternational();
	public double calculateDuration();
	public double calculateTicketPrice();
	public void getLuggageAllowance();
}
