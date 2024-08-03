/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Class is responsible for creating a list of issues and sharing information with other classes
 * @author Jake Donovan
 *
 */
public class IssueManager {
	/** A list of issues */
	private IssueList issueList;
	/** A singleton of the IssueManager class*/
	private static IssueManager singleton;

	/**
	 * The IssueManager class constructor
	 */
	private IssueManager() {
		issueList = new IssueList();
	}

	/**
	 * Gets instance of IssueManager class
	 * @return issueManager an instance of the IssueManager class
	 */
	public static IssueManager getInstance() {
		if(singleton == null) {
			singleton = new IssueManager();
		}

		return singleton;
	}

	/**
	 * Saves issues to a file
	 * @param fileName the passed file name
	 * @throws IllegalArgumentException if something happens while delegating to IssueWriter class
	 */
	public void saveIssuesToFile(String fileName) {
		IssueWriter.writeIssuesToFile(fileName, issueList.getIssues());
	}

	/**
	 * Loads issues from a file
	 * @param fileName the file where issues will be saved
	 * @throws IllegalArgumentException if something happens while delegating to IssueReader class
	 */
	public void loadIssuesFromFile(String fileName) {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(fileName);

		if(issues != null) {
			createNewIssueList();
			issueList.addIssues(issues);
		}
	}

	/**
	 * Creates a new issue list
	 */
	public void createNewIssueList() {
		issueList = new IssueList();
	}

	/**
	 * Gets issue list as an array
	 * @return issueListArray the issue list as an array
	 */
	public Object[][] getIssueListAsArray(){
		Object[][] issueListAsArray = new Object[issueList.getIssues().size()][4];

		for(int i = 0; i < issueListAsArray.length; i++) {
			issueListAsArray[i][0] = issueList.getIssues().get(i).getIssueId();
			issueListAsArray[i][1] = issueList.getIssues().get(i).getStateName();
			issueListAsArray[i][2] = issueList.getIssues().get(i).getIssueType();
			issueListAsArray[i][3] = issueList.getIssues().get(i).getSummary();
		}

		return issueListAsArray;
	}

	/**
	 * Gets IssueList by type as an array
	 * @param issueType the issue's type
	 * @throws IllegalArgumentException if issue type is null
	 * @return issueListAsAnArray the issue list as an array by type
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issueType){
		if(issueType == null) {
			throw new IllegalArgumentException("Issue type cannot be null.");
		}


		Object[][] issuesListAsArrayByType = new Object[issueList.getIssuesByType(issueType).size()][4];

		for(int i = 0; i < issuesListAsArrayByType.length; i++) {
			issuesListAsArrayByType[i][0] = issueList.getIssuesByType(issueType).get(i).getIssueId();
			issuesListAsArrayByType[i][1] = issueList.getIssuesByType(issueType).get(i).getStateName();
			issuesListAsArrayByType[i][2] = issueList.getIssuesByType(issueType).get(i).getIssueType();
			issuesListAsArrayByType[i][3] = issueList.getIssuesByType(issueType).get(i).getSummary();
		}

		return issuesListAsArrayByType;
	}

	/**
	 * Gets issues by id
	 * @param id the issue' id
	 * @return issue the issue
	 */
	public Issue getIssueById(int id) {
		for(int i = 0; i < issueList.getIssues().size(); i++) {
			if(issueList.getIssueById(id) != null) {
				return issueList.getIssueById(id);
			}
		}

		return null;
	}

	/**
	 * Executes a Command on an issue with passed index and Command
	 * @param idx the issue's index
	 * @param c the Command value
	 */
	public void executeCommand(int idx, Command c) {
		issueList.getIssueById(idx).update(c);
	}

	/**
	 * Deletes an issue by id
	 * @param id the issue's id
	 */
	public void deleteIssueById(int id) {
		for(int i = 0; i < issueList.getIssues().size(); i++) {
			if(issueList.getIssues().get(i).getIssueId() == id) {
				issueList.getIssues().remove(i);
			}
		}
	}

	/**
	 * Adds an issue to list with passed parameters
	 * @param issueType the issue's type
	 * @param summary the issue's summary
	 * @param note the issue's note
	 */
	public void addIssueToList(IssueType issueType, String summary, String note) {
		issueList.addIssue(issueType, summary, note);
	}
}
