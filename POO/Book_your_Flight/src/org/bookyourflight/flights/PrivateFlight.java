package org.bookyourflight.flights;

import java.time.LocalDateTime;
import java.util.List;

public class PrivateFlight extends AbstractFlight {
	// Flight constructor
	public PrivateFlight(Destination origin, Destination destination, String Id) {
		// call abstract class constructor 
		// Id must be of the form PI-xxxx
		// Gate must be of the form Z-xx
		super(origin, destination,"PI-[0-9]{4}","Z-[0-9]{2}");
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime getDepatureDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDepartueGate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination getOrigin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket getTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket assignTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<char[]> getFlightMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFlightMap(List<char[]> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInternational() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calculateDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateTicketPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getLuggageAllowance() {
		// TODO Auto-generated method stub
		
	}

}
