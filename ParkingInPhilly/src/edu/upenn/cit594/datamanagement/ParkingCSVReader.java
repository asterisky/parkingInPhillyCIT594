import java.io.File;
import java.util.*;

public class ParkingCSVReader implements Reader {
	protected String filename; 
	
	ParkingCSVReader (String s){
		filename = s; 
	}

	@Override
	public List<ParkingViolation> readData() {
		List<ParkingViolation> parkingViolations = new ArrayList<ParkingViolation>(); 
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
				String violationZip = null; 
				
				if (lineItems.length == 7) {
					violationZip = lineItems[6];
				}
				parkingViolations.add(new ParkingViolation(
						date,fine, description, vehicleID, vehicleState, violationID, violationZip, violationZip));
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
		List<ParkingViolation> test; 
		Reader r = new ParkingCSVReader("parking.csv"); 
		test = r.readData(); 
		
		for (ParkingViolation p : test) {
			System.out.println(p.getDescription());
		}
	}
	}


//hello test 2


