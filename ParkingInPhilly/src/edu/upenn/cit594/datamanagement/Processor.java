/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti Abstract processor class uses
 *         factory pattern to allow for reading in files in different formats
 *
 */
public abstract class Processor {

	protected Reader r;

	public Processor() {
		r = makeReader();
	}

	public Map<Integer, Violation> readFile(String filename) {
		Map<Integer, Violation> m = r.readFile(filename);
		return m;
	}

	protected abstract Reader makeReader();
}
