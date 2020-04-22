/**
 * 
 */
package edu.upenn.cit594.datamanagement;

/**
 * @author quetzalcoatl
 *
 */
public class JSONProcessor extends Processor {

	public JSONProcessor(String filename) {
		super(filename);
	}

	@Override
	protected Reader makeReader(String filename) {
		Reader r = new JSONReader(filename);
		return r;
	}
}
