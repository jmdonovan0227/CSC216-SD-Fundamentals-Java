/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Event class is responsible for creating valid Event objects by passing an Event's title,
 * meeting days, start time, end time, and checking event details to make sure they are valid
 * @author Jake Donovan
 *
 */
public class Event extends Activity {
	/** An Event's details */
	private String eventDetails;
	
	/**
	 * Event class constructor creates an Event object by passing parameters to Activity
	 * super class and passes event details to setter method within Event class to finalize
	 * the Event object (Finalize = all Event details are connected to Event object and initialized)
	 * @param title an Event's title
	 * @param meetingDays an Event's meeting days
	 * @param startTime an Event's starting time
	 * @param endTime an Event's ending time
	 * @param eventDetails an Event's details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}
	
	/**
	 * Returns Event details
	 * @return eventDetails the Event details
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	
	/**
	 * Sets Event details
	 * @param eventDetails the Event's details
	 * @throws IllegalArgumentException if event details are null, event details are optional so event details
	 * can be an empty String
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Returns details on an Event object as a short display including an Event's
	 * title, and meeting string (aka meeting days and start/end times) (Note: all remaining fields are empty Strings
	 * because an Event will not have a name or section like Course)
	 * @return shortDisplay a String array containing all information (listed in above comment) on an Event
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = super.getTitle();
		shortDisplay[3] = super.getMeetingString();
		
		return shortDisplay;
	}
	
	/**
	 * Returns all details on an Event object as a long display including an Event's
	 * title, meeting string (aka meeting days and start/end times), and an Event's details (Note: all remaining fields are empty Strings)
	 * because an Event does not have a name, section, credits, or instructorId like Course)
	 * @return longDisplay a String array containing all information (listed in above comment) on an Event
	 */
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = super.getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = super.getMeetingString();
		longDisplay[6] = getEventDetails();
		
		return longDisplay;
	}
	
	
	/**
	 * This method is responsible checking Event objects to verify that they are valid Events by overriding Activity superclass
	 * setMeetingDays method and then this method will pass to setMeetingDays method within superclass for final checks and final assignment
	 * @param meetingDays an Event's meeting days
	 * @param startTime an Event's starting time
	 * @param endTime an Event's ending time
	 * @throws IllegalArgumentException if any weekday letter other than M, T, W, H, F, S, U appear within an Event's meeting days,
	 * and if a valid weekday appears more than once for an Event's meeting days
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
			int mCounter = 0;
			int tCounter = 0;
			int wCounter = 0;
			int thCounter = 0;
			int fCounter = 0;
			int saCounter = 0;
			int suCounter = 0;
			
			if(meetingDays != null) {
			
			for(int i = 0; i < meetingDays.length(); i++) {
				if(meetingDays.charAt(i) == 'M') {
					mCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'T') {
					tCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'W') {
					wCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'H') {
					thCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'F') {
					fCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'S') {
					saCounter++;
				}
				
				else if(meetingDays.charAt(i) == 'U') {
					suCounter++;
				}
				
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			if(mCounter > 1 || tCounter > 1 || wCounter > 1 || thCounter > 1 || fCounter > 1 || saCounter > 1 || suCounter > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * This method is responsible for checking for duplicate Event's within a student's schedule,
	 * this method will first check if a passed activity is an instance of Event and then checking if that Event already
	 * exists within a student's schedule, this method will achieve by being called several times through the use of a for loop
	 * in WolfScheduler class and will compare the activity's title against Event titles within schedule, if the activity is actually a Course
	 * this method will return false
	 * @param activity a passed activity that can be either a Course or Event
	 * @return true if the passed activity is a duplicate Course (already in student's schedule) otherwise this method will return false
	 */
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Event && activity.getTitle().equals(this.getTitle());
	}

	/**
	 * Overrides super class implementation of toString() to return an Event's
	 * title, meeting days, starting time, and ending time as a string
	 * @return String a string containing all information (listed in above comment) on an Event as a string
	 */
	@Override
	public String toString() {
		return super.getTitle() + "," + super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime() + "," + getEventDetails();
	}
}
