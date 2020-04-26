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

	protected HashMap<HashMap<Integer, List<Object>>, Integer> memoizationTotalPop = new HashMap<>();
	protected HashMap<HashMap<Integer, List<Object>>, Map<Integer, Double>> memoizationFinesPerCapita = new HashMap<>();
	protected HashMap<Integer, Double> memoizationMarketValPerCapita = new HashMap<>();
	protected HashMap<Integer, Double> avgValueResults = new HashMap<>();
	protected HashMap<Integer, Double> avgLivableAreaResults = new HashMap<>(); 
	protected HashMap<Integer, Double> customQuestion = new HashMap<>();

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
	public Map<Integer, Double> totalFinesPerCapita(HashMap<Integer, List<Object>> parkingData,
			HashMap<Integer, List<Object>> populationData) {
		TreeMap<Integer, Double> perCapita = new TreeMap<>();
		// error checking and memoization
		if (parkingData == null || populationData == null) {
			return perCapita;
		}
		if (memoizationFinesPerCapita != null && memoizationFinesPerCapita.containsKey(populationData)) {
			return memoizationFinesPerCapita.get(populationData);
		}

		// iterate through each zip code in the populations data set
		for (Integer z : populationData.keySet()) {
			List<Object> violations = parkingData.get(z);
			int[] population = getAsInteger(populationData.get(z));
			// Population, num of violations, and fines per capita should be > 0
			if (population != null && violations != null && population[0] > 0 && violations.size() > 0) {
				Double finesInZip = totalFinesForZip(violations, population[0]);
				if (finesInZip > 0) {
					perCapita.put(z, finesInZip);
				}
			}
		}

		memoizationFinesPerCapita.put(populationData, perCapita);
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
	 * finds total fines per capita for a SINGLE zip code
	 */
	public double totalMarketValForZip(Integer zip, HashMap<Integer, List<Object>> properties, HashMap<Integer, List<Object>> populations) {
		if(memoizationMarketValPerCapita.containsKey(zip)) {
			return memoizationMarketValPerCapita.get(zip);
		}
		
		//just error checking first
		if(zip == null || properties == null || populations == null || zip < 9999 || zip > 99999 || properties.containsKey(zip) == false || populations.containsKey(zip) == false ) {
			memoizationMarketValPerCapita.put(zip, 0.0);
			return 0.0;
		}
		
		//setting up variables to hold values
		double totalValue = 0.0;
		List<Object> propsInZip = properties.get(zip);
		List<Object> popInZip = populations.get(zip);
		int[] population = getAsInteger(popInZip);
		//quick error check for population size
		if(population[0] <= 0) {
			memoizationMarketValPerCapita.put(zip, 0.0);
			return 0.0;
		}
		
		//sum property values in that zipcode
		for (Object o : propsInZip) {
			Property p = (Property) o;
			double propertyValue = p.getMarketValue();
			totalValue += propertyValue;
		}
		//error check for property value number
		if(totalValue <= 0) {
			memoizationMarketValPerCapita.put(zip, 0.0);
			return 0.0;
		}
		
		memoizationMarketValPerCapita.put(zip, (totalValue / population[0]));
		return (totalValue / population[0]);
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
	
	//Uses Strategy method to get the average value on the stored dataset, it will either use the valueAverager or LivableArea 
		//Averager which both implement DataAverager & uses memoization to store values in a HashMap for a zipCode
		
		public double averageValue(HashMap<Integer, List<Object>> propertyData, int zipCode){
			if (avgValueResults.containsKey(zipCode)) {
				return avgValueResults.get(zipCode);
			}
			else {
				double result = getAverage(propertyData, zipCode, new ValueAverager());
				avgValueResults.put(zipCode, result);
				return result;
			}
		}
		
		public double averageLivableArea(HashMap<Integer, List<Object>> propertyData, int zipCode) {
			if (avgLivableAreaResults.containsKey(zipCode)) {
				return avgLivableAreaResults.get(zipCode);
			}
			else {
				double result = getAverage(propertyData, zipCode, new LivableAreaAverager());
				avgLivableAreaResults.put(zipCode, result);
				return result;
			}
		}
		
		public double averageFinesPerCapitaPerAveragePropertyValueRatio(HashMap<Integer, List<Object>> propertyData, 
				TreeMap<Integer, Double> perCapita,int zipCode) {
			if (customQuestion.containsKey(zipCode)) {
				return customQuestion.get(zipCode);
			}
			else {
				double finesPerCapita = perCapita.get(zipCode);	
				double avgPropertyValue = averageValue(propertyData, zipCode);
				double ratio = finesPerCapita/avgPropertyValue;
				customQuestion.put(zipCode, ratio);
				return ratio;
				
			}
			
		}
		
		
		//this is the helper method that will use the interface DataAverager to create the appropriate averager
		private double getAverage(HashMap<Integer, List<Object>> propertyData, int zipCode, DataAverager da) {
			List<Double> values = da.average(propertyData, zipCode);
			double average =0;
			for (Double d : values) {
				average += d;
			}
			return average/values.size();
		}


//	for testing methods:
//	public static void main(String[] args) {
//		Analysis a = new Analysis();
//		Processor pop = new WStxtProcessor("/Users/quetzalcoatl/Downloads/population.txt");
//		Processor vio = new JSONProcessor("/Users/quetzalcoatl/Downloads/parking.json");
//		PropertyReaderCSV houses = new PropertyReaderCSV("/Users/quetzalcoatl/eclipse/MCIT/594/parkingInPhillyCIT594/PropertiesSmall.csv");
//		HashMap<Integer, List<Object>> population = (HashMap<Integer, List<Object>>) pop.readFile();
//		HashMap<Integer, List<Object>> violations = (HashMap<Integer, List<Object>>) vio.readFile();
//		HashMap<Integer, List<Object>> properties = (HashMap<Integer, List<Object>>) houses.readFile();
//
//		Map<Integer, Double> finesPerCap = a.totalFinesPerCapita(violations, population);
//		int totalPop = a.totalPopulationByZip(population);
//		
//		
//		for (Integer e : finesPerCap.keySet()) {
//			System.out.println(e + "\t" + finesPerCap.get(e));
//		}
//	}
}
