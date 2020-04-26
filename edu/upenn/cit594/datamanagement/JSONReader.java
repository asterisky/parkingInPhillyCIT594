/**
 * 
 */
package edu.upenn.cit594.datamanagement;

import java.io.*;
import java.util.*;

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
	protected String filename;

	public JSONReader(String file) {
		this.filename = file;
	}

	@Override
	public HashMap<Integer, List<Object>> read() {
		Map<Integer, List<Object>> parkingViolations = new HashMap<>();

		try {
			// create helper objects to parse the JSON
			JSONParser parser = new JSONParser();
			JSONArray dataArray = (JSONArray) parser.parse(new FileReader(filename));
			Iterator iter = dataArray.iterator();

			// iterate through the JSON object lines to get the desired data into the Set
			while (iter.hasNext()) {

				// get data from object into string
				JSONObject rowData = (JSONObject) iter.next();
				
				//cannot parse null data into double or int
				if (rowData.get("fine").toString() != null && rowData.get("fine").toString().equals("") != true
						&& rowData.get("zip_code").toString() != null
						&& rowData.get("zip_code").toString().equals("") != true) {
					String date = rowData.get("date").toString();
					double fine = Double.parseDouble(rowData.get("fine").toString());
					String violation = rowData.get("violation").toString();
					String vehicleID = rowData.get("plate_id").toString();
					String state = rowData.get("state").toString();
					String ticketID = rowData.get("ticket_number").toString();
					int zip = Integer.valueOf(rowData.get("zip_code").toString());

					//we want to discard rows with missing data
					if (date != null && violation != null && vehicleID != null && state != null && ticketID != null
							&& zip > 9999) {

						// create ParkingViolation object
						ParkingViolation pv = new ParkingViolation(date, fine, violation, vehicleID, state, ticketID,
								zip);

						// if the zipcode is already in the Map, append a new Parking Violation
						if (parkingViolations.containsKey(zip)) {
							parkingViolations.get(zip).add(pv);
						}
						// if this is a new zipcode, start a new list
						else {
							ArrayList<Object> listOfPV = new ArrayList<>();
							listOfPV.add(pv);

							parkingViolations.put(zip, listOfPV);
						}
					}
				}
			}
		} catch (ParseException | IOException e) {
			System.out.println("Error reading JSON file.");

			e.printStackTrace();
		}

		return parkingViolations;
	}

}
