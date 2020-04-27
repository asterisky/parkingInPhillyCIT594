package edu.upenn.cit594.datamanagement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

import edu.upenn.cit594.Processor.*;
import edu.upenn.cit594.logging.Logger;

public class UserInterface {
	// Analysis class only ----
	protected Analysis a = new Analysis();
	protected Scanner s = new Scanner(System.in);
	protected HashMap<Integer, List<Object>> properties;
	protected HashMap<Integer, List<Object>> parkingViolations;
	protected HashMap<Integer, List<Object>> populations;

	public UserInterface(Map<Integer, List<Object>> propertyData,
			Map<Integer, List<Object>> parkingViolationData, Map<Integer, List<Object>> populationData) {
		this.properties = (HashMap<Integer, List<Object>>) propertyData;
		this.parkingViolations = (HashMap<Integer, List<Object>>) parkingViolationData;
		this.populations = (HashMap<Integer, List<Object>>) populationData;
	}

	public void run() {
			
			displayMenu(); 
			//if not a number
			if (!s.hasNextInt()) {
				System.out.println("Integer between 0-6 not entered");
				System.exit(0);
			}
			//if spaces or multiple numbers
			String userInput = s.nextLine();
			Logger.getLogger().log(userInput);
			if (userInput.length() != 1) {
				System.out.println("Invalid integer format, please enter an intger from 0-6");
				System.exit(0);
			}
				
			int input = Integer.parseInt(userInput);
			
			// continue the ui if an integer was properly selected
		
				if (input==0){
					System.exit(0);
				}
				
				if (input==1){
					System.out.println(a.totalPopulationByZip(populations));
				}
				if (input==2){
					Map<Integer, Double> finesPerCap = a.totalFinesPerCapita(parkingViolations, populations);
					//truncate to 4 digits after the decimal
					for (Integer i : finesPerCap.keySet()) {
						BigDecimal truncated = new BigDecimal(finesPerCap.get(i));
						truncated = truncated.setScale(4, RoundingMode.FLOOR);

						System.out.println(i + " " + truncated);
					}
				}
				if (input==3){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt();
					Logger.getLogger().log(inputZip);

//					System.out.println(pp.averageValue(inputZip)); 
				}
				if (input==4){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt(); 
					Logger.getLogger().log(inputZip);

//					System.out.println(pp.averageLivableArea(inputZip)); 
				}
				if (input==5){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt();
					Logger.getLogger().log(inputZip);

					System.out.println(a.totalMarketValForZip(inputZip, properties, populations));
				}
				if (input==6){
					//Aman method
				}
		}

	private void displayMenu() {
		System.out.println("Select an option from 0 - 6");
		System.out.println("0: Exits Program \n" + "1: Total Population for all Zip Codes \n"
				+ "2: Total Fines per Capita for each Zip Code \n"
				+ "3: Average Market Value of Residences for a Specific Zip Code \n"
				+ "4: Average Total Livable Area of Residences for a Specific Zip Code \n"
				+ "5: Total Residential Market Value per Capita for a Specific Zip Code \n" + "6: Custom question"); // UPDATE
																														// THE
																														// QUESTION
	}

	public static void main(String[] args) {
		PropertyReaderCSV reader = new PropertyReaderCSV("PropertiesSmall.csv");
		WStxtProcessor pop = new WStxtProcessor("/Users/quetzalcoatl/Downloads/population.txt");
		CSVProcessor parking = new CSVProcessor("parking.csv");
		HashMap<Integer, List<Object>> chek = new HashMap<>();
		
		UserInterface ui = new UserInterface(chek, parking.readFile(), pop.readFile());
		ui.run();

	}

}
