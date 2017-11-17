package traffic;

public class Simulator {

	private int time;
	private int end;
	private int rate;
	private int numOutputs;

	/**
	 * Constructor with a maxTime and rate parameter
	 * 
	 * @param maxTime
	 *            amount of time (in simulated minutes) that the simulation will run
	 * @param rate
	 *            the frequency at which cars will enter the simulation, given as an
	 *            integer from 1 to Integer.MAX_VALUE
	 */
	public Simulator(int maxTime, int rate) {
		time = 0;
		end = maxTime;
		this.rate = rate;
		numOutputs = (int)(maxTime/rate);
	} // end constructor Simulator(int, int)

	/**
	 * Runs a simulation using the variables from the constructor
	 */
	public void run() {

		Highway h = new Highway();

		while (time < end) {
			if (time % rate == 0) {
				h.add();
			}
			h.updatePos(time, 'c');
			h.updatePos(time, 'n');
			h.markCon(time);
			h.transfer();
			h.markEnd(time);
			calculate(h.remove());

			time++;
		}
	} // end method run
	
	public void calculate(Car c) {
		double timeElapsed = c.getEndTime() - c.getEntryTime();
		
	}
}
