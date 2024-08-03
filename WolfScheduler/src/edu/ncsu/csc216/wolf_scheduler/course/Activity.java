package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Activity super class will be responsible creating and adding Events to a schedule, and determining whether
 * an Event is valid and that there are no conflicts in a student's schedule with Events and Student Courses
 * @author Jake Donovan
 */
public abstract class Activity implements Conflict {
	/** Maximum hours used for determining valid course times */
	private static final int UPPER_HOUR = 24;
	/** Maximum minutes used for determining valid course times */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/** (Contract) for a method to return an Event or Course's name, section, title, and meeting string 
	 * to be implemented by Course and Event subclasses
	 * @return String[] of size 4 with all referenced (in above comment) information
	 */
	public abstract String[] getShortDisplayArray();
	
	/** 
	 * (Contract) for a method to return an Event or Course's name, section, title, credits, instructorId, meetingString, and EventDetails
	 * to be implemented by Course and Event subclasses
	 * @return String[] of size 7 with all referenced (in above comment) information
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks if two Activity objects are in conflict which means they have at least one day with an overlapping time, which means that an Activity
	 * on the same day starts at the same time when another Activity ends or if an Activity begins while another Activity is still in session
	 * @param possibleConflictingActivity a Course or Event that will be compared to Courses and Events in a student's schedule to determine whether the passed Activity conflicts
	 * with another Course or Event in the schedule in which case, a ConflictException will be thrown 
	 * @throws ConflictException a custom Exception which will be thrown if a passed Activity conflicts with another Course or Event (Activity = a Course or Event) in a student's schedule
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// No need to execute remaining statements if one of the Activities meeting days are "Arranged" because Arranged
		// Activities cannot conflict with another Activity
		if(possibleConflictingActivity.getMeetingDays().equals("A") || this.getMeetingDays().equals("A")) {
			return; // no more checking is necessary
		}
		
		// Boolean used to determine whether a Course or Event share a weekday
		boolean matchingDay = false;
		
		// Compare all meeting day character in passed activity to current Course being compared
		for(int i = 0; i < possibleConflictingActivity.getMeetingDays().length(); i++) {
			// Check if meeting day character in passed activity exists as a meeting day for the compared Course or Event
			// indexOf() will return -1 every time that a character (weekday) does not exists within meetingDays string of compared Course
			// if indexOf() does not return -1 that means a character (weekday) within passed activity exists within meeting days string (share a weekday)
			if(this.getMeetingDays().indexOf(possibleConflictingActivity.getMeetingDays().charAt(i)) != -1) {
				// If a matching weekday is found between two Activities being compared set matchinDay to true and break loop no further checking is needed
				matchingDay = true;
				break;
			}
		}
		
		// If a matching day was found between two activities, this code is triggered
		if(matchingDay) {
			// The start time of the passed Activity
			int activityStart = possibleConflictingActivity.getStartTime();
			int activityEnd = possibleConflictingActivity.getEndTime();
			
		    // Covers cases where, 1) start time (Passed Activity) is less than start of compared Activity but start time of passed Activity is less than compared Activity end time
			// Simplified this means a Course started before the other but is still going on when another Course starts = 1:00 - 2:00 vs 1:05 - 2:05, 2) Covers case when startTime of passed is greater
			// than start of compared but less than end time so basically the reverse order of case 1 so 1:05 - 2:05 vs 1:00 - 2:00 so Class starts 5 mins after the 2nd class and but the 2nd class is still going on so this
			// is also a Conflict (Could have used a reference to passed Activity end time but this solution seemed to be good to not only cover all cases but also cover all branches at the same time = more simple is better)
			if(activityStart > this.getStartTime() && activityStart <= this.getEndTime()) {
				throw new ConflictException();
			}
			
			if(activityStart < this.getStartTime() && activityEnd >= this.getEndTime()) {
				throw new ConflictException();
			}
			
			if(activityStart < this.getStartTime() && activityEnd > this.getStartTime() && activityEnd < this.getEndTime()) {
				throw new ConflictException();
			}
			
			// Covers case when Events start at same time
			if(activityStart == this.getStartTime() || this.getStartTime() == activityEnd || activityStart == this.getEndTime()) {
				throw new ConflictException();
			}
		}
	}
	
	/** (Contract) for a method that checks for duplicate Courses and Events within a student's schedule
	 * @param activity a Course or Event that will be examined to determine if the Course or Event already exists within a student's schedule
	 * @return true if a Course or Event already exists within a student's schedule otherwise return false
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Activity super class constructor, which creates Events or Courses based on passed parameters
	 * @param title A Course or Event's title
	 * @param meetingDays A Course  or Event's meeting days
	 * @param startTime A Course or Event's start time
	 * @param endTime A Course or Event's end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
	
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start times
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Course's end times
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets meeting days for courses using a course's meeting days, start times, and
	 * end times
	 * @param meetingDays a course's meeting days of the week
	 * @param startTime   a course's starting time
	 * @param endTime     a course's ending time
	 * @throws IllegalArgumentException if meeting days is null or an empty string,
	 * or if a meeting days is equal to arranged but starting time and ending time is not 0,
	 * or if starting times or ending times are less than 0, or if the starting time comes
	 * before ending time, or an invalid weekday character is received, or if a weekday
	 * character is found more than once, or if a start time or ending time is found to have
	 * invalid hours or minutes (aka greater than or equal to 24 or 60)
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(startTime < 0 || endTime < 0 || startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		// Convert starting and ending times hours and minutes to standard times for
		// error checking times
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
	
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		// Everything works if we are at this point
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		}

	/**
	 * Gets meeting string in standard time format
	 * 
	 * @return String a string containing a course's meeting days and times
	 *         formatted in standard time and including whether the course times are
	 *         in the morning, afternoon, or evening indicated by a AM or PM at the
	 *         end of the time
	 */
	public String getMeetingString() {
		// If a course is arranged simply return Arranged
		if (getMeetingDays().equals("A")) {
			return "Arranged";
		}
	
		// Otherwise call the private helper method to help us convert or time to a
		// standard time format
		String startTimeStandard = getTimeString(getStartTime());
		String endTimeStandard = getTimeString(getEndTime());
	
		return getMeetingDays() + " " + startTimeStandard + "-" + endTimeStandard;
	}

