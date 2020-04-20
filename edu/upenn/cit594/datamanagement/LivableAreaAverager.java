package edu.upenn.cit594.datamanagement;

import java.util.*;

public class LivableAreaAverager implements DataAverager{

	@Override
	public List<Double> average(HashMap<Integer, List<Property>> data, int zipCode) {
		ArrayList<Double> values = new ArrayList<>();
		for (Property p : data.get(zipCode)) {
			values.add(p.getLivableArea());
		}
		return values;
	}

}
