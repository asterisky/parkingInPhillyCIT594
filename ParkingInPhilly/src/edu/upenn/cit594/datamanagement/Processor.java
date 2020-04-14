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

	// I need 2 kinds of read function; this one will read tweets
	public Set<String> readFile(String filename) {
		Set<String> s = r.readFile(filename);
		return s;
	}

	protected abstract Reader makeReader();
}
