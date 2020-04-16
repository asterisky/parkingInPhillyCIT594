/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.time.*;

/**
 * @author Aman Nischal and Katie Pizziketti This object holds the data for a
 *         parking violation.
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

	//constructor for one row of data on parking violations
	public Violation(LocalDateTime dateDatum, int fineDatum, String vioDatum, int carDatum, String stateDatum,
			int ticDatum, int zipDatum) {
		this.dateTime = dateDatum;
		this.fine = fineDatum;
		this.violation = vioDatum;
		this.vehicleID = carDatum;
		this.state = stateDatum;
		this.ticketID = ticDatum;
		this.zip = zipDatum;
	}
	
	//it's all getters and setters down here

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public String getViolation() {
		return violation;
	}

	public void setViolation(String violation) {
		this.violation = violation;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

}
