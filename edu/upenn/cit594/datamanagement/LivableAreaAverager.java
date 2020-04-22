package edu.upenn.cit594.datamanagement;

import java.util.*;
//Averager for livalable area, return the list of livable area based on the desired zip code
public class LivableAreaAverager implements DataAverager{

	@Override
	public List<Double> average(HashMap<Integer, List<Object>> data, int zipCode) {
		ArrayList<Double> values = new ArrayList<>();
		for (Object  o : data.get(zipCode)) {
			Property p = (Property) o;
			values.add(p.getLivableArea());
		}
		return values;
	}

}
