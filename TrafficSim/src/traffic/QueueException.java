package traffic;

/**
 * Exception for the CarQueue data structure
 * 
 * @author Garrison Henkle
 * @since 1.8
 *
 */
public class QueueException extends Exception {
	/**
	 * Constructor with a reason parameter
	 * 
	 * @param reason
	 *            message to be printed to the screen
	 */
	public QueueException(String reason) {
		super(reason);
	} // end constructor QueueException
}
