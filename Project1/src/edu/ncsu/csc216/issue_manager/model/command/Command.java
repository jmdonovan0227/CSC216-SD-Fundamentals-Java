/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.command;

/**
 * This class is responsible for creating Command objects and passing Command objects
 * back and forth between Issue class so that an Issue's state can be correctly updated, this class
 * also includes all standard getter fields for each value that makes a valid Command object
 * @author Jake Donovan
 *
 */
public class Command {
	/** Resolution code fixed as a String */
	public static final String R_FIXED = "Fixed";
	/** Resolution code duplicate as a String */
	public static final String R_DUPLICATE = "Duplicate";
	/** Resolution code wontfix as a String */
	public static final String R_WONTFIX = "WontFix";
	/** Resolution code worksforme as a String*/
	public static final String R_WORKSFORME = "WorksForMe";
	/** A Command owner's id*/
	private String ownerId;
	/** A Command note */
	private String note;
	/** A CommandValue */
	private CommandValue c;
	/** A Resolution code */
	private Resolution resolution;

	/**
	 * Constructs a Command object with passed parameters including the CommandValue,
	 * the Command's owner id, Command's resolution code, and the Command's note
	 * @param c the CommandValue
	 * @param ownerId the Command's owner id
	 * @param r the Command's Resolution code
	 * @param note the Command's note
	 * @throws IllegalArgumentException if CommandValue is null, command equals Assign and owner id is null or an empty string,
	 * also will throw an Exception if notes is null or an empty String
	 */
	public Command(CommandValue c, String ownerId, Resolution r, String note) {
		if(c == null || c == CommandValue.ASSIGN && (ownerId == null || "".equals(ownerId))) {
			throw new IllegalArgumentException("Invalid information.");
		}

		if(c == CommandValue.RESOLVE && r == null) {
			throw new IllegalArgumentException("Invalid information.");
		}

		if(note == null || "".equals(note)) {
			throw new IllegalArgumentException("Invalid information.");
		}

		this.c = c;
		this.ownerId = ownerId;
		this.resolution = r;
		this.note = note;
	}

	/**
	 * Gets CommandValue
	 * @return c the CommandValue
	 */
	public CommandValue getCommand() {
		return c;
	}

	/**
	 * Gets Command's owner id
	 * @return ownerId the Command's owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Gets the Command's Resolution code
	 * @return resolution the Command's Resolution code
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * Gets the Command's note
	 * @return note the Command's note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * All valid CommandValues that can be used in the IssueManager program including
	 * assign, confirm, resolve, verify, and reopen
	 * @author Jake Donovan
	 *
	 */
	public enum CommandValue { ASSIGN, CONFIRM, RESOLVE, VERIFY, REOPEN }

	/**
	 * All Resolution codes that can be used in IssueManager program which include
	 * fixed, duplicate, wontfix, and worksforme
	 * @author Jake Donovan
	 *
	 */
	public enum Resolution { FIXED, DUPLICATE, WONTFIX, WORKSFORME }
}
