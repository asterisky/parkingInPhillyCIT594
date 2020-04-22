/**
 * 
 */
package edu.upenn.cit594.Processor;

import edu.upenn.cit594.datamanagement.*;
import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti Performs manipulations of data and
 *         stores memoization datasets.
 *
 */
public class Analysis {

	protected HashMap<HashMap<Integer, List<Object>>, Integer> memoization;

	/*
	 * Sums populations for all zip codes.
	 */
	public int totalPopulationByZip(HashMap<Integer, List<Object>> popList) {
		int totalPop = 0;

		if (popList == null) {
			return totalPop;
		}

		if (memoization.containsKey(popList)) {
			return memoization.get(popList);
		}

		// get population of each zip code and update counters
		for (Integer p : popList.keySet()) {
			List<Object> localPop = new ArrayList<>();
			localPop.add(popList.get(p));
			String e = localPop.get(0).toString();
			e = e.replaceAll("\\[|\\]", "");
			Integer element = Integer.valueOf(e);

			totalPop += element;
		}
		memoization.put(popList, totalPop);
		return (totalPop);
	}

	public Set<double[]> totalFinesPerCapita(HashMap<Integer, List<Object>> parkingViolations,
			HashMap<Integer, List<Object>> populations) {
		Set<double[]> perCapita = new TreeSet<>();
		
		for(Integer z : populations.keySet()) {
			double finesInZip = totalFinesForZip(z, populations.get(z));
		}
		
		return perCapita;
	}

	/*
	 * helper method finds total fines per capita for a SINGLE zip code
	 */
	private double totalFinesForZip(List<Object> violations, Integer population) {
		double sumFines = 0.0;
		for (Object o : violations) {
			ParkingViolation pv = (ParkingViolation) o;
			if (pv.getVehicleState().equals("PA")) {
				double fine = pv.getFine();
				sumFines += fine;
			}
		}
		return (sumFines / population);
	}
}
