package edu.upenn.cit594.datamanagement;

import java.util.*;
//Averager for Value, return the list of livable area based on the desired zip code
public class ValueAverager implements DataAverager {

	@Override
	public List<Double> average(HashMap<Integer, List<Property>> data, int zipCode) {
		ArrayList<Double> values = new ArrayList<>();
		
		for (Property p : data.get(zipCode)) {
			values.add(p.getMarketValue());
		}
		return values;
	}

}
