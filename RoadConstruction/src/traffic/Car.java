package traffic;

public class Car {

	private int pos;
	private int prevPos;
	private int EntryTime;
	private int speed;
	private int prevSpeed;

	public Car(int time) {
		EntryTime = time;
		speed = 0;
		pos = 0;
	}

	public void advPos(int distance) {
		prevPos = pos;
		pos += distance;
	}
	
	public void setSpeed(int speedIn) {
		prevSpeed = speed;
		speed = speedIn;
	}
	
	public int getEntryTime() {
		return EntryTime;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getPrevPos() {
		return prevPos;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getPrevSpeed() {
		return prevSpeed;
	}

}
