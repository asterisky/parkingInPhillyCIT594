package edu.upenn.cit594.datamanagement;
import java.io.File;
import java.util.*;

public class ParkingCSVReader implements Reader {
	protected String filename; 
	
	ParkingCSVReader (String s){
		filename = s; 
	}

	public Map<Integer, ParkingViolation[]> readFile() {
		HashMap<Integer, ParkingViolation> parkingViolations = new HashMap<>(); 
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
				
				if (lineItems.length == 7) {
					violationZip = Integer.parseInt(lineItems[6]);
				}
				
				if parkingViolations.containsKey(violationZip)
				parkingViolations.add(new ParkingViolation(date, fine, description,
						vehicleID, vehicleState, violationID, violationZip));
				
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
		Map<Integer, Violation[]> test; 
		Reader r = new ParkingCSVReader("parking.csv"); 
		test = r.readFile(); 
		
		for (ParkingViolation p : test) {
			System.out.println(p.getDescription());


}
	}
	}

