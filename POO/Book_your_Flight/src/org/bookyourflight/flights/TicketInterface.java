package org.bookyourflight.flights;

import org.bookyourflight.user.User;

public interface TicketInterface {
	/**
	 * This method retrieves the ticket
	 * id
	 * */
	public String getTicketId();
	public DomesticFlight getFlight();
	public User getUser();
	public double TicketPriece();
}
