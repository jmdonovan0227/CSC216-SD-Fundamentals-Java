/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;

/**
 * Tests the functionality of the NotebookReader class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class NotebookReaderTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.io.NotebookReader#readNotebookFile(java.io.File)}.
	 */
	@Test
	void testReadNotebookFile() {
		try {
		Notebook notebook0 = NotebookReader.readNotebookFile(new File("test-files/notebook0.txt"));
		assertEquals(1, notebook0.getTaskListsNames().length);
		Notebook notebook1 = NotebookReader.readNotebookFile(new File("test-files/notebook1.txt"));
		assertEquals(4, notebook1.getTaskListsNames().length);
		Notebook notebook2 = NotebookReader.readNotebookFile(new File("test-files/notebook2.txt"));
		assertEquals(4, notebook2.getTaskListsNames().length);
		Notebook notebook5 = NotebookReader.readNotebookFile(new File("test-files/notebook5.txt"));
		assertEquals(1, notebook5.getTaskListsNames().length);
		Notebook notebook6 = NotebookReader.readNotebookFile(new File("test-files/notebook6.txt"));
		assertEquals(1, notebook6.getTaskListsNames().length);
		//Notebook notebook7 = NotebookReader.readNotebookFile(new File("test-files/notebook7.txt"));
		//assertEquals(2, notebook7.getTaskListsNames().length);
		//Notebook notebook4 = NotebookReader.readNotebookFile(new File("test-files/notebook4.txt"));
		//assertEquals(1, notebook4.getTaskListsNames().length);
		}
		catch(Exception e) {
			fail("Unexpected Exception was thrown");
		}
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> NotebookReader.readNotebookFile(new File("test-files/notebook3.txt")));
		assertEquals("Unable to load file.", e.getMessage());
	}

}
