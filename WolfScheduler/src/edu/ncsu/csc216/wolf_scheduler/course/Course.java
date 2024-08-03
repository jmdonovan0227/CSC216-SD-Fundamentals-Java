/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Course class is being utilized in the WolfScheduler program to be able to
 * construct fields and receive all required information to create a valid
 * Course which can include a Course's name, title, section number, credits,
 * instructor id, meeting days, and starting and ending times if a Course's
 * meeting times are not arranged. The Course class is also responsible for
 * error checking all required fields that are used to create valid Course
 * object's through setter methods, and then all fields are also able to be able
 * to be returned through getter methods. The Course class is also able to use
 * the Object classes' equals() and hashCode() methods to be able to compare
 * objects for equivalency (Are the two Course the same?) and then the
 * hashCode() is able to returned a hashed value, and if two Course object are
 * equivalent, then the hashed value will be equal to Course object that is
 * being compared (the two Course objects that are being compared). The
 * toString() method (lastly) is able to return Course objects (with all fields
 * that create that Course object) as a String 
 * @author Jake Donovan
 */
public class Course extends Activity {
	/** Minimum course name length for a valid course name */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum course name length for a valid course name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum letter count for a valid course name */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum letter count for a valid course name */
	private static final int MAX_LETTER_COUNT = 4;
	/** Number of digits required for a valid course name */
	private static final int DIGIT_COUNT = 3;
	/** Number of digits required for a valid section number */
	private static final int SECTION_LENGTH = 3;
	/** Maximum credits for a valid course */
	private static final int MAX_CREDITS = 5;
	/** Minimum credits for a valid course */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	
	/**
	 * First Course Constructor of Course class which is utilized to create a Course
	 * object with all information including a Course's name, title, section number,
	 * credits, instructor id, meeting days, starting time, and ending time
	 * @param name a Course's name
	 * @param title a Course's title
	 * @param section a Course's section number
	 * @param credits a Course's credits
	 * @param instructorId a Course's instructor id
	 * @param meetingDays a Course's meeting days
	 * @param startTime a Course's starting time
	 * @param endTime a Course's ending time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Second Course Constructor of the Course class which will be utilized to
	 * generate a Course object when meeting times for a Course are arranged and
	 * there are no starting or ending times
	 * @param name a Course's name
	 * @param title a Course's title
	 * @param section a Course's section number
	 * @param credits a Course's credits
	 * @param instructorId a Course's instructor id
	 * @param meetingDays  a Course's meeting days
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null, less than min name length,
	 * greater than max name length, an invalid ordering of letter and numbers to make a
	 * course name, an invalid number of letters or digits for a valid course name.
	 */
	private void setName(String name) {
		// Throw exception if name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 characters or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check for correct pattern for a course name
		int letterCounter = 0;
		int digitCounter = 0;
		boolean flag = false;

		for (int i = 0; i < name.length(); i++) {
			if (!flag) {
				if (Character.isLetter(name.charAt(i))) {
					letterCounter++;
				}

				else if (name.charAt(i) == ' ') {
					flag = true;
				}

				else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}

			else if (flag) {
				if (Character.isDigit(name.charAt(i))) {
					digitCounter++;
				}

				else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		// Check that the number of letters is correct
		if (letterCounter < MIN_LETTER_COUNT || letterCounter > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check that the number of digits is correct
		if (digitCounter != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// If we are this far, we have a valid course name. Set field to paramater
		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null, section length is not
	 * equal to 3, or if section value contains a non-digit
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// Check section string to make sure all characters are digits
		boolean isNotDigit = false;
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				isNotDigit = true;
			}
		}

		// If a non digit is found throw an exception
		if (isNotDigit) {
			throw new IllegalArgumentException("Invalid section.");
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are less than min credits or
	 * greater than max credits
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor id
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor id
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is null or an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}
	
	/**
	 * Returns a String array of length four containing a Course's name, section, title, and meeting string
	 * @return shortDisplay a String array containing all information listed above on Course objects
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = super.getTitle();
		shortDisplay[3] = super.getMeetingString();
				
		return shortDisplay;
	}
	
	/**
	 * Returns a String array of length seven containing a Course's name, section, title, credits, instructorId, meeting string,
	 * and an empty string because a Course does not have event details
	 * @return longDisplay a String array containing all information listed above on Course objects
	 */
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = super.getTitle();
		longDisplay[3] = Integer.toString(getCredits());
		longDisplay[4] = getInstructorId();
		longDisplay[5] = super.getMeetingString();
		longDisplay[6] = "";
				
		return longDisplay;
	}
	
	/**
	 * This setMeetingDays method is responsible for checking that a Course object's meeting days,
	 * starting, and ending time are valid by overriding setMeetingDays method from Activity superclass, and then this method
	 * will pass information (after checking Course object) to Activity superclass for final checks and final assignment of parameters
	 * @param meetingDays a Course's meeting days
	 * @param startTime a Course's starting time
	 * @param endTime a Course's ending time
	 * @throws IllegalArgumentException if startTime and endTime is not 0 when meeting days are arranged, if any other  weekday letter than
	 * M, T, W, H, or F are found in meeting days when not arranged, or if a meeting day appears more than once
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// First we wanna check for an arranged meeting days
		// Note this will only check if A is the only letter in String, below we will handle the case
		// Where A is a meeting day but there are other meeting days which is invalid
		if(meetingDays != null) {
			
			if("A".equals(meetingDays)) {
				if(startTime != 0 && endTime != 0) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
				
				// Course meeting days is arranged and startTime and endTime are equal to 0
				// Pass final checks to super class (Activity)
				super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			}
			
			else {
				int mCounter = 0;
				int tCounter = 0;
				int wCounter = 0;
				int thCounter = 0;
				int fCounter = 0;
					
					// Check for multiple occurrences of the same day for a Course
					// Check that meeting days are valid that being M, T, W, H, and F being valid
					for(int i = 0; i < meetingDays.length(); i++) {
						if (meetingDays.charAt(i) == 'M') {
							mCounter++;
						}
			
						else if (meetingDays.charAt(i) == 'T') {
							tCounter++;
						}
			
						else if (meetingDays.charAt(i) == 'W') {
							wCounter++;
						}
			
						else if (meetingDays.charAt(i) == 'H') {
							thCounter++;
						}
			
						else if (meetingDays.charAt(i) == 'F') {
							fCounter++;
						}
			
						else {
							throw new IllegalArgumentException("Invalid meeting days and times.");
						}
					}
			
			// Did a course appear more than once?
			if (mCounter > 1 || tCounter > 1 || wCounter > 1 || thCounter > 1 || fCounter > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			}
		}
			
			// Pass to super class for final checks if we have made it this far
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	
	/**
	 * This method is responsible for checking for duplicate Courses within a student's schedule by checking if a passed activity
	 * is an instance of Course and already exists within a student's schedule (will be called several times through a for loop in WolfScheduler
	 * so that each Course within schedule will be checked against passed activity)
	 * @param activity an Activity that will be cross referenced with Courses within a Student's schedule,
	 * if the activity is actually an Event this method will simply return false
	 * @return true if a duplicate event is found otherwise this method returns false
	 */
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && ((Course) activity).getName().equals(this.getName());
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}
	
	/**
	 * Generates a hashCode for Course using all fields within Course class
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields, otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
}
