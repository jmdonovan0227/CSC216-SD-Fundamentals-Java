/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the checkConflict() method within Activity class, to determine if checkConflict()
 * method is correctly determining if a Course or Event should create a conflict and throw a ConflictException
 * @author Jake Donovan
 *
 */
class ActivityTest {

	/**
	 * tests checkConflict() with two non-conflicting Courses
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		    
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1500, 1600);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		    
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));
	}
	
	/**
	 * Tests checkConflict method by creating to Courses that conflict. The creation of these two Courses should throw a
	 * ConflictException which is what we are testing here
	 */
	@Test
	public void testCheckConflictWithConflict() {
		// Test cases to consider, 1) a2.checkConflict(a1) method is called to check method is communtative.
		// 2) Conflict on a singe day, 3) Conflict where endTime for this is the same as the startTime for possibleConflictingActivity
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1600);
	    
	    Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
	    
	    try {
			a4.checkConflict(a3);
		} catch (ConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
	    Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a6 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1335, 1500);
	    
	    Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
	    assertEquals("Schedule conflict.", e5.getMessage());
	    
	    try {
	    	a6.checkConflict(a5);
	    } catch(ConflictException e) {
	    	//
	    }
	    Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
	    assertEquals("Schedule conflict.", e6.getMessage());
	    
	    Activity a7 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1335, 1505);
	    Activity a8 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TW", 1330, 1500);
	 
	    
	    Exception e7 = assertThrows(ConflictException.class, () -> a7.checkConflict(a8));
	    assertEquals("Schedule conflict.", e7.getMessage());
	    
	    Exception e8 = assertThrows(ConflictException.class, () -> a8.checkConflict(a7));
	    assertEquals("Schedule conflict.", e8.getMessage());
	    
	    Activity a9 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1300, 1455);
	    Activity a10 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TW", 1230, 1255);
	    assertDoesNotThrow(() -> a10.checkConflict(a9));
	    
	    Activity a11 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1300, 1300);
	    Activity a12 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TW", 1300, 1300);
	    Exception e9 = assertThrows(ConflictException.class, () -> a11.checkConflict(a12));
	    assertEquals("Schedule conflict.", e9.getMessage());
	    
	    Activity a13 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
	    Activity a14 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TW", 1330, 1500);
	    assertDoesNotThrow(() -> a13.checkConflict(a14));
	    assertDoesNotThrow(() -> a14.checkConflict(a13));
	}
}
