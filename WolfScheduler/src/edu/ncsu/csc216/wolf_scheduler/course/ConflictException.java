/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This java class is used to throw a custom exception known as a ConflictException
 * it achieves this by first extending the Exception class and using code to create the messages and conditions
 * for when the ConflictException is thrown and then returned to the class that throws a ConflictException
 * @author Jake Donovan
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This constructor is responsible for passing custom Exception
	 * message to parent constructor, which would be the super class known as the Exception class
	 * which this class is currently extending
	 * @param message the custom Exception message that is passed to parent constructor
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * This constructor passes the default message to first constructor and sets the default message as "Schedule conflict."
	 * when this constructor is called (Default Message = "Schedule conflict.")
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
