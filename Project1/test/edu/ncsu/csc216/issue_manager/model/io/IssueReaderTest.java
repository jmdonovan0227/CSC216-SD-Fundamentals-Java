/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests the IssueReader class
 * @author Jake Donovan
 *
 */
class IssueReaderTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.io.IssueReader#readIssuesFromFile(java.lang.String)}.
	 */
	@Test
	void testReadIssuesFromFile() {
		List<Issue> issueList = IssueReader.readIssuesFromFile("test-files/issue1.txt");
		assertEquals(5, issueList.size());
		Exception e = assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue3.txt"));
		assertEquals("Unable to load file.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue4.txt"));
		assertEquals("Unable to load file.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue10.txt"));
		assertEquals("Unable to load file.", e3.getMessage());
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue11.txt"));
		assertEquals("Unable to load file.", e4.getMessage());
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue12.txt"));
		assertEquals("Unable to load file.", e5.getMessage());
	}

}
