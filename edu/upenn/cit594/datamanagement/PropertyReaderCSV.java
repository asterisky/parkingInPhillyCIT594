package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.*;

public class PropertyReaderCSV {
	String filename; 
	
	PropertyReaderCSV(String file){
		filename = file;
	}
	
	public HashMap<Integer, List<Property>> readFile(){
		HashMap<Integer,List<Property>> properties = new HashMap<>();
		Scanner in = null;
		try {
			in = new Scanner(new File(filename));
			String[] headers = in.nextLine().split(",");
			in.nextLine();
			int indexOfArea = 0;
			int indexOfValue = 0;
			int indexOfZip =0;
			
			for (int i =0; i<headers.length-1; i++) {
				String s = headers[i];
				if (s.contentEquals("total_livable_area")) {
					indexOfArea = i;
				}
				if (s.contentEquals("zip_code")) {
					indexOfZip =i;
				}
				if (s.contentEquals("market_value")) {
					indexOfValue =i;
				}
			}
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] lineItems = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
				double area = Double.parseDouble(lineItems[indexOfArea]);
				double value = Double.parseDouble(lineItems[indexOfValue]);
				String zipCodeUnclean = lineItems[indexOfZip];
				if (zipCodeUnclean.length() > 5) {
					zipCodeUnclean = zipCodeUnclean.substring(0,5);
				}
				
				int zip = Integer.parseInt(zipCodeUnclean);
				
				Property myProperty =  new Property(value, area, zip);
				
				if (properties.containsKey(zip)) 
				{
					properties.get(zip).add(myProperty);
				}
				else 
				{
					ArrayList<Property> propertyList = new ArrayList<>();
					propertyList.add(myProperty);
					properties.put(zip, propertyList);
				}
				
			}
			
			return properties;
		}
			catch (Exception e) {
				throw new IllegalStateException(e);
			}
			finally {
				in.close();
			}
	}
	
	public static void main(String[] args) {
		PropertyReaderCSV reader = new PropertyReaderCSV("PropertiesSmall.csv");
		HashMap<Integer, List<Property>> test = reader.readFile();
		for (int i : test.keySet()) {
			for (Property p : test.get(i)) {
				System.out.println(p.getZipCode());
			}
		}
		
	}

}
