/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the IssueList class
 * @author Jake Donovan
 *
 */
class IssueListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#IssueList()}.
	 */
	@Test
	void testIssueList() {
		IssueList issueList = new IssueList();
		issueList.addIssue(IssueType.BUG, "A bug issue", "A note");
		assertEquals(1, issueList.getIssues().size());
		assertEquals(issueList.getIssues().get(0), issueList.getIssueById(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#addIssue(edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testAddIssue() {
		IssueList issueList = new IssueList();
		issueList.addIssue(IssueType.BUG, "A bug issue", "A note");
		assertEquals(issueList.getIssues().get(0), issueList.getIssueById(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#addIssues(java.util.List)}.
	 */
	@Test
	void testAddIssues() {
		IssueList issueList = new IssueList();
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue(1, IssueType.BUG, "A new issue", "A note"));
		issues.add(new Issue(3, IssueType.BUG, "Another issue", "Another note"));
		issues.add(new Issue(2, IssueType.ENHANCEMENT, "A test issue", "A test issue note"));
		issueList.addIssues(issues);
		assertEquals(3, issueList.getIssues().size());
		assertEquals(1, issueList.getIssues().get(0).getIssueId());
		assertEquals(2, issueList.getIssues().get(1).getIssueId());
		assertEquals(3, issueList.getIssues().get(2).getIssueId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssues()}.
	 */
	@Test
	void testGetIssues() {
		IssueList issueList = new IssueList();
		assertEquals(0, issueList.getIssues().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssuesByType(java.lang.String)}.
	 */
	@Test
	void testGetIssuesByType() {
		IssueList issueList = new IssueList();
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue(1, IssueType.BUG, "A new issue", "A note"));
		issues.add(new Issue(3, IssueType.BUG, "Another issue", "Another note"));
		issues.add(new Issue(2, IssueType.ENHANCEMENT, "A test issue", "A test issue note"));
		issueList.addIssues(issues);
		assertEquals(3, issueList.getIssues().size());
		assertEquals(1, issueList.getIssuesByType("Enhancement").size());
		assertEquals(2, issueList.getIssuesByType("Bug").size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssueById(int)}.
	 */
	@Test
	void testGetIssueById() {
		IssueList issueList = new IssueList();
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue(1, IssueType.BUG, "A new issue", "A note"));
		issues.add(new Issue(3, IssueType.BUG, "Another issue", "Another note"));
		issues.add(new Issue(2, IssueType.ENHANCEMENT, "A test issue", "A test issue note"));
		issueList.addIssues(issues);
		assertEquals(3, issueList.getIssues().size());
		assertEquals(issues.get(0), issueList.getIssueById(1));
		assertEquals(issues.get(1), issueList.getIssueById(3));
		assertEquals(issues.get(2), issueList.getIssueById(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#executeCommand(int, edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	void testExecuteCommand() {
		IssueList issueList = new IssueList();
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue(1, IssueType.BUG, "A new issue", "A note"));
		issues.add(new Issue(3, IssueType.BUG, "Another issue", "Another note"));
		issues.add(new Issue(2, IssueType.ENHANCEMENT, "A test issue", "A test issue note"));
		issueList.addIssues(issues);
		assertEquals(3, issueList.getIssues().size());
		issueList.executeCommand(1, new Command(CommandValue.CONFIRM, "", null, "A test add"));
		assertEquals("Confirmed", issueList.getIssueById(1).getStateName());
		assertEquals(2, issueList.getIssueById(1).getNotes().size());
		assertEquals(null, issueList.getIssueById(1).getOwner());
		assertEquals(null, issueList.getIssueById(1).getResolution());
		assertEquals("[New] A note", issueList.getIssueById(1).getNotes().get(0));
		assertEquals("[Confirmed] A test add", issueList.getIssueById(1).getNotes().get(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#deleteIssueById(int)}.
	 */
	@Test
	void testDeleteIssueById() {
		IssueList issueList = new IssueList();
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue(1, IssueType.BUG, "A new issue", "A note"));
		issues.add(new Issue(3, IssueType.BUG, "Another issue", "Another note"));
		issues.add(new Issue(2, IssueType.ENHANCEMENT, "A test issue", "A test issue note"));
		issueList.addIssues(issues);
		assertEquals(3, issueList.getIssues().size());
		issueList.deleteIssueById(1);
		assertEquals(2, issueList.getIssues().size());
		assertEquals(2, issueList.getIssues().get(0).getIssueId());
		assertEquals(3, issueList.getIssues().get(1).getIssueId());
	}

}
