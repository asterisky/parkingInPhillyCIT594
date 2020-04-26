/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti
 * Abstract processor class uses factory pattern to allow for reading in files in different formats.
 *
 */
public abstract class Processor {

	protected Reader r;

	public Processor(String filename) {
		r = makeReader(filename);
	}

	public HashMap<Integer, List<Object>> readFile(){
		HashMap<Integer, List<Object>> m = r.read();
		return m;
	}

	protected abstract Reader makeReader(String filename);
}
