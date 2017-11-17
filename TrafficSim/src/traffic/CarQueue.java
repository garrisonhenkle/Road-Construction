package traffic;

/**
 * Contains a circular based queue to hold Car objects and methods for accessing
 * the data
 * 
 * @author Garrison Henkle
 * @since 1.8
 */
public class CarQueue {

	// variables
	private int numItems;
	private int maxItems;
	private int front;
	private int back;

	// objects
	private Car returnVal;
	private Car[] queueItems;

	/**
	 * Constructor with a size parameter
	 * 
	 * @param size
	 *            size of the queue
	 */
	public CarQueue(int size) {
		numItems = 0;
		maxItems = size;
		front = 0;
		back = 0;
		if (size > 0)
			queueItems = new Car[maxItems];
	} // end constructor CarQueue(int)

	/**
	 * Adds an item to the back of the queue
	 * 
	 * @param c
	 *            Car to be inserted into the queue
	 * @throws QueueException
	 *             exception thrown if the queue is full
	 */
	public void enqueue(Car c) throws QueueException {
		if (numItems < maxItems) {
			queueItems[back] = c;
			back = (back + 1) % maxItems;
			numItems++;
		} else
			throw new QueueException("Cannot enqueue item. The queue is full.");
	} // end method enqueue

	/**
	 * Removes an item from the front of the queue
	 * 
	 * @return returnVal Car that was removed from the queue
	 * @throws QueueException
	 *             exception thrown if the queue is empty
	 */
	public Car dequeue() throws QueueException {
		if (numItems > 0) {
			returnVal = queueItems[front];
			queueItems[front] = null;
			front = (front + 1) % maxItems;
			numItems--;
			return returnVal;
		} else
			throw new QueueException("Cannot dequeue item. The queue is empty.");
	} // end method dequeue

	/**
	 * Returns the Car at the front of the queue without removing it from the queue
	 * 
	 * @return queueItems[front] Car the front of the queue
	 */
	public Car peek() {
		if (numItems > 0)
			return queueItems[front];
		return null;
	} // end method peek

	/**
	 * Returns the size of the queue
	 * 
	 * @return numItems the number of items in the queue
	 */
	public int getSize() {
		return numItems;
	} // end method getSize

	/**
	 * Returns true or false depending on if the queue is full
	 * 
	 * @return true if the queue is full, false otherwise
	 */
	public boolean isFull() {
		if (numItems == maxItems)
			return true;
		else
			return false;
	} // end method isFull

	/**
	 * Checks to see if the queue has more than <percent> percent of items in the
	 * queue
	 * 
	 * @param percent
	 *            the percent to be checked
	 * @return true if there the percent of the queue full is greater than or equal
	 *         to percent, false otherwise
	 */
	public boolean isPercent(int percent) {
		if (numItems >= (maxItems * (percent * .01)))
			return true;
		else
			return false;
	}
} // end method isPerecent
