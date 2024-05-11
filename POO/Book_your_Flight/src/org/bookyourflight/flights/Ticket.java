package org.bookyourflight.flights;

import java.util.Random;
import org.bookyourflight.user.User;

public class Ticket implements TicketInterface{
	// Variable type - name - value
	private String Id;
	private DomesticFlight Flight;
	private User User;
	private double TicketPriece;
	
	// default constructor Ticket()
	public Ticket(DomesticFlight flight, User user) {
		// define the ticket id
		Random rand = new Random(9999);
		this.Id = "RE-"+rand.nextInt(); 
		this.Flight = flight;
		this.User = user;
		this.TicketPriece = rand.nextDouble();
	}
	
	public String getTicketId() {
		return this.Id;
	}

	
	public DomesticFlight getFlight() {
		return this.Flight;
	}

	
	public User getUser() {
		return this.User;
	}

	
	public double TicketPriece() {
		return this.TicketPriece;
	}
	
	@Override
	public String toString() {
		return "Your ticket id is "+this.Id;
	}
}
