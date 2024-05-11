package org.bookyourflight.com;

import java.time.LocalDateTime;

import org.bookyourflight.flights.AbstractFlight;
import org.bookyourflight.flights.Destination;
import org.bookyourflight.flights.DomesticFlight;
import org.bookyourflight.flights.FlightException;
import org.bookyourflight.flights.Ticket;

public class Main {

	public static void main(String[] args) {
		
		try { 
			DomesticFlight f = (DomesticFlight) new DomesticFlight(Destination.UK, Destination.MX)
					.setId("RE-4346")
					.setDepartueGate("A-04")
					.setDepatureDate(LocalDateTime.now());
		} catch(FlightException ex) {
			System.out.println("An exception has acurred " + ex.getMessage());
		} finally {
			System.out.println(" This is happening no matter what");
		}
		
		// TODO Auto-generated method stub
		//System.out.println(ex);
		
	}

}
