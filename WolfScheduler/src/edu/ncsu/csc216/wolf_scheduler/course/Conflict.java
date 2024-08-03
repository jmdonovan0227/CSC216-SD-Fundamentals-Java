package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Conflict interface is used to declare a method known as checkConflict (void method) which will be used in implementing classes
 * to determine whether a passed Activity (Course or Event) conflicts with another Course or Event in a student's schedule, in which case,
 * a custom ConflictException will be thrown
 * @author Jake Donovan
 *
 */
public interface Conflict {
	/** Declares (contract) a checkConflict method within this interface that when implemented in other classes will
	 * be used to check for conflicting Courses and Events within a student's schedule, the parameter will included an Activity
	 * which will need to be recognized as a Course and Event then checked among other Courses and Events in a student's schedule, then in the case
	 * the Activity is conflicting with another Course or Event a custom ConflictException will be thrown indicating that the student cannot add the Course or Event 
	 * passed in parameter to their schedule
	 * @param possibleConflictingActivity a Course or Event that could conflict with another Course or Event in a student's schedule
	 * @throws ConflictException a custom Exception which will be called if the passed activity conflicts with another Course or Event in a student's schedule
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