	/**
	 * Private helper method used to convert military time (style) starting and
	 * ending times for courses into standard time and returning to
	 * getMeetingString() method
	 * 
	 * @param time the starting or ending time that will be converted from military
	 *             time to standard time
	 * @return timeFormatted the starting or ending time formatted to standard time
	 *         and including AM or PM based on when the starting or ending time
	 *         takes place during the day
	 */
	private String getTimeString(int time) {
		String timeFormatted = "";
		int convertHour = time / 100;
		int convertMin = time % 100;
	
		if (convertHour >= 12) {
			if(convertHour > 12) {
				convertHour = convertHour - 12;
			}
	
			if (convertMin < 10) {
				String formatMins = "0" + convertMin;
				timeFormatted = convertHour + ":" + formatMins + "PM";
			}
	
			else {
				timeFormatted = convertHour + ":" + convertMin + "PM";
			}
		}
	
		else {
			// Check if convert hours is 0 meaning it is really 12 AM so convert to 12 here
			// placed here so convertHours will not incorrectly
			// return what time it is 12 AM versus 12 PM
		    if (convertHour == 0) {
				convertHour = 12;
			}
			
			if (convertMin < 10) {
				String formatMins = "0" + convertMin;
				timeFormatted = convertHour + ":" + formatMins + "AM";
			}
	
			// For the case if the time is 12 when it should be considered the afternoon
			else if (convertMin >= 10) {
				timeFormatted = convertHour + ":" + convertMin + "AM";
			}
		}
	
		return timeFormatted;
	}
	
	/**
	 * Generates a hashCode for Activity using all fields
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}