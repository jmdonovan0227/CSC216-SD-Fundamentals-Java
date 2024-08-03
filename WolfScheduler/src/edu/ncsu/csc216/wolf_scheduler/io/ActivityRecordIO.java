/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * This class is responsible for writing Activities created by a student, to a file
 * where all Events and Courses can be saved
 * @author Jake Donovan
 *
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Courses and Events to a file  
	 * @param fileName file to write schedule of Courses to
	 * @param activities an ArrayList of Activities which can include Courses and Events
	 * @throws IOException if cannot write to file
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (Activity a : activities) {
		    fileWriter.println(a.toString());
		}
	
		fileWriter.close();
	}
}
