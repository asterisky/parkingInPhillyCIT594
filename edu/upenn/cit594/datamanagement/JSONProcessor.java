/**
 * 
 */
package edu.upenn.cit594.datamanagement;

/**
 * @author quetzalcoatl
 *
 */
public class JSONProcessor extends Processor {

	@Override
	protected Reader makeReader() {
		Reader r = new JSONReader();
		return r;
	}
}
