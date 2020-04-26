package edu.upenn.cit594.datamanagement;
import java.util.*;

//Interface that will take the data and return a generic list of doubles that you can average based on zip code
public interface DataAverager {
	
	public List<Double> average(Map<Integer, List<Object>> propertyData , int zipCode);
	

}
