package traffic;

public class CarQueue {

	// variables
	private int numItems;
	private int maxItems;
	private int front;
	private int back;
	
	// objects
	private Car returnVal;
	private Car[] queueItems;

	public CarQueue(int size) {
		numItems = 0;
		maxItems = size;
		front = 0;
		back = 0;
		if (size > 0)
			queueItems = new Car[maxItems];
	}

	public void enqueue(Car c) throws QueueException {
		if (numItems < maxItems) {
			queueItems[back] = c;
			back = (back + 1) % maxItems;
			numItems++;
		} else
			throw new QueueException("Cannot enqueue item. The queue is full.");
	}

	public Car dequeue() throws QueueException {
		if (numItems > 0) {
			returnVal = queueItems[front];
			queueItems[front] = null;
			front = (front + 1) % maxItems;
			numItems--;
			return returnVal;
		} else
			throw new QueueException("Cannot dequeue item. The queue is empty.");
	}

	public Car peek() {
		if (numItems > 0)
			return queueItems[front];
		return null;
	}

	public int getSize() {
		return numItems;
	}
	
	public boolean isFull() {
		if(numItems == maxItems)
			return true;
		else
			return false;
	}
	
	public boolean isPercent(int percent) {
		if(numItems >= (maxItems * (percent * .01)))
			return true;
		else
			return false;
	}
}
