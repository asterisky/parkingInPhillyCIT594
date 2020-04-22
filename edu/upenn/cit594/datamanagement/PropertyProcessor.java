package edu.upenn.cit594.datamanagement;

import java.util.*;

//Property Processor takes in the data structure from the reader and stored it in order for the processor to analyze data

public class PropertyProcessor {
	private HashMap<Integer,List<Object>> data;
	
	PropertyProcessor(HashMap<Integer,List<Object>> data){
		this.data =data;
		
	}
	
	//Uses Strategy method to get the average value on the stored dataset, it will either use the valueAverager or LivableArea 
	//Averager which both implement DataAverager 
	
	public double averageValue(int zipCode){
		
		return getAverage(data, zipCode, new ValueAverager());
		
	}
	public double averageLivableArea(int zipCode) {
		return getAverage(data, zipCode, new LivableAreaAverager());
	}
	
	
	//this is the helper method that will use the interface DataAverager to create the appropriate averager
	private double getAverage(HashMap<Integer,List<Object>> data, int zipCode, DataAverager da) {
		List<Double> values = da.average(data, zipCode);
		double average =0;
		for (Double d : values) {
			average += d;
		}
		return average/values.size();
	}
	//testing to see if it works
	public static void main(String[] args) {
		PropertyReaderCSV reader = new PropertyReaderCSV("PropertiesSmall.csv");
		HashMap<Integer, List<Object>> test = reader.readFile();
		PropertyProcessor pp = new PropertyProcessor(test); 
		System.out.println(pp.averageLivableArea(19147));
	}

}
