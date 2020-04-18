package edu.upenn.cit594.datamanagement;

import java.util.*;

/**
 * @author Aman Nischal and Katie Pizziketti This interface allows different
 *         file types to be read and processed with more flexibility using
 *         factory pattern.
 *
 */

public interface Reader {

	public Map<Integer, List<ParkingViolation>> readFile();
}
