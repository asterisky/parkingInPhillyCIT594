package edu.upenn.cit594.datamanagement;
import java.io.File;
import java.util.*;

public class ParkingCSVReader implements Reader {
	protected String filename; 
	
	ParkingCSVReader (String s){
		filename = s; 
	}

	public Map<Integer, List<Object>> read() {
		Map<Integer, List<Object>> parkingViolations = new HashMap<>(); 
		Scanner in =null; 
		try {
			in = new Scanner(new File(filename));
			while (in.hasNextLine()) {
				
				String line = in.nextLine();
				String[] lineItems = line.split(",");
				String date = lineItems[0].substring(0, lineItems[0].indexOf("T"));
				double fine = Double.parseDouble(lineItems[1]);
				String description = lineItems[2];
				String vehicleID = lineItems[3];
				String vehicleState = lineItems[4];
				String violationID = lineItems[5];
				int violationZip; 
				
				// if it contains a zipcode then add it to the hashmap 
				if (lineItems.length == 7) {
					violationZip = Integer.parseInt(lineItems[6]);
					// if there is a zipcode existing add the ParkingViolation to the List
					if (parkingViolations.containsKey(violationZip)) 
					{
						parkingViolations.get(violationZip).add(new ParkingViolation(
								date, fine, description, vehicleID, vehicleState, 
								violationID, violationZip));
					}
					//if there is no zipcode key then create a new List, put the zip as a key 
					//and list as its value
					else 
					{
						List<Object> myList = new ArrayList<>();
						myList.add(new ParkingViolation(date, fine, description, vehicleID, 
								vehicleState, violationID, violationZip));
						
						parkingViolations.put(violationZip, myList);
					}
				}
				
			}
		}
			catch (Exception e) {
				throw new IllegalStateException(e);
			}
			finally {
				in.close();
			}
			return parkingViolations;
		}

	
	public static void main(String[] args) {
		Map<Integer, List<Object>> test; 
		Reader r = new ParkingCSVReader("parking.csv"); 
		test = r.read(); 
		
		for (int i : test.keySet()) {
			List<Object> p = test.get(i); 
			System.out.println(i +" : "+ p.size());


}
	}
	}

