package traffic;

import java.util.LinkedList;

public class Highway {

	// constants and conversions
	private final double MPH75 = 1.25; // miles per minute
	private final double MPH40 = (2 / 3); // miles per minute
	private final int LENGTH = 132000; // length of the highway in feet
	private final int LENGTHNORM = 105600; // length of the normal highway in feet
	private final int LENGTHCON = 26400; // length of the construction zone in feet

	// objects
	private CarQueue con; // construction queue
	private CarQueue norm; // normal queue

	// variables

	private int t = 0; // internal count variable for the speed

	protected int carLength = 100; // amount of space a car requires on the road in feet
	private int size = LENGTH / carLength; // size of the queues (max number of cars on the road at once with given
											// parameters

	private double conSpeed; // current speed in construction zone
	private double normSpeed; // current speed in the normal zone
	protected double speeds[][]; // speed history

	public Highway(int time) {
		speeds = new double[2][time]; // norm is row 0, con is row 1
		con = new CarQueue(size);
		norm = new CarQueue(size);
	}

	public void addCar(int timeIn) {
		if (norm.isFull() == false) {
			Car c = new Car(timeIn);
			try {
				norm.enqueue(c);
			} catch (QueueException ex) {
				System.out.println("Car cannot enter the road, the highway is full.");
			}
		}
	}

	public void moveToCon(Car c) {
		try {
			con.enqueue(norm.dequeue());
		} catch (QueueException ex) {
			System.out.println("Car cannot enter the construction, the construction zone is full");
		}
	}

	public void calcSpeed() {
		if (norm.isPercent(90) == true)
			normSpeed = MPH75 * .25;
		else if (norm.isPercent(75) == true)
			normSpeed = MPH75 * .50;
		else if (norm.isPercent(50) == true)
			normSpeed = MPH75 * .75;
		else
			normSpeed = MPH75;

		if (con.isPercent(90) == true)
			conSpeed = MPH40 * .25;
		else if (con.isPercent(75) == true)
			conSpeed = MPH40 * .50;
		else if (con.isPercent(50) == true)
			conSpeed = MPH40 * .75;
		else
			conSpeed = MPH40;

		speeds[0][t] = normSpeed;
		speeds[1][t++] = conSpeed;
	}

	// variables for calcPos
	private int initT; // initial time
	private int currPos; // current position

	public void calcPos(Car c, int currTime) {

		initT = c.getEntryTime();

		for (int i = initT; i < currTime; i++) {
			
		}

		c.setPos();

	}

	public boolean isInCon(int pos) {
		if(pos >= )
	}

	public boolean isInCon()
}
