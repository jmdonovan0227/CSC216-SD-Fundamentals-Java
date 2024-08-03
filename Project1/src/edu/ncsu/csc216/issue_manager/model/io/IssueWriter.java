package edu.ncsu.csc216.issue_manager.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * This class is responsible for writing Issues to a file which will be created through
 * IssueManagerGUI and passed as fileName to this class and saved to the passed file name
 * @author Jake Donovan
 *
 */
public class IssueWriter {

	/**
	 * Writes issues to a file with a passed fileName and a list of issues
	 * @param fileName the file where the issues will be saved
	 * @param issueList a list of issues created by the user
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public static void writeIssuesToFile(String fileName, List<Issue> issueList) {
		PrintStream fileWriter = null;

		try {
			fileWriter = new PrintStream(new File(fileName));

			if(fileWriter != null) {
				for(int i = 0; i < issueList.size(); i++) {
					fileWriter.println("*" + issueList.get(i).toString());
					fileWriter.print(issueList.get(i).getNotesString());
				}
			}
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
