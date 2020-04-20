package edu.upenn.cit594.datamanagement;
import java.util.*;

public interface DataAverager {
	public List<Double> average(HashMap<Integer, List<Property>> data , int zipCode);
	

}
