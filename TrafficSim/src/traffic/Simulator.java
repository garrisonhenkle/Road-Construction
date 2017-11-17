package traffic;

public class Simulator {

	private int time; // holds the simulated time in minutes
	private int end; // holds the termination time for the simulator in minutes
	private int rate; // the rate at which cars are added to the simulation
	private int numOutputs; // the number of outputs created by the simulator
	protected int length; // length of the construction zone
	private int count = 0; // counter variable to increment through the outputs array
	private double[] outputs; // contains the outputs of the simulation

	/**
	 * Constructor with a maxTime and rate parameter
	 * 
	 * Initializes all the necessary variables to run the simulator
	 * 
	 * @param maxTime
	 *            amount of time (in simulated minutes) that the simulation will run
	 * @param rate
	 *            the frequency at which cars will enter the simulation, given as an
	 *            integer from 1 to Integer.MAX_VALUE
	 */
	public Simulator(int maxTime, int rate, int conLength) {
		time = 0;
		end = maxTime;
		this.rate = rate;
		numOutputs = (int) (maxTime / rate);
		outputs = new double[numOutputs];
		length = conLength;
	} // end constructor Simulator(int, int)

	/**
	 * Runs a time driven simulation using the variables from the constructor
	 * 
	 * Loops through end times to find the average time it takes for a car to
	 * complete the 25 mile stretch of highway
	 */
	public void run(int length) {

		Highway h = new Highway(length);

		// while the time is less than the termination time
		while (time < end) {
			// if the time is divisible by the rate, add a car to the simulation
			if (time % rate == 0)
				h.add(time);
			// update the position for the cars in both queues
			h.updatePos(time, 'c');
			h.updatePos(time, 'n');
			// mark the cars that have gone into the construction zone and transfer them to
			// the other queue
			h.markCon(time);
			if (h.hasEntered())
				h.transfer();
			// mark the cars that have completed the 25 mile stretch and remove them from
			// the queue, then calculate the time elapsed over the stretch of highway
			h.markEnd(time);
			if (h.hasEnded()) 
				calculate(h.remove());

			// increment time
			time++;
		}

		System.out.println("The average time was " + getAverage());
	} // end method run

	/**
	 * Calculates the elapsed time for a car and stores the data in outputs
	 * 
	 * @param c
	 *            Car for which an elapsed time will be found
	 */
	public void calculate(Car c) {

		// compute the elapsed time
		double timeElapsed = c.getEndTime() - c.getEntryTime();
			
		// add the resulting time to the outputs array and increase the count variable
		outputs[count++] = timeElapsed;
		

	} // end method calculator

	/**
	 * Returns the average time
	 * 
	 * @return average the average time it took for a car to complete the 25 miles
	 */
	public double getAverage() {

		double sum = 0;
		// compute the sum
		for (double d : outputs) {
			sum += d;
			System.out.println("d: " + d);
		}

		// compute the average
		System.out.println(outputs.length);
		double average = sum / outputs.length;

		return average;

	} // end method getAverage
}
