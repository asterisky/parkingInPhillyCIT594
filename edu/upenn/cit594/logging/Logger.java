/**
 * 
 */
package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;
import java.util.Set;

import edu.upenn.cit594.datamanagement.FluTweet;

/**
 * @author Aman Nischal and Katie Pizziketti
 * Record user inputs and activities by writing to the log file that was specified as a runtime argument.
 *
 */
public class Logger {

	private static File filename;
	protected PrintWriter pw;
	
	// private constructor for singleton pattern
	private Logger() {
	}

	// private instance of one Logger
	private static Logger instance = new Logger();

	// public accessor for the Logger instance
	public static Logger getLogger() {
		return instance;
	}

	// start the log file
	public void initLogFileName(String[] args) {
		filename = new File(args[4]);
		try {
			pw = new PrintWriter(filename);

			// take the output file from the user and store as string
			long time = System.currentTimeMillis();

			pw.println(time + " " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}
	
	//run when opening a file for reading
	public void logOpenFile(String file) {
		try {
		
			// take the output file from the user and store as string
			long time = System.currentTimeMillis();

			pw.println(time + " " + filename);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}

	//log user inputs
	public void startLog(String input) {
	}
	
	//close the logger's printwriter when done with the file
	public void closeLog() {
		pw.close();
	}
}
