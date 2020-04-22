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

	protected HashMap<HashMap<Integer, List<Object>>, Integer> memoizationTotalPop;
	protected HashMap<HashMap<Integer, List<Object>>, Map<Integer, Double>> memoizationFinesPerCapita;

	/*
	 * Sums populations for all zip codes.
	 */
	public int totalPopulationByZip(HashMap<Integer, List<Object>> popList) {
		int totalPop = 0;

		if (popList == null) {
			return totalPop;
		}

		if (memoizationTotalPop != null && memoizationTotalPop.containsKey(popList)) {
			return memoizationTotalPop.get(popList);
		}

		// get population of each zip code and update counters
		for (Integer p : popList.keySet()) {
			int[] localPop = getAsInteger(popList.get(p));
			totalPop += localPop[0];
		}
		memoizationTotalPop.put(popList, totalPop);
		return (totalPop);
	}

	/*
	 * Returns a treemap (sorted) of zip codes (key) and total fines per capita
	 */
	public Map<Integer, Double> totalFinesPerCapita(HashMap<Integer, List<Object>> parkingViolations,
			HashMap<Integer, List<Object>> populations) {
		TreeMap<Integer, Double> perCapita = new TreeMap<>();
		// error checking and memoization
		if (parkingViolations == null || populations == null) {
			return perCapita;
		}
		if (memoizationFinesPerCapita != null && memoizationFinesPerCapita.containsKey(populations)) {
			return memoizationFinesPerCapita.get(populations);
		}

		// iterate through each zip code in the populations data set
		for (Integer z : populations.keySet()) {
			List<Object> violations = parkingViolations.get(z);
			int[] population = getAsInteger(populations.get(z));
			// Population, num of violations, and fines per capita should be > 0
			if (population != null && violations != null && population[0] > 0 && violations.size() > 0) {
				Double finesInZip = totalFinesForZip(violations, population[0]);
				if (finesInZip > 0) {
					perCapita.put(z, finesInZip);
				}
			}
		}
		/**
		 * MEMOIZATION IS NOT WORKING AND I DON'T KNOW WHY :D
		 **/
//		memoizationFinesPerCapita.put(populations, perCapita);
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

	/*
	 * helper method to parse objects as integer array
	 */
	private int[] getAsInteger(List<Object> list) {
		int[] listOfInts = new int[list.size()];
		int i = 0;
		for (Object o : list) {
			String str = o.toString();
			str = str.replaceAll("\\[|\\]", "");
			Integer element = Integer.valueOf(str);
			listOfInts[i] = element;
			i++;
		}
		return listOfInts;
	}

//	for testing methods:
//	public static void main(String[] args) {
//		Analysis a = new Analysis();
//		Processor pop = new WStxtProcessor("/Users/quetzalcoatl/Downloads/population.txt");
//		Processor vio = new JSONProcessor("/Users/quetzalcoatl/Downloads/parking.json");
//		HashMap<Integer, List<Object>> population = (HashMap<Integer, List<Object>>) pop.readFile();
//		HashMap<Integer, List<Object>> violations = (HashMap<Integer, List<Object>>) vio.readFile();
//
//		Map<Integer, Double> finesPerCap = a.totalFinesPerCapita(violations, population);
//
//		for (Integer e : finesPerCap.keySet()) {
//			System.out.println(e + "\t" + finesPerCap.get(e));
//		}
//	}
}
