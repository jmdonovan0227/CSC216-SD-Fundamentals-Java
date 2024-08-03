/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the functionality of the IssueManager class
 * @author Jake Donovan
 *
 */
class IssueManagerTest {
	/** Instance of the IssueManager class */
	private IssueManager issueManager;

	/**
	 * Creates an instance of IssueManager class, which can be used to test IssueManager class
	 * @throws java.lang.Exception if an instance of IssueManager class cannot be created
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.issueManager = IssueManager.getInstance();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		this.issueManager = IssueManager.getInstance();
		assertNotNull(issueManager);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#saveIssuesToFile(java.lang.String)}.
	 */
	@Test
	void testSaveIssuesToFile() {
		this.issueManager.createNewIssueList();
		this.issueManager.addIssueToList(IssueType.BUG, "A summary", "A note");
		assertEquals(1, issueManager.getIssueListAsArray().length);
		this.issueManager.saveIssuesToFile("test-files/issue_manager_test_file");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#loadIssuesFromFile(java.lang.String)}.
	 */
	@Test
	void testLoadIssuesFromFile() {
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, this.issueManager.getIssueListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#createNewIssueList()}.
	 */
	@Test
	void testCreateNewIssueList() {
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, this.issueManager.getIssueListAsArray().length);
		this.issueManager.createNewIssueList();
		assertEquals(0, this.issueManager.getIssueListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueListAsArray()}.
	 */
	@Test
	void testGetIssueListAsArray() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, this.issueManager.getIssueListAsArray().length);
		assertEquals(1, this.issueManager.getIssueListAsArray()[0][0]);
		assertEquals("New", this.issueManager.getIssueListAsArray()[0][1]);
		assertEquals("Enhancement", this.issueManager.getIssueListAsArray()[0][2]);
		assertEquals("Issue description", this.issueManager.getIssueListAsArray()[0][3]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueListAsArrayByIssueType(java.lang.String)}.
	 */
	@Test
	void testGetIssueListAsArrayByIssueType() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, this.issueManager.getIssueListAsArray().length);
		assertEquals(2, this.issueManager.getIssueListAsArrayByIssueType("Bug").length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueById(int)}.
	 */
	@Test
	void testGetIssueById() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, issueManager.getIssueListAsArray().length);
		assertEquals("1,New,Enhancement,Issue description,null,false,", issueManager.getIssueById(1).toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#executeCommand(int, edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	void testExecuteCommand() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, issueManager.getIssueListAsArray().length);
		issueManager.executeCommand(1, new Command(CommandValue.ASSIGN, "Jake", null, "A note"));
		assertEquals("Working", issueManager.getIssueById(1).getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#deleteIssueById(int)}.
	 */
	@Test
	void testDeleteIssueById() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, issueManager.getIssueListAsArray().length);
		issueManager.deleteIssueById(1);
		assertEquals(4, issueManager.getIssueListAsArray().length);
		assertEquals(3, issueManager.getIssueListAsArray()[0][0]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#addIssueToList(edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testAddIssueToList() {
		this.issueManager.createNewIssueList();
		this.issueManager.loadIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, issueManager.getIssueListAsArray().length);
		this.issueManager.createNewIssueList();
		this.issueManager.addIssueToList(IssueType.ENHANCEMENT, "An extra summary", "An extra note");
		assertEquals(1, issueManager.getIssueListAsArray().length);
	}
}
