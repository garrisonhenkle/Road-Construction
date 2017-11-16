package extra;

import traffic.CarQueue;

public class Lane {
	
	//variables
	private int size;
	
	//objects
	private CarQueue q;
	
	// constants
	final int HWYL = 132000; // length of the highway in feet (25 mi = 132,000 ft)
	final int CAR_DIST = 100; // length a car needs on the highway in feet
	final int LANE_CAP = HWYL / CAR_DIST; // amount of cars one lane can hold if each car gets 100 ft

	public Lane(int size) {
		this.size = size;
		q = new CarQueue(size);
	}
	
	public boolean canEnter() {
		if(q.getSize() < size)
			return true;
		else
			return false;
	}
}
