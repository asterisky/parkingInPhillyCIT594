/**
 * 
 */
package edu.upenn.cit594.Processor;
import edu.upenn.cit594.datamanagement.*;
import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti
 * Performs manipulations of data and stores memoization datasets.
 *
 */
public class Analysis {

	protected HashMap<HashMap<Integer, List<Object>>, Integer> memoization;
	
	/*
	 * Sums populations for all zip codes.
	 */
	public int totalPopulationByZip(HashMap<Integer, List<Object>> popList) {
		int totalPop = 0;
		
		if(popList == null) {
			return totalPop;
		}
		
		if(memoization.containsKey(popList)) {
			return memoization.get(popList);
		}
		
		//get population of each zip code and update counters
		for(Integer p : popList.keySet()) {
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
	

}
