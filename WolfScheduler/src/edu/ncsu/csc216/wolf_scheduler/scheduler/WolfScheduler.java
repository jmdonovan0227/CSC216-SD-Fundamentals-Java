/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/** The WolfScheduler class is responsible for creating an ArrayList of Courses for
 * a Course Schedule being created by a student and a Course catalog which holds all NCSU courses.
 * This class is also capable of modifying a schedule's title, adding Courses to the schedule, removing Courses from the schedule,
 * reading in Courses from a file via readCourseRecords() method in CourseRecordIO, and this class can also export a student's schedule
 * schedule by passing a file name and the schedule using the writeCourseRecords() method from CourseRecordIO, and lastly, this class is also capable
 * of returning all fields through getter methods, it is able to reset a schedule, and return a Course Catalog and schedules as a 2d String array with a few pieces of information including name,
 * section, and title or all information related to a course using another method
 * @author Jake Donovan
 *
 */
public class WolfScheduler {
	/** An ArrayList of type Course which contains all Courses in the NCSU Course Catalog */
	private ArrayList<Course> catalog;
	/** An ArrayList of type Course which contains all Courses added to a student's schedule */
	private ArrayList<Activity> schedule;
	/** The title of a student's schedule */
	private String title;
	
	/**
	 * WolfScheduler constructor which is used to create a new schedule, set the default schedule title
	 * as "My Schedule", and also reads in all Courses from Course Catalog into an ArrayList of Courses known as the catalog
	 * @param fileName the file that contains all Courses within the Course catalog which will be read into WolfScheduler class
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		setScheduleTitle("My Schedule");
		
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * Sets the schedule's title
	 * @param title the schedule's new title (if valid)
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setScheduleTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}
	
	/**
	 * Exports a student's schedule by passing the passed file name parameter
	 * and the ArrayList of Courses called the schedule which was created by the student
	 * @param fileName the name of the file where the student's schedule will be saved
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch(IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
	/**
	 * Gets schedule title
	 * @return title the schedule's title
	 */
	public String getScheduleTitle() {
		return title;
	}
	
	/**
	 * Adds Courses to student' schedule
	 * @param name the name of a Course to be added to the schedule (if valid)
	 * @param section the section number of a Course to be added to the schedule (if valid)
	 * @throws IllegalArgumentException if a Course already exists within schedule (is a duplicate!)
	 * @return course a valid Course or null based on whether the passed parameters created a valid Course
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course course = null;
		boolean flag = false; // This will be true when a duplicate Course is found
		
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				course = catalog.get(i);
				break;
			}
		}
		
		if(course != null) {
			for(int i = 0; i < schedule.size(); i++) {
				if(schedule.get(i).isDuplicate(course)) {
					// Schedule.get(i) is a Course and the Course already exists within schedule
					// return false
					flag = true;
				}
			}
			
			if(flag) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			
			else {
				try {
				for(int i = 0; i < schedule.size(); i++) {
					schedule.get(i).checkConflict(course);
				}
				} catch(ConflictException e) {
					// If we got this far a conflict was thrown
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
			
			// If we got this far there are no issues adding Activity to schedule (so now we can add Activity to schedule
			schedule.add(course);
			return true;
		}
		
		// If a course does not exist in catalog return false
		return false;
	}
	
	/**
	 * This method is responsible for checking for duplicate Events and then if Events do not 
	 * already exist within a student's schedule (is a duplicate) then the Event will be added to a student's schedule
	 * via the ArrayList add option
	 * @param eventTitle an Event's title
	 * @param eventMeetingDays an Event's meeting days
	 * @param eventStartTime an Event's starting time
	 * @param eventEndTime an Event's ending time
	 * @param eventDetails an Event's details
	 * @throws IllegalArgumentException if an Event already exists within a student's schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		boolean flag = false; // If a duplicate Event is found this will be true
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(event)) {
				flag = true; // A duplicate event was found in schedule
			}
		}
		
		if(flag) {
			throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
		}
		
		else {
			try {
			for(int i = 0; i < schedule.size(); i++) {
				schedule.get(i).checkConflict(event);
			}
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		
		// If we got this far, the Event is not a duplicate and does not have a time conflict with another Activity, thus we can
		// add the Activity
		schedule.add(event);
	}
	
	/**
	 * Returns the schedule as a 2D String array with Course's names, titles, and section numbers or an empty 2D String array if there
	 * are not Courses in the catalog
	 * @return scheduleCourses2D the schedule returned with Course's names, titles, and section numbers
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduledActivities2D = new String[schedule.size()][3];
		
		for(int i = 0; i < schedule.size(); i++) {
			scheduledActivities2D[i] = schedule.get(i).getShortDisplayArray();
		}
		
		return scheduledActivities2D;
	}
	
	/**
	 * Returns Courses within the catalog as a 2D String array including Course's names, titles, and section numbers,
	 * or if the Course catalog is empty, an empty 2D String array will be returned
	 * @return courseCatalog2D the course catalog as a 2D String array which includes information on Course's names, titles, and section numbers
	 */
	public String[][] getCourseCatalog() {
		   String [][] catalogArray = new String[catalog.size()][3];
	        for (int i = 0; i < catalog.size(); i++) {
	            Course c = catalog.get(i);
	            catalogArray[i] = c.getShortDisplayArray();
	        }
	        
	        return catalogArray;
	}
	
	/**
	 * Returns a student's schedule returned as 2D String array with all information included on their Courses within their schedule including
	 * Course names, titles, section numbers, credit hours, instructor ids, and meeting days and times
	 * @return fullScheduledCourses2D a student's schedule returned as a 2D String array with all information on their Courses
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullScheduledActivities2D = new String[schedule.size()][6];
		
		for(int i = 0; i < schedule.size(); i++) {
			fullScheduledActivities2D[i] = schedule.get(i).getLongDisplayArray();
		}
		
		return fullScheduledActivities2D;
	}
	
	/**
	 * Creates a new schedule for students
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}
	
	/**
	 * Removes a Course from a student's schedule by passing a Course name and section number which will be used to determine
	 * if the Course can be removed from the student's schedule
	 * @param idx TODO
	 * @return boolean true or false based on whether the Course can be removed from a student's schedule or not
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Gets a Course from the Course catalog by passing a name and section parameter which be used to cross reference
	 * with Courses within the Course catalog. If a match is found the Course will be returned otherwise this method returns null
	 * @param name a Course's name (if valid)
	 * @param section a Course's section number (if valid)
	 * @return course a Course that is contained within the Course catalog or null if the Course does not exist in the Course catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course course = null;
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				course = catalog.get(i);
				break;
			}
		}
		
		return course;
	}
}
