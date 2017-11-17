package traffic;

/**
 * Contains the object Car and its constructors and methods
 * 
 * Car consists of 4 main fields: pos (position), the time of entry (entryTime),
 * the time of entry to the construction zone (conTime), and the time of exit
 * from the highway (endTime).
 * 
 * @author Garrison Henkle
 * @since 1.8
 */
public class Car {

	private double pos; // position of the car
	private int entryTime; // time at creation
	private double conTime; // time the car enters the construction zone
	private double EndTime; // time the car exits the highway
	protected boolean conFlag = false; // flag to indicate if it has left normal
	protected boolean endFlag = true; // flag to indicate if it has left the highway

	/**
	 * Constructor with a time parameter
	 * 
	 * @param time
	 *            current simulated time in minutes
	 */
	public Car(int time) {
		entryTime = time;
		pos = 0;
	} // end constructor car(int)

	/**
	 * Sets the construction flag to true
	 * 
	 * Indicates that the car is in the construction zone
	 */
	public void setCFlag() {
		conFlag = true;
	} // end methods setCFlag

	/**
	 * Sets the end flag to true
	 * 
	 * Indicates that the car has left the 25 mile stretch of simulated highway
	 */
	public void setEFlag() {
		endFlag = true;
	} // end method setEFlag

	/**
	 * Determines whether the car is in the construction zone
	 * 
	 * @return true if the car is in the construction zone, false otherwise
	 */
	public boolean isInCon() {
		if (conFlag == true)
			return true;
		else
			return false;
	} // end method isInCon

	/**
	 * Determines whether the car is on the highway
	 * 
	 * @return true is the car is on the highway, false otherwise
	 */
	public boolean hasLeft() {
		if (endFlag == true)
			return true;
		else
			return false;
	} // end method hasLeft

	/**
	 * Advances the position of the car
	 * 
	 * @param distance
	 *            the distance by which to advance the car
	 */
	public void advPos(double distance) {
		pos += distance;
	} // end method advPos

	/**
	 * Sets the time at which the car entered the construction zone
	 * 
	 * @param time
	 *            time to set conTime
	 */
	public void setConTime(double time) {
		conTime = time;
	} // end method setConTime

	/**
	 * Sets the time at which the car left the highway
	 * 
	 * @param time
	 *            time to set endTime
	 */
	public void setEndTime(double time) {
		EndTime = time;
	} // end method setEndTime

	/**
	 * Sets the position of the car
	 * 
	 * @param distance
	 *            the position at which to set the car
	 */
	public void setPos(double distance) {
		pos = distance;
	} // end method setPos

	/**
	 * Returns the entry time of the car
	 * 
	 * @return entryTime the entry time
	 */
	public int getEntryTime() {
		return entryTime;
	} // end method getEntryTime

	/**
	 * Returns the construction time of the car
	 * 
	 * @return conTime the construction time
	 */
	public double getConTime() {
		return conTime;
	} // end method getConTime

	/**
	 * Returns the end time of the car
	 * 
	 * @return EndTime the end time
	 */
	public double getEndTime() {
		return EndTime;
	} // end method getEndTime

	/**
	 * Returns the current position of the car
	 * 
	 * @return pos the position of the car
	 */
	public double getPos() {
		return pos;
	} // end method getPos
}
