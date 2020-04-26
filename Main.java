import edu.upenn.cit594.datamanagement.*;

public class Main {
	public static void main(String[] args) {
		//if length of arguments is incorrect
		if (args.length < 5) {
			System.out.println("not enough arguments");
			System.exit(0);
			}
		
		//if neither csv or json
		if (!args[0].contentEquals("csv") && !args[0].contentEquals("json")) {
			System.out.println("its neither");
			System.exit(0);
		}
	
		
		//implement if file is unreadable*****
		String fileFormat = args[0];
		String parkingViolationFile = args[1];	
		String propertyValuesFile = args [2];
		String populationFile = args[3];
		String logFileName = args[4];
		
		Processor parkingViolation = new CSVProcessor(parkingViolationFile);
		Processor population = new WStxtProcessor(populationFile);
		Processor properties = new PropertyProcessor(propertyValuesFile);
		
		UserInterface ui = new UserInterface(parkingViolation.readFile(), population.readFile(), properties.readFile());
		
		//***** Getting Error on second user input?? ******
		while (true) {
			ui.run();
			System.out.println("********************************************************************");
		}
		
		
		
		//Fill in all the items*****
		//CODE HERE
		
		
	}

}
