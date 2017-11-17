package traffic;

import java.util.LinkedList;

public class Highway {

	// constants and conversions
	private final double MPH75 = 1.25; // miles per minute
	private final double MPH40 = (2 / 3); // miles per minute
	private final int LENGTH = 25; // length of the highway in miles
	private final int LENGTHNORM = 20; // length of the normal highway in miles
	private final int LENGTHCON = 5; // length of the construction zone in miles

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
	public void removeFromCon() {
		try {
			
		} catch(QueueException ex) {
			
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

	public void calcPos(Car c, int currTime) {
		
	}
	
	
	
	
	// variables for calcExitTime
	private int initT; // initial time
	private int conT; // construction entry time
	private double leftOverT; // left over time
	private double leftOverP; // left over position
	private int currPos; // current position
	private int count; // counter
	
	public double calcExitTime(Car c, int currTime) {

		initT = c.getEntryTime(); //set the initial time
		conT = (int)c.getConTime(); //set the integer value of the con enter time
		leftOverT = c.getConTime() - conT; //decimal left over from con enter time's cast
		count = initT; //count variable for the maintain position in the speeds array
		
		//from the initial entry time to the construction zone, add the the distance to the position
		for (int i = initT; isInCon(currPos) == false; i++) {
			currPos += speeds[0][i]; //speeds can be added directly because the time unit is 1 minute and the speeds are given as miles per minute
			count++; //increase the counter to maintain positioning in the speed array for later
		}
		
		//since the construction zone boolean does not take into account the decimal, it must be accounted for
		currPos -= ((1-leftOverT) * speeds[0][count-1]); //remove (1 - (the decimal) ) * normSpeed 
		currPos += ((1-leftOverT)* speeds[1][count-1]); //re-add (1 - (the decimal) ) but multiply it against the proper conSpeed
		
		//from the initial construction time + 1 to the end of highway, add the distance to the position
		for(int i = count; hasExit(currPos);i++) {
			currPos += speeds[1][i]; //adds the distance for one time unit
			count++; //maintain positioning in the speed array
		}
		
		//since the exit boolean does not take into account the decimal, it must be accounted for
		c

	}

	public void check() {

	}

	public boolean isInCon(int pos) {
		if (pos >= LENGTHNORM)
			return true;
		else
			return false;

	}

	public boolean hasExit(int pos) {
		if (pos >= LENGTH)
			return true;
		else
			return false;
	}
}
