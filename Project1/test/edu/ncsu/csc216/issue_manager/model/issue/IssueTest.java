package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the functionality of the Issue class to make sure everything is functioning as intended
 * @author Jake Donovan
 *
 */
class IssueTest {
	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#Issue(int, edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testIssueIntIssueTypeStringString() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(1, null, "A summary", "A note"));
		assertEquals("Issue cannot be created.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Issue(2, IssueType.BUG, "A summary", null));
		assertEquals("Issue cannot be created.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Issue(2, IssueType.BUG, "A summary", ""));
		assertEquals("Issue cannot be created.", e3.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#Issue(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testIssueIntStringStringStringStringBooleanStringArrayListOfString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] a random note");
		Issue issue = new Issue(1, "Working", "Bug", "Summary", "Owner", true, "Duplicate", notes);
		assertEquals(1, issue.getIssueId());
		assertEquals("Working", issue.getStateName());
		assertEquals("Bug", issue.getIssueType());
		assertEquals("Summary", issue.getSummary());
		assertEquals("Owner", issue.getOwner());
		assertTrue(issue.isConfirmed());
		assertEquals("Duplicate", issue.getResolution());
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "Working", "Bug", "Summary", "", true, "Duplicate", notes));
		assertEquals("Issue cannot be created.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "Closed", "Enhancement", "Summary", "", true, "Duplicate", notes));
		assertEquals("Issue cannot be created.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getIssueId()}.
	 */
	@Test
	void testGetIssueId() {
		ArrayList<String> notes = new ArrayList<>();
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(-1, "New", "Bug", "A summary", "", false, null, notes));
		assertEquals("Issue cannot be created.", e.getMessage());
		Issue issue = new Issue(1, IssueType.BUG, "A summary", "A note");
		assertEquals(1, issue.getIssueId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getStateName()}.
	 */
	@Test
	void testGetStateName() {
		ArrayList<String> notes = new ArrayList<>();
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(1, null, "Bug", "A summary", "", false, null, notes));
		assertEquals("Issue cannot be created.", e.getMessage());
		Issue issue = new Issue(1, IssueType.BUG, "A summary", "A note");
		assertEquals("New", issue.getStateName());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "", "Bug", "A summary", "", false, null, notes));
		assertEquals("Issue cannot be created.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getIssueType()}.
	 */
	@Test
	void testGetIssueType() {
		ArrayList<String> notes = new ArrayList<>();
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", null, "A summary", "", false, null, notes));
		assertEquals("Issue cannot be created.", e.getMessage());
		Issue issue = new Issue(1, IssueType.BUG, "A summary", "A note");
		assertEquals("Bug", issue.getIssueType());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", "", "A summary", "", false, null, notes));
		assertEquals("Issue cannot be created.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getResolution()}.
	 */
	@Test
	void testGetResolution() {
		Issue issue = new Issue(1, IssueType.ENHANCEMENT, "Summary", "A note");
		assertEquals(null, issue.getResolution());
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] a random note");
		Issue issue2 = new Issue(2, "Working", "Bug", "Summary", "Owner", true, "Duplicate", notes);
		assertEquals(2, issue2.getIssueId());
		assertEquals("Working", issue2.getStateName());
		assertEquals("Bug", issue2.getIssueType());
		assertEquals("Summary", issue2.getSummary());
		assertEquals("Owner", issue2.getOwner());
		assertTrue(issue2.isConfirmed());
		assertEquals("Duplicate", issue2.getResolution());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getOwner()}.
	 */
	@Test
	void testGetOwner() {
		Issue issue = new Issue(1, IssueType.BUG, "Summary", "A note");
		assertEquals(null, issue.getOwner());
		assertEquals(1, issue.getIssueId());
		assertEquals("Bug", issue.getIssueType());
		assertEquals("Summary", issue.getSummary());
		assertEquals("[New] A note", issue.getNotes().get(0));
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] a random note");
		Issue issue2 = new Issue(2, "Working", "Bug", "Summary", "Owner", true, "Duplicate", notes);
		assertEquals(2, issue2.getIssueId());
		assertEquals("Working", issue2.getStateName());
		assertEquals("Bug", issue2.getIssueType());
		assertEquals("Summary", issue2.getSummary());
		assertEquals("Owner", issue2.getOwner());
		assertTrue(issue2.isConfirmed());
		assertEquals("Duplicate", issue2.getResolution());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getSummary()}.
	 */
	@Test
	void testGetSummary() {
		ArrayList<String> notes = new ArrayList<>();
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", "Bug", null, "", false, null, notes));
		assertEquals("Issue cannot be created.", e.getMessage());
		Issue issue = new Issue(1, IssueType.BUG, "A summary", "A note");
		assertEquals("A summary", issue.getSummary());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Issue(1, "New", "Bug", "", "", false, null, notes));
		assertEquals("Issue cannot be created.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getNotes()}.
	 */
	@Test
	void testGetNotes() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("[New] a note");
		Issue issue = new Issue(1, "Working", "Bug", "Summary", "Jake", true, "Duplicate", notes);
		assertEquals("Duplicate", issue.getResolution());
		Issue issue2 = new Issue(1, "Working", "Bug", "Summary", "Jake", true, "WorksForMe", notes);
		assertEquals("WorksForMe", issue2.getResolution());
		assertEquals(1, issue.getNotes().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getNotesString()}.
	 */
	@Test
	void testGetNotesString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] new note");
		Issue issue = new Issue(1, "New", "Bug", "Summary", "Jake", false, "Fixed", notes);
		assertEquals(1, issue.getIssueId());
		assertEquals("New", issue.getStateName());
		assertEquals("Summary", issue.getSummary());
		assertEquals("Jake", issue.getOwner());
		assertFalse(issue.isConfirmed());
		assertEquals("Fixed", issue.getResolution());
		
		try {
			issue.update(new Command(CommandValue.ASSIGN, "Owner", null, "A note"));
		} catch(UnsupportedOperationException e) {
			//
		}
		
		assertEquals(1, issue.getIssueId());
		assertEquals("New", issue.getStateName());
		assertEquals("Summary", issue.getSummary());
		assertEquals("Jake", issue.getOwner());
		assertFalse(issue.isConfirmed());
		assertEquals("Fixed", issue.getResolution());
		
		Issue issue2 = new Issue(1, IssueType.BUG, "Summary", "A note");
		assertEquals(1, issue2.getIssueId());
		assertEquals("Bug", issue2.getIssueType());
		assertEquals(1, issue2.getNotes().size());
		issue2.update(new Command(CommandValue.RESOLVE, "", Resolution.DUPLICATE, "A note"));
		assertEquals("Closed", issue2.getStateName());
		assertEquals("Duplicate", issue2.getResolution());
		
		try {
			issue2.update(new Command(CommandValue.ASSIGN, "An owner", null, "A note"));
		} catch(UnsupportedOperationException e) {
			//
		}
		
		assertEquals("Closed", issue2.getStateName());
		assertEquals("Duplicate", issue2.getResolution());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#isConfirmed()}.
	 */
	@Test
	void testIsConfirmed() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("[New] a test note");
		Issue issueTest = new Issue(1, "Closed", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertTrue(issueTest.isConfirmed());
		
		Issue issue2 = new Issue(2, IssueType.ENHANCEMENT, "Summary", "Note");
		assertFalse(issue2.isConfirmed());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#toString()}.
	 */
	@Test
	void testToString() {
		ArrayList<String> notes = new ArrayList<>();
		notes.add("[New] a test note");
		Issue issueTest = new Issue(1, "Closed", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("1,Closed,Bug,Summary,Owner,true,Fixed", issueTest.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	void testUpdate() {
		ArrayList<String> notes2 = new ArrayList<>();
		notes2.add("[New] a test note");
		Issue issueTest = new Issue(1, "Closed", "Bug", "Summary", "Owner", true, "Fixed", notes2);
		assertEquals("Fixed", issueTest.getResolution());
		try {
		issueTest.update(new Command(CommandValue.ASSIGN, "Owner", null, "A note"));
		}
		catch(UnsupportedOperationException e) {
			//
		}
		assertEquals("Fixed", issueTest.getResolution());
		
		
		issueTest.getResolution();
		assertEquals("Fixed", issueTest.getResolution());
		// New State Transitions
		Issue issue = new Issue(1, IssueType.BUG, "Summary", "A note");
		assertEquals("New", issue.getStateName());
		issue.update(new Command(CommandValue.RESOLVE, "", Resolution.DUPLICATE, "A note"));
		assertEquals("Closed", issue.getStateName());
		Issue issue2 = new Issue(2, IssueType.BUG, "Summary", "A note");
		assertEquals("New", issue2.getStateName());
		assertFalse(issue2.isConfirmed());
		issue2.update(new Command(CommandValue.CONFIRM, "", null, "A note"));
		assertEquals("Confirmed", issue2.getStateName());
		assertTrue(issue2.isConfirmed());
		Issue issue3 = new Issue(3, IssueType.ENHANCEMENT, "Summary", "A note");
		assertEquals("New", issue3.getStateName());
		issue3.update(new Command(CommandValue.ASSIGN, "Owner", null, "A note"));
		assertEquals("Working", issue3.getStateName());
		assertEquals("Owner", issue3.getOwner());

		// Working State Transitions
		ArrayList<String> notes = new ArrayList<>();
		notes.add("[New] another note");
		Issue issueR = new Issue(1, "Working", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("Working", issueR.getStateName());
		issueR.update(new Command(CommandValue.RESOLVE, "", Resolution.FIXED, "A note"));
		assertEquals("Verifying", issueR.getStateName());
		Issue issueR2 = new Issue(2, "Working", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("Working", issueR2.getStateName());
		issueR2.update(new Command(CommandValue.RESOLVE, "", Resolution.DUPLICATE, "A note"));
		assertEquals("Closed", issueR2.getStateName());
		Issue issueR3 = new Issue(3, "Working", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("Working", issueR3.getStateName());
		issueR3.update(new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "A note"));
		assertEquals("Closed", issueR3.getStateName());
		Issue issueR4 = new Issue(4, "Working", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("Working", issueR4.getStateName());
		issueR4.update(new Command(CommandValue.RESOLVE, "", Resolution.WORKSFORME, "A note"));
		assertEquals("Closed", issueR4.getStateName());
		Issue issueR5 = new Issue(5, "Working", "Enhancement", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Working", issueR5.getStateName());
		issueR5.update(new Command(CommandValue.RESOLVE, "", Resolution.DUPLICATE, "A note"));
		assertEquals("Closed", issueR5.getStateName());
		Issue issueR6 = new Issue(6, "Working", "Enhancement", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Working", issueR6.getStateName());
		issueR6.update(new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "A note"));
		assertEquals("Closed", issueR6.getStateName());

		// Confirmed State Transitions
		Issue issueCon = new Issue(1, "Confirmed", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Confirmed", issueCon.getStateName());
		issueCon.update(new Command(CommandValue.ASSIGN, "Owner", null, "A note"));
		assertEquals("Working", issueCon.getStateName());
		assertEquals("Owner", issueCon.getOwner());
		Issue issueCon2 = new Issue(2, "Confirmed", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Confirmed", issueCon2.getStateName());
		issueCon2.update(new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "A note"));
		assertEquals("Closed", issueCon2.getStateName());
		assertEquals("WontFix", issueCon2.getResolution());
		Issue issueCon3 = new Issue(3, "Confirmed", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Confirmed", issueCon3.getStateName());

		// Verifying State Transitions
		Issue issueVer = new Issue(1, "Verifying", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Verifying", issueVer.getStateName());
		issueVer.update(new Command(CommandValue.VERIFY, "", null, "A note"));
		assertEquals("Closed", issueVer.getStateName());
		Issue issueVer2 = new Issue(2, "Verifying", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Verifying", issueVer2.getStateName());
		issueVer2.update(new Command(CommandValue.REOPEN, "", null, "A note"));
		assertEquals("Working", issueVer2.getStateName());
		Issue issueVer3 = new Issue(3, "Verifying", "Bug", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Verifying", issueVer3.getStateName());
		//Exception e4 = assertThrows(UnsupportedOperationException.class, () -> issueVer3.update(new Command(CommandValue.RESOLVE, "", Resolution.DUPLICATE, "A note")));
		//assertEquals("Invalid information.", e4.getMessage());

		// Closed State Transitions
		Issue issueClo = new Issue(1, "Closed", "Bug", "Summary", "Owner", true, "Fixed", notes);
		assertEquals("Closed", issueClo.getStateName());
		assertEquals("Fixed", issueClo.getResolution());
		issueClo.update(new Command(CommandValue.REOPEN, "", null, "A note"));
		assertEquals("Working", issueClo.getStateName());
		assertEquals(null, issueClo.getResolution());
		Issue issueClo2 = new Issue(2, "Closed", "Bug", "Summary", "", true, "Fixed", notes);
		assertEquals("Closed", issueClo2.getStateName());
		assertEquals("Fixed", issueClo2.getResolution());
		issueClo2.update(new Command(CommandValue.REOPEN, "", null, "A note"));
		assertEquals("Confirmed", issueClo2.getStateName());
		assertEquals(null, issueClo2.getResolution());
		Issue issueClo3 = new Issue(3, "Closed", "Enhancement", "Summary", "", false, "Fixed", notes);
		assertEquals("Closed", issueClo3.getStateName());
		assertEquals("Fixed", issueClo3.getResolution());
		issueClo3.update(new Command(CommandValue.REOPEN, "", null, "A note"));
		assertEquals("New", issueClo3.getStateName());
		assertEquals(null, issueClo3.getResolution());
		Issue issueClo4 = new Issue(3, "Closed", "Enhancement", "Summary", "Owner", false, "Fixed", notes);
		assertEquals("Closed", issueClo4.getStateName());
		assertEquals("Fixed", issueClo4.getResolution());
		issueClo4.update(new Command(CommandValue.REOPEN, "", null, "A note"));
		assertEquals("Working", issueClo4.getStateName());
		assertEquals(null, issueClo4.getResolution());
	}
}
