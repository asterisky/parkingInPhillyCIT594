package edu.upenn.cit594.datamanagement;

public class ParkingViolation {
	private String timestamp;
	private double fine; 
	private String description;
	private String vehicleID;
	private String vehicleState; 
	private String violationID;
	private int violationZip;
	
	ParkingViolation(String timestamp, double fine, String description, String vehicleID, String vehicleState,
			String violationID, int violationZip){
		this.timestamp = timestamp;
		this.fine = fine;
		this.description = description;
		this.vehicleID = vehicleID;
		this.vehicleState = vehicleState;
		this.violationID = violationID;
		this.violationZip = violationZip;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public double getFine() {
		return fine;
	}

	public String getDescription() {
		return description;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public String getVehicleState() {
		return vehicleState;
	}

	public String getViolationID() {
		return violationID;
	}

	public int getViolationZip() {
		return violationZip;
	}
	
	
	

}
