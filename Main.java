import edu.upenn.cit594.datamanagement.Processor;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.UserInterface;
import edu.upenn.cit594.logging.Logger;

public class Main {
	public static void main(String[] args) {
		Logger.getLogger().startLogFile(args);
		//if length of arguments is incorrect
		if (args.length < 5) {
			System.out.println("not enough arguments");
			System.exit(0);
			}
		
		//if neither csv or json
		if (!args[0].contentEquals("csv") || !args[0].contentEquals("json")) {
			System.exit(0);
		}
		
	
		
		//implement if file is unreadable*****
		//CODE HERE
		
		//set variables
		String fileFormat = args[0];
		String parkingVioloationFile = args[1];	
		String propertyValuesFile = args [2];
		String populationFile = args[3];
		String logFileName = args[4];
		
		//How do i read in the file processor or reader class?
		UserInterface ui = new UserInterface(null);
		
		
		//Fill in all the items*****
		//CODE HERE
		
		Logger.getLogger().closeLog();
	}

}
