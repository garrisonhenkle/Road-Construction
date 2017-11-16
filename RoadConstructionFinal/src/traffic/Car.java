package traffic;

public class Car {

	private double pos;
	private int entryTime;
	@SuppressWarnings("unused")
	private int conTime;

	public Car(int time) {
		entryTime = time;
		pos = 0;
		
	}

	public void setPos(double distance) {
		pos = distance;
	}
	
	public void setConTime(int time) {
		conTime = time;
	}
	
	public int getEntryTime() {
		return entryTime;
	}
	
	public double getPos() {
		return pos;
	}
}
