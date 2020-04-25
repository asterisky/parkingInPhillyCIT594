package edu.upenn.cit594.datamanagement;

import java.util.*;

//Property Processor takes in the data structure from the reader and stored it in order for the processor to analyze data

public class PropertyProcessor {
	private HashMap<Integer,List<Object>> data;
	//for memoization 2 hashsets storing the values for the zipcode
	private HashMap<Integer, Double> avgValueResults = new HashMap<>();
	private HashMap<Integer, Double> avgLivableAreaResults = new HashMap<>(); 
	
	
	PropertyProcessor(HashMap<Integer,List<Object>> data){
		this.data =data;
		
	}
	
	//Uses Strategy method to get the average value on the stored dataset, it will either use the valueAverager or LivableArea 
	//Averager which both implement DataAverager & uses memoization to store values in a HashMap for a zipCode
	
	public double averageValue(int zipCode){
		if (avgValueResults.containsKey(zipCode)) {
			return avgValueResults.get(zipCode);
		}
		else {
			double result = getAverage(data, zipCode, new ValueAverager());
			avgValueResults.put(zipCode, result);
			return result;
		}
	}
	
	
	public double averageLivableArea(int zipCode) {
		if (avgLivableAreaResults.containsKey(zipCode)) {
			return avgLivableAreaResults.get(zipCode);
		}
		else {
			double result = getAverage(data, zipCode, new LivableAreaAverager());
			avgLivableAreaResults.put(zipCode, result);
			return result;
		}
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
		HashMap<Integer, List<Object>> test = (HashMap<Integer, List<Object>>) reader.read();
		PropertyProcessor pp = new PropertyProcessor(test); 
		System.out.println(pp.averageValue(19147));
		System.out.println(pp.averageValue(19148));
	}

}
