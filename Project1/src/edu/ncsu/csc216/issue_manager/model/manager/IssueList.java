package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * This class is responsible for maintaining a list of issues
 * @author Jake Donovan
 *
 */
public class IssueList {
	/** A list of issues */
	private List<Issue> issues;
	/** The counter for the number of issues in the issue list*/
	private int counter;

	/**
	 * The issue list class constructor
	 */
	public IssueList() {
		this.issues = new ArrayList<>();
		this.counter = 1;
	}

	/**
	 * Adds an issue to issue list
	 * @param issueType the issue type
	 * @param summary the issue's summary
	 * @param note the issue's note
	 * @return Integer the index where the issue was added
	 */
	public int addIssue(IssueType issueType, String summary, String note) {
		int id = counter;
		Issue issue = new Issue(id, issueType, summary, note);
		addIssue(issue);
		counter = counter + 1;
		return id;
	}

	/**
	 * Adds issues to an issue list
	 * @param issueList the passed issue list
	 */
	public void addIssues(List<Issue> issueList) {
		issues = new ArrayList<Issue>();
		
		for(int i = 0; i < issueList.size(); i++) {
			addIssue(issueList.get(i));
		}
	}

	/**
	 * Adds an issue with a passed issue
	 * @param issue the passed issue
	 */
	private void addIssue(Issue issue) {
		boolean invalidIssue = false;

		for (int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueId() == issue.getIssueId()) {
				invalidIssue = true;
				break;
			}
		}

		if(invalidIssue) {
			return;
		}

		else {
			if(issues.size() == 0) {
				issues.add(issue);
				return;
			}

			else {
				for(int i = 0; i < issues.size(); i++) {
					if(i == issues.size() - 1 && issue.getIssueId() > issues.get(i).getIssueId()){
						issues.add(issue); // add at end if we are at size and issue id of the passed issue is still greater than the final element in list
						break;
					}

					else if(issue.getIssueId() < issues.get(i).getIssueId()) {
						issues.add(i, issue); // as soon as we find the id where the passed issue id is less, add issue at index and push bigger id to right
						break;
					}
				}

				return;
			}
		}
	}

	/**
	 * Gets the list of issues
	 * @return issues the list of issues
	 */
	public List<Issue> getIssues(){
		return issues;
	}

	/**
	 * Gets issues by type
	 * @param issueType the issue's type
	 * @throws IllegalArgumentException if issue type is an empty string or null
	 * @return issuesByType a list of issues by type
	 */
	public List<Issue> getIssuesByType(String issueType){
		if("".equals(issueType) || issueType == null) {
			throw new IllegalArgumentException("Issue type does not exist");
		}
			List<Issue> issuesByType = new ArrayList<>();

		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueType().equals(issueType)) {
				issuesByType.add(issues.get(i));
			}
		}
		
		return issuesByType;
	}

	/**
	 * Gets issues by id
	 * @param id the issue's id
	 * @return issue the returned issue
	 */
	public Issue getIssueById(int id) {
		Issue issue = null;
		
		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueId() == id) {
				issue = issues.get(i);
				break;
			}
		}

		return issue;
	}

	/**
	 * Executes a Command on an issue
	 * @param id the issue's id
	 * @param c the passed Command value
	 */
	public void executeCommand(int id, Command c) {
		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueId() == id) {
				issues.get(i).update(c);
				break;
			}
		}
	}

	/**
	 * Deletes an issue by id
	 * @param id the issue's id
	 */
	public void deleteIssueById(int id) {
		for(int i = 0; i < issues.size(); i++) {
			if(issues.get(i).getIssueId() == id) {
				issues.remove(i);
				break;
			}
		}
	}
}
