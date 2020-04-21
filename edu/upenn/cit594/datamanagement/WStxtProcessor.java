/**
 * 
 */
package edu.upenn.cit594.datamanagement;

/**
 * @author Aman Nischal and Katie Pizziketti
 *	Processes whitespace-delim text files
 */
public class WStxtProcessor extends Processor{

	public WStxtProcessor(String filename) {
		super(filename);
	}

	@Override
	protected Reader makeReader(String filename) {
		Reader r = new PopulationReaderWStxt(filename);
		return r;
	}

}
