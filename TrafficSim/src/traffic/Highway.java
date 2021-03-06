package traffic;

/**
 * Contains methods to store and manipulate data in the highway structure
 * 
 * Uses queues to maintain the positions of cars in the road
 * 
 * @author Garrison Henkle
 * @since 1.8
 *
 */
public class Highway {
	// constants
	private final double MPH75 = 1.25; // miles per minute
	private final double MPH40 = (2 / 3); // miles per minute
	private final int LGTH = 25; // length of the highway in miles

	// objects
	private CarQueue con; // construction queue
	private CarQueue norm; // normal queue
	CarQueue q = null; // temporary pointer

	// variables
	private int LGTHN; // length of the normal highway in miles
	private int LGTHC; // length of the construction zone in miles
	private double cSpeed; // construction speed
	private double nSpeed; // normal speed
	private double speed; // active speed
	private double tempPos; // temporary position holder
	private double tempTime; // temporary time holder
	protected int carLength = 100; // space a car needs in feet
	private int sizeN; // max amount of cars that can fit in norm (base on carLength)
	private int sizeC; // max amount of cars that can fit in con ("" "")

	/**
	 * Constructor with no parameters
	 */
	public Highway(int constructionLength) {
		// set the length of the construction zone
		LGTHC = constructionLength;
		// set the length of the normal zone
		LGTHN = LGTH - LGTHC;
		// set the greatest size of the queues
		sizeN = 1000;
		sizeC = 1000;
		// create the queues
		con = new CarQueue(sizeC);
		norm = new CarQueue(sizeN);

	} // end constructor Highway

	/**
	 * Calculates the current speed of the cars in the normal queue
	 */
	public void calcNSpeed() {
		if (norm.isPercent(90) == true)
			nSpeed = MPH75 * .25;
		else if (norm.isPercent(75) == true)
			nSpeed = MPH75 * .50;
		else if (norm.isPercent(50) == true)
			nSpeed = MPH75 * .75;
		else
			nSpeed = MPH75;
	} // end method calcNSpeed

	/**
	 * Calculates the current speed of the cars in the construction queue
	 */
	public void calcCSpeed() {
		if (con.isPercent(90) == true)
			cSpeed = MPH40 * .25;
		else if (con.isPercent(75) == true)
			cSpeed = MPH40 * .50;
		else if (con.isPercent(50) == true)
			cSpeed = MPH40 * .75;
		else
			cSpeed = MPH40;
	} // end method calcCSpeed

	/**
	 * Returns an array with the data of the selected queue
	 * 
	 * @param c
	 *            Selects the queue. Can be construction ('c') or normal ('n)
	 * @return out array of Cars
	 */
	private Car[] retrieveCars(char c) {

		// set mode
		if (c == 'c')
			q = con;
		if (c == 'n')
			q = norm;

		// create output array
		Car[] out = new Car[q.getSize()];

		// create a size variable since the .getSize() return will shrink
		int size = q.getSize();

		// move all data from queue to array
		for (int i = 0; i < size; i++) {
			try {
				out[i] = q.dequeue();
			} catch (QueueException ex) {
				System.out.println("Unable to retrieve the data");
			}
		}
		return out;
	} // end method retrieveCars

	/**
	 * Moves an array into a specified queue
	 * 
	 * @param cars
	 *            the array to be transfered
	 * @param c
	 *            selects the queue. Can be construction ('c') or normal ('n')
	 */
	private void restoreCars(Car[] cars, char c) {

		// set mode
		if (c == 'c')
			q = con;
		if (c == 'n')
			q = norm;

		// move all data from array to queue
		for (int i = 0; i < cars.length; i++) {
			try {
				q.enqueue(cars[i]);
			} catch (QueueException ex) {
				System.out.println("Unable to restore the data");
			}
		}
	} // end method restoreCars

