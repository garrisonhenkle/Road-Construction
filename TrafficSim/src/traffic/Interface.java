package traffic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {

	// variables
	private int max;
	private int rate;
	private int length;
	private int maxTime;
	private int finalRate;
	private int conLength;

	// objects
	private String input;

	public static void main(String[] args) {
		Interface i = new Interface();
		Simulator trafficSim = i.prompt();
		trafficSim.run(trafficSim.length);
	}

	/**
	 * Prompts for input
	 * 
	 * Prompts the user to input a max time, a rate, and a construction length, then
	 * starts the simulator with these values
	 */
	public Simulator prompt() {

		Simulator sim;

		System.out.println("Traffic Simulator");
		System.out.println("---------------------------------------------------");
		System.out.println("Max time (in minutes) should be an integer greater than or equal to 1.");
		System.out.println(
				"Rate should be an integer greater than or equal to 1. The rate is the time between each car entering the highway");
		System.out.println("Construction length (in miles) is an integer where 0 < integer < 25");
		System.out.println("---------------------------------------------------");

		System.out.println("Please enter the max time:");

		// get the max time
		do {
			// get input and parse it for integers
			input = getInput();
			max = getInt(input);

			// if the input is correct, break the loop, otherwise continue the loop
			if (max >= 1)
				break;
			else {
				System.out.println("Invalid max, please try again:");
				continue;
			}
		} while (true);

		// set max
		maxTime = max;

		System.out.println("Please enter the rate:");

		// get the rate
		do {
			// get input and parse it for integers
			input = getInput();
			rate = getInt(input);

			// if the input is correct, break the loop, otherwise continue the loop
			if (rate > 0)
				break;
			else {
				System.out.println("Invalid rate, please try again:");
				continue;
			}
		} while (true);

		// set rate
		finalRate = rate;

		System.out.println("Please enter the length:");

		// get the construction length
		do {
			// get input and parse it for integers
			input = getInput();
			length = getInt(input);

			// if the input is correct, break the loop, otherwise continue the loop
			if (0 < length && length < 25)
				break;
			else {
				System.out.println("Invalid construction length, please try again:");
				continue;
			}
		} while (true);

		// set length
		conLength = length;

		// create a new simulator to return
		sim = new Simulator(maxTime, finalRate, conLength);

		return sim;

	} // end method prompt

	/**
	 * Gets input from the user
	 * 
	 * @return in the input of the user as a String
	 */
	public String getInput() {
		// variables
		@SuppressWarnings("resource")
		Scanner userIn = new Scanner(System.in);
		String in = null;

		// loop until a valid output is received
		do {
			if (userIn.hasNextLine()) {
				in = userIn.nextLine();
				break;
			}
		} while (true);

		return in;
	} // end method getInput

	/**
	 * Parses integers from Strings
	 * 
	 * If the input is not a number or a negative number, the output will be -1. If
	 * the input is a positive, the output will be the number as an int
	 * 
	 * @param in
	 *            the String to parse
	 * @return out if out is > 0, -1 if out < 0
	 */
	public int getInt(String in) {
		int out;
		try {
			out = Integer.parseInt(in);
		} catch (NumberFormatException ex) {
			return -1;
		}
		if (out > 0)
			return out;
		else
			return -1;
	} // end method getInt
}
