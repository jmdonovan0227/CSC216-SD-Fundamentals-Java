/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the IssueWriter class
 * @author Jake Donovan
 *
 */
class IssueWriterTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.io.IssueWriter#writeIssuesToFile(java.lang.String, java.util.List)}.
	 */
	@Test
	void testWriteIssuesToFile() {
		List<Issue> issueList = new ArrayList<>();
		issueList.add(new Issue(1, IssueType.BUG, "A new issue", "Created for testing"));
		assertEquals(1, issueList.size());

		try {
			IssueWriter.writeIssuesToFile("test-files/testing_issue_writer_act", issueList);
		} catch(Exception e) {
			fail("Cannot write to issue file");
		}

		checkFiles("test-files/testing_issue_writer_exp", "test-files/testing_issue_writer_act");

		Exception e = assertThrows(IllegalArgumentException.class, () -> IssueWriter.writeIssuesToFile("test-file/an_invalid_file", issueList));
		assertEquals("Unable to save file.", e.getMessage());
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