	/**
	 * Checks if the car has entered the construction zone, then repairs the data
	 * 
	 * The conTime is set and the proper pos is given to the car.
	 * 
	 * @param currTime
	 *            Indicates the current time in the simulation.
	 */
	public void markCon(int currTime) {

		Car[] cars = retrieveCars('n');

		for (Car c : cars) {
			// if it exceeds the length and has not been processed
			if (c.getPos() >= LGTHN && !c.isInCon()) {

				// set the flag
				c.setCFlag();

				// get the distance it has traveled in the construction zone
				tempPos = c.getPos() - LGTHN;

				// get the decimal of the conTime value --> speed = miles/minutes, so minutes =
				// miles/speed
				tempTime = tempPos / nSpeed;

				// sets the conTime of the car
				c.setConTime((currTime - 1) + tempTime);

				// advance the proper amount of miles
				c.advPos(cSpeed);

			}
		}

	} // end method markCon

	/**
	 * Checks if the car has left the simulation, then repairs the data
	 * 
	 * The endTime is set
	 * 
	 * @param currTime
	 *            Indicates the current time in the simulation.
	 */
	public void markEnd(int currTime) {

		Car[] cars = retrieveCars('c');

		for (Car c : cars) {
			// if it exceeds the length and has not been processed
			if (c.getPos() >= LGTH && !c.hasLeft()) {

				// set the flag
				c.setEFlag();

				// get the extra distance it has traveled
				tempPos = c.getPos() - LGTH;

				// get the decimal of the conTime value --> speed = miles/minutes, so minutes =
				// miles/speed
				tempTime = tempPos / cSpeed;

				// sets the endTime of the car
				c.setEndTime((currTime - 1) + tempTime);

			}
		}

	} // end method markEnd

	/**
	 * Updates the positions and marks cars on the highway
	 * 
	 * Cars are marked if they have entered the construction zone or if they have
	 * reached the end of the highway
	 * 
	 * @param currTime
	 *            gives the current time of the simulation (in minutes)
	 * @param c
	 *            character that indicates the current mode. Either c (construction)
	 *            or n (normal).
	 */
	public void updatePos(int currTime, char c) {

		// calculate the current speeds
		calcCSpeed();
		calcNSpeed();

		// set the mode
		if (c == 'c') {
			q = con;
			speed = cSpeed;
		} else if (c == 'n') {
			q = norm;
			speed = nSpeed;
		}

		// retrieve the data
		Car[] data = retrieveCars(c);

		for (int i = 0; i < data.length; i++) {
			data[i].advPos(speed);
		}

		restoreCars(data, c);
	} // end method updatePos

	/**
	 * Transfers a car from normal road to the construction zone
	 */
	public void transfer() {
		try {
			con.enqueue(norm.dequeue());
		} catch (QueueException ex) {
			System.out.println("Unable to move the car between the normal and construction zones.");
		}
	} // end method transfer

	/**
	 * Adds a car with initial time <time> to the beginning of the road (normal
	 * road)
	 * 
	 * @param time
	 *            the initial starting time of the car
	 */
	public void add(int time) {
		Car c = new Car(time);
		try {
			norm.enqueue(c);
		} catch (QueueException ex) {
			System.out.println("Unable to add car to the highway.  Insufficient amount of space.");

		}
	} // end method add

	/**
	 * Removes a car from the end of the road (construction zone)
	 * 
	 * @return Car the Car removed from the road
	 */
	public Car remove() {
		try {
			return con.dequeue();
		} catch (QueueException ex) {
			System.out.println("Unable to remove car from the highway.");
		}
		return null;
	} // end method remove

	/**
	 * Determines whether a car has entered the construction zone
	 * 
	 * @return true if the car is in the construction zone, false otherwise
	 */
	public boolean hasEntered() {
		if (norm.peek() != null) {
			if (norm.peek().isInCon())
				return true;
			else
				return false;
		}
		return false;

	} // end method hasEntered

	/**
	 * Determines whether a car has left the highway (left the construction zone)
	 * 
	 * @return true if the car has finished the stretch or highway, false otherwise
	 */
	public boolean hasEnded() {
		if (con.peek() != null) {
			if (con.peek().hasLeft())
				return true;
			else
				return false;
		}
		return false;
	}

} // end class Highway
