/**
 * 
 */
package edu.upenn.cit594.datamanagement;
import java.time.*;

/**
 * @author Aman Nischal and Katie Pizziketti
 * This object holds the data for a parking violation.
 *
 */
public class Violation {
	
	protected LocalDateTime dateTime;
	protected int fine;
	protected String violation;
	protected int vehicleID;
	protected String state;
	protected int ticketID;
	protected int zip;
	
	public Violation(LocalDateTime dateDatum, int fineDatum, String vioDatum, int carDatum, String stateDatum, int ticDatum, int zipDatum) {
		this.dateTime = dateDatum;
		this.fine = fineDatum;
		this.violation = vioDatum;
		this.vehicleID = carDatum;
		this.state = stateDatum;
		this.ticketID = ticDatum;
		this.zip = zipDatum;
	}

}
