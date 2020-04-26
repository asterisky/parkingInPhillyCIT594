package edu.upenn.cit594.datamanagement;

import java.util.*;

//Property Processor takes in the data structure from the reader and stored it in order for the processor to analyze data

public class PropertyProcessor extends Processor {

	public PropertyProcessor(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Reader makeReader(String filename) {
		Reader r = new PropertyReaderCSV(filename);
		return r;
	}
	
	

}
