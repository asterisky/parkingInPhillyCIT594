/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Aman Nischal and Katie Pizziketti Methods to read in data from
 *         OpenDataPhilly in JSON file format. Some data cleaning is also
 *         included when importing data.
 *
 */
public class JSONReader implements Reader {

	@Override
	public Map<Integer, Violation> readFile(String filename){
		Map<Integer, Violation> parkingViolations = new HashMap<>();

		try {
			// create helper objects to parse the JSON
			JSONParser parser = new JSONParser();
			JSONArray dataArray = (JSONArray) parser.parse(new FileReader(filename));
			Iterator iter = dataArray.iterator();

			// iterate through the JSON object lines to get the desired data into the Set
			while (iter.hasNext()) {

				// get data from object into string
				JSONObject rowData = (JSONObject) iter.next();
				
				String dateStringToParse = rowData.get("date").toString();
				LocalDateTime date = LocalDateTime.parse(dateStringToParse);
				int fine = (int) rowData.get("fine");
				String violation = rowData.get("violation").toString();
				int vehicleID = (int) rowData.get("vehicle ID");
				String state = rowData.get("state").toString();
				int ticketID = (int) rowData.get("ticket number");
				int zip = (int) rowData.get("zipcode");

				// create Violation object with row data
				Violation v = new Violation(date, fine, violation, vehicleID, state, ticketID, zip);
				parkingViolations.put(vehicleID, v);
			}

		} catch (ParseException | IOException e) {
			System.out.println(
					"Error reading JSON file.");

			e.printStackTrace();
		}

		return parkingViolations;
	public Set<String> readFile(String filename) {
		Set<String> parsedData = new HashSet<String>();

		try {
			// create helper objects to parse JSON file
			JSONParser parser = new JSONParser();
			JSONArray dataArray = (JSONArray) parser.parse(new FileReader(filename));
			Iterator iter = dataArray.iterator();

			// iterate through the JSON object lines to get the desired data into the Set
			while (iter.hasNext()) {

				// get data from object into string
				JSONObject data = (JSONObject) iter.next();

				int fine = (int) data.get("fine");

			}

		} catch (ParseException | IOException e) {
			System.out.println("Data file could not be read successfully.");
			e.printStackTrace();
		}
		
		return parsedData;
	}
}
