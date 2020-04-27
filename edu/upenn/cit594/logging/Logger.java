/**
 * 
 */
package edu.upenn.cit594.logging;

import java.io.*;

/**
 * @author Aman Nischal and Katie Pizziketti Record user inputs and activities
 *         by writing to the log file that was specified as a runtime argument.
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

	/**
	 * Start the log file (from main with runtime args)
	 * 
	 * @param args
	 */
	public void startLogFile(String[] args) {
		if(args.length < 5) {
			int numArgs = args.length;
			for(int i = numArgs - 5; i > 0; i--) {
				args[i + numArgs] = ""; 
			}
		}
		filename = new File(args[4]);
		try {
			pw = new PrintWriter(filename);

			long time = System.currentTimeMillis();

			pw.println(time + " " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}

	/**
	 * when file is opened for reading, pass in the filename as string
	 * 
	 * @param file
	 */
	public void logOpenFile(String file) {
		try {

			long time = System.currentTimeMillis();

			pw.println(time + " " + file);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}

	/**
	 * takes user input string - selection or zip
	 * 
	 * @param input
	 */
	public void log(String input) {
		try {

			long time = System.currentTimeMillis();

			pw.println(time + " " + input);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}
	/**
	 * Overloaded to handle ints too via the power of concatenation!
	 * @param input
	 */
	public void log(int input) {
		try {

			long time = System.currentTimeMillis();

			pw.println(time + " " + input);
			pw.flush();
		}

		catch (Exception e) {
			System.out.println("There was a problem with processing the log file");
		}
	}

	/**
	 * please run this at the end of program to close the printwriter
	 */
	public void closeLog() {
		pw.close();
	}
}
