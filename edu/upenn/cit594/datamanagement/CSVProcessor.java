package edu.upenn.cit594.datamanagement;

public class CSVProcessor extends Processor {

	public CSVProcessor(String filename) {
		super(filename);
		
	}
	
	@Override
	protected Reader makeReader(String filename) {
		Reader r = new ParkingCSVReader(filename);
		return r;
	}

}
