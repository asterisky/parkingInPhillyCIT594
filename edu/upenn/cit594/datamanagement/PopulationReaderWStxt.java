/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti reads population data from a
 *         whitespace-separated text document.
 *
 */
public class PopulationReaderWStxt implements Reader {

	protected String filename;

	public PopulationReaderWStxt(String file) {
		this.filename = file;
	}

	@Override
	public Map<Integer, List<Object>> read() {
		HashMap<Integer, List<Object>> population = new HashMap<>();
		List<Object> popList = new ArrayList<>();

		// import the text, read by line, parse by comma
		File file = new File(filename);
		Scanner s; // scanner needed to look at the file

		try {
			s = new Scanner(file);
			while (s.hasNextLine()) { // keep looking for another line in the file
				// parse state components
				// get line and split by comma
				String[] splLine = s.nextLine().split("\\s+");
				Integer zip = Integer.valueOf(splLine[0]);
				Integer pop = Integer.valueOf(splLine[1]);
				popList.add(pop);
				if (!population.containsKey(zip)) {
					population.put(zip, popList);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(
					"I cannot find the population file.  Please check the file name in the argument and restart the program.");

			e.printStackTrace();
		}
		return population;
	}
}
