package edu.upenn.cit594.datamanagement;

import java.util.*;

import edu.upenn.cit594.Processor.Analysis;

public class UserInterface {
	HashMap<Integer, List<Object>> parkingData, populationData,propertyData;
	Analysis a;
	Scanner s; 
	
	public UserInterface(HashMap<Integer, List<Object>> parkingData, HashMap<Integer, List<Object>> populationData , 
			HashMap<Integer, List<Object>> propertyData){
		
		this.propertyData = propertyData;
		this.populationData = populationData;
		this.parkingData = parkingData;
		a = new Analysis();
		
		
	}
		
		public void run() {
			
			displayMenu(); 
			s = new Scanner(System.in);
			//if not a number
			if (!s.hasNextInt()) {
				System.out.println("Integer between 0-6 not entered");
				System.exit(0);
			}
			//if spaces or multiple numbers
			String userInput = s.nextLine();
			System.out.println(userInput);
			
			if (userInput.length() != 1) {
				System.out.println("Invalid integer format, please enter an intger from 0-6");
				System.exit(0);
			}
				
			int input = Integer.parseInt(userInput);
			
			if (input != 0 && input != 1 && input != 2 && input != 3 && input != 4 && 
					input != 5 && input != 6) {
				System.out.println("Integer between 0-6 not entered");
				System.exit(0);
			}
			
			
			// continue the ui if an integer was properly selected
		
				if (input==0){
					System.exit(0);
				}
				
				if (input==1){
					//Katie method
				}
				if (input==2){
					//Katie method
				}
				if (input==3){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt(); 
					System.out.println(a.averageValue(propertyData, inputZip)); 
				}
				if (input==4){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt(); 
					System.out.println(a.averageLivableArea(propertyData, inputZip)); 
				}
				if (input==5){
					//Katie method
				}
				if (input==6){
					System.out.println("Enter Zip Code");
					int inputZip = s.nextInt(); 
					double answer = a.averageFinesPerCapitaPerAveragePropertyValueRatio(propertyData, 
							(TreeMap<Integer, Double>) a.totalFinesPerCapita(parkingData, populationData), inputZip);
					System.out.println(answer);
				}
		}
		
		private void displayMenu() {
			System.out.println("Select an option from 0 - 6");
			System.out.println("0: Exits Program \n"
					+ "1: Total Population for all Zip Codes \n"
					+ "2: Total Fines per Capita for each Zip Code \n"
					+ "3: Average Market Value of Residences for a Specific Zip Code \n"
					+ "4: Average Total Livable Area of Residences for a Specific Zip Code \n"
					+ "5: Total Residential Market Value per Capita for a Specific Zip Code \n"
					+ "6: Ratio of Average Fines per Capita to Average Property Values for a specific Zip Code");
		}

}
