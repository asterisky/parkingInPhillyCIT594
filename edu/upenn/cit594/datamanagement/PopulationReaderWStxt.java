/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.upenn.cit594.logging.Logger;

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
	public HashMap<Integer, List<Object>> read() {
		HashMap<Integer, List<Object>> population = new HashMap<>();

		// import the text, read by line, parse by whitespace
		File file = new File(filename);
		Logger.getLogger().logOpenFile(filename);

		Scanner s; // scanner needed to look at the file

		try {
			s = new Scanner(file);
			while (s.hasNextLine()) { // keep looking for another line in the file
				// parse file components
				// get line and split by any amount of whitespace
				String[] splLine = s.nextLine().split("\\s+");
				if (splLine[0] != null && splLine[1] != null) {
					List<Object> popList = new ArrayList<>(); // each list must be unique or they will all append to the
																// same list

					//split creates a string array so I separate these and finalize them
					Integer zip = Integer.valueOf(splLine[0]);
					Integer pop = Integer.valueOf(splLine[1]);
					popList.add(pop);
					//sanity check - each zip code should be unique!
					if (!population.containsKey(zip)) {
						population.put(zip, popList);
					}
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
