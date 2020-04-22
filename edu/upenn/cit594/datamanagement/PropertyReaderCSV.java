package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.*;

public class PropertyReaderCSV {
	String filename; 
	
	PropertyReaderCSV(String file){
		filename = file;
	}
	
	public HashMap<Integer, List<Object>> readFile(){
		HashMap<Integer,List<Object>> properties = new HashMap<>();
		Scanner in = null;
		//read in properties 
		try {
			in = new Scanner(new File(filename));
			String[] headers = in.nextLine().split(",");
			in.nextLine();
			//index column location of relevant values 
			int indexOfArea = 0;
			int indexOfValue = 0;
			int indexOfZip =0;
			//get the index via a loop
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
			//split lines on commas except commas in quotes 
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] lineItems = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
				double area = Double.parseDouble(lineItems[indexOfArea]);
				double value = Double.parseDouble(lineItems[indexOfValue]);
				String zipCodeUnclean = lineItems[indexOfZip];
				// trim zip down to 5 length
				if (zipCodeUnclean.length() > 5) {
					zipCodeUnclean = zipCodeUnclean.substring(0,5);
				}
				
				int zip = Integer.parseInt(zipCodeUnclean);
				
				//create a new array if the key zip value is not present otherwise get the ArrayList stored and add new Property
				Property myProperty =  new Property(value, area, zip);
				
				if (properties.containsKey(zip)) 
				{
					properties.get(zip).add(myProperty);
				}
				else 
				{
					ArrayList<Object> propertyList = new ArrayList<>();
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
	//testing if it works 
	public static void main(String[] args) {
		PropertyReaderCSV reader = new PropertyReaderCSV("PropertiesSmall.csv");
		HashMap<Integer, List<Object>> test = reader.readFile();
		for (int i : test.keySet()) {
			for (Object  o : test.get(i)) {
				Property p = (Property) o;
				System.out.println(p.getZipCode()+":"+p.getMarketValue()+":"+p.getLivableArea());
			}
		}
		
	}

}
