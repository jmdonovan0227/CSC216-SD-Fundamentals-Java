/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * This class is responsible for reading an IssueList from a file and returning
 * to the IssueManager program in order to be able to edit in our IssueManagerGUI application
 * @author Jake Donovan
 *
 */
public class IssueReader {

	/**
	 * Reads and returns a list of issues from a file
	 * @param fileName the file that will be read from
	 * @throws IllegalArgumentException if File cannot be loaded
	 * @return issueList a list of issues read from a file
	 */
	public static ArrayList<Issue> readIssuesFromFile(String fileName) {
		Scanner scanFileContents = null;
		String fileContents = new String();
		ArrayList<Issue> allIssues = null;

		try {
			scanFileContents = new Scanner(new FileInputStream(fileName));

			while(scanFileContents.hasNextLine()) {
				String fileInfo = scanFileContents.nextLine();
				fileInfo = fileInfo.concat("\n");
				fileContents += fileInfo;
			}

		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		Scanner breakApartFileContents = null;
		
		try {
			breakApartFileContents = new Scanner(fileContents);
		} catch(Exception e) {
			// If there was an issue we go here
		}

		if(breakApartFileContents != null) {
			allIssues = new ArrayList<>(); // If a FileNotFoundException was not thrown construct allIssue ArrayList of Issues
			breakApartFileContents.useDelimiter("\\r?\\n?[*]"); // Breaks apart an individual Issue including notes, and reads
			// from file until it hits another Issue
			while(breakApartFileContents.hasNext()) { // While there is another Issue in file
				String anIssueAsAString = breakApartFileContents.next();
				Issue issue = processIssue(anIssueAsAString);

				if(issue != null) {
					allIssues.add(issue);
				}
			}
		}

		return allIssues; // If we got here and the file contents had valid Issues, we return them here otherwise if there were no file issues, this will return with a size of 0
	}

	/**
	 * Helps readIssuesFromFile method to break issue lists into individual issues
	 * which will be returned to readIssueFromFile and added to an ArrayList of Issue that can
	 * be used in this program
	 * @param issueString an issue passed from readIssueFromFile method
	 * @return issue an issue read from a file
	 */
	private static Issue processIssue(String issueString) {
		Issue issue = null;
		
		Scanner checkCommas = new Scanner(issueString);
		String testIssue = checkCommas.nextLine();
		checkCommas.close();
		
		boolean commas = false;
		int counter = 0;
		for(int i = 0; i < testIssue.length(); i++) {
			if(issueString.charAt(i) == ',') {
				counter++;
			}
		}
		
		if(counter < 6 || counter > 6) {
			commas = true;
		}
		
		if(commas) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		Scanner scanIssue = new Scanner(issueString);
		scanIssue.useDelimiter("\n"); // Might have to use next line depending on how this works
		String issueInfo = scanIssue.next().trim();
		scanIssue.close();
		Scanner scanIssuePieces = new Scanner(issueInfo);
		scanIssuePieces.useDelimiter(",");
		int id = 0;
		String state = "";
		String type = "";
		String summary = "";
		String owner = "";
		String confirmed = "";
		String resolution = "";

		id = Integer.valueOf(scanIssuePieces.next().trim());

		if(id <= 0) { // Invalid id return null
			scanIssuePieces.close();
			return issue;
		}

		state = scanIssuePieces.next().trim();

		if(!state.equals(Issue.NEW_NAME) && !state.equals(Issue.WORKING_NAME) && !state.equals(Issue.VERIFYING_NAME) && !state.equals(Issue.CONFIRMED_NAME) && !state.equals(Issue.CLOSED_NAME)) {
			// A valid state name was not found
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		type = scanIssuePieces.next().trim();

		if(!type.equals(Issue.I_BUG) && !type.equals(Issue.I_ENHANCEMENT)) {
			// A valid issue type was not found
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		summary = scanIssuePieces.next().trim();

		owner = scanIssuePieces.next().trim();

		if(state.equals(Issue.NEW_NAME) && !"".equals(owner)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		else if(state.equals(Issue.CONFIRMED_NAME) && !"".equals(owner)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		else if(state.equals(Issue.WORKING_NAME) && "".equals(owner)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		else if(state.equals(Issue.VERIFYING_NAME) && "".equals(owner)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		if(type.equals(Issue.I_ENHANCEMENT) && state.equals(Issue.CONFIRMED_NAME)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		confirmed = scanIssuePieces.next().trim();

		if(!"true".equals(confirmed) && !"false".equals(confirmed) || Issue.I_ENHANCEMENT.equals(type) && "true".equals(confirmed)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		if(type.equals(Issue.I_BUG) && state.equals(Issue.WORKING_NAME) && !"true".equals(confirmed)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		if(scanIssuePieces.hasNext()) {
			resolution = scanIssuePieces.next().trim();
		}

		if(state.equals(Issue.VERIFYING_NAME) && !resolution.equals(Command.R_FIXED)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		else if(state.equals(Issue.CLOSED_NAME) && !resolution.equals(Command.R_DUPLICATE) && !resolution.equals(Command.R_FIXED)
				&& !resolution.equals(Command.R_WONTFIX) && !resolution.equals(Command.R_WORKSFORME)) {
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}

		Scanner notesScanner = new Scanner(issueString);
		notesScanner.useDelimiter("\\r?\\n?[-]");
		ArrayList<String> notes = new ArrayList<>();

		while(notesScanner.hasNext()) {
			String note = notesScanner.next().trim();

			if(note.contains("[") && note.contains("]")) {
				String checkNote = note.substring(note.indexOf("["), note.indexOf(" "));
				checkNote = checkNote.replace("[", "");
				checkNote = checkNote.replace("]", "");

				if(!checkNote.equals(Issue.NEW_NAME) && !checkNote.equals(Issue.WORKING_NAME) && !checkNote.equals(Issue.CONFIRMED_NAME) && !checkNote.equals(Issue.VERIFYING_NAME)
						&& !checkNote.equals(Issue.CLOSED_NAME)) {
					continue;
				}

				else {
					notes.add(note);
				}
			}

			else {
				continue;
			}
		}

		notesScanner.close();

		if(notes.isEmpty()){
			scanIssuePieces.close();
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		else {
			boolean confirmedAsBoolean = Boolean.valueOf(confirmed);
			int idAsInteger = Integer.valueOf(id);
			issue = new Issue(idAsInteger, state, type, summary, owner, confirmedAsBoolean, resolution, notes);
		}

		scanIssuePieces.close();

		return issue;
	}
}
