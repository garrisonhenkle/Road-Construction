package traffic;

public class Car {

	private double pos;
	private int entryTime;
	private double conTime;
	private double removalTime;

	public Car(int time) {
		entryTime = time;
		pos = 0;
		
	}
	
	public void setConTime(int time) {
		conTime = time;
	}
	
	public void setRemovalTime(int time) {
		removalTime = time;
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
	
	public double getRemovalTime() {
		return removalTime;
	}
	
	public double getPos() {
		return pos;
	}
}
