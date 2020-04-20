/**
 * 
 */
package edu.upenn.cit594.datamanagement;

/**
 * @author Aman Nischal and Katie Pizziketti
 *	Processes whitespace-delim text files
 */
public class WStxtProcessor extends Processor{

	@Override
	protected Reader makeReader() {
		Reader r = new PopulationReaderWStxt();
		return r;
	}

}
