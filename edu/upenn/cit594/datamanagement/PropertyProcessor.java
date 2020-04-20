package edu.upenn.cit594.datamanagement;

import java.util.*;

public class PropertyProcessor {
	private HashMap<Integer,List<Property>> data;
	PropertyProcessor(HashMap<Integer,List<Property>> data){
		this.data =data;
		
	}
	
	public double averageValue(int zipCode){
		
		return getAverage(data, zipCode, new ValueAverager());
		
	}
	public double averageLivableArea(int zipCode) {
		return getAverage(data, zipCode, new LivableAreaAverager());
	}
	
	private double getAverage(HashMap<Integer,List<Property>> data, int zipCode, DataAverager da) {
		List<Double> values = da.average(data, zipCode);
		double average =0;
		for (Double d : values) {
			average += d;
		}
		return average/values.size();
	}
	
	public static void main(String[] args) {
		PropertyReaderCSV reader = new PropertyReaderCSV("PropertiesSmall.csv");
		HashMap<Integer, List<Property>> test = reader.readFile();
		PropertyProcessor pp = new PropertyProcessor(test); 
		System.out.println(pp.averageLivableArea(19147));
	}

}
