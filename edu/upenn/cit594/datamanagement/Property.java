package edu.upenn.cit594.datamanagement;

public class Property {
	private double marketValue;
	private double livableArea;
	private int zipCode;
	
	Property(double value, double area, int zip){
		marketValue = value;
		livableArea = area;
		zipCode =zip; 
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getLivableArea() {
		return livableArea;
	}

	public int getZipCode() {
		return zipCode;
	}

}
