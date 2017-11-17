package traffic;

public class Car {

	private double pos; // position of the car
	private int entryTime; // time at creation
	private double conTime; // time the car enters the construction zone
	private double EndTime; // time the car exits the highway
	protected boolean conFlag = false; // flag to indicate if it has left normal
	protected boolean endFlag = true; // flag to indicate if it has left the highway

	public Car(int time) {
		entryTime = time;
		pos = 0;
	}

	public void setCFlag() {
		conFlag = true;
	}

	public void setEFlag() {
		endFlag = true;
	}

	public boolean isInCon() {
		if (conFlag == true)
			return true;
		else
			return false;
	}

	public boolean hasLeft() {
		if (endFlag == true)
			return true;
		else
			return false;
	}

	public void advPos(double distance) {
		pos += distance;
	}

	public void setConTime(double time) {
		conTime = time;
	}

	public void setEndTime(double time) {
		EndTime = time;
	}

	public void setPos(double distance) {
		pos = distance;
	}

	public int getEntryTime() {
		return entryTime;
	}

	public double getConTime() {
		return conTime;
	}

	public double getEndTime() {
		return EndTime;
	}

	public double getPos() {
		return pos;
	}
}
