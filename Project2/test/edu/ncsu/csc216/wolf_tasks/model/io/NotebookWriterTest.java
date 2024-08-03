/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Tests the NotebookWriter class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class NotebookWriterTest {
	
	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter#writeNotebookFile(java.io.File, java.lang.String, edu.ncsu.csc216.wolf_tasks.model.util.ISortedList)}.
	 */
	@Test
	void testWriteNotebookFile() {
		ISortedList<TaskList> taskLists = new SortedList<TaskList>();
		TaskList aTaskList = new TaskList("ATaskList", 0);
		taskLists.add(aTaskList);
		TaskList tasks1 = new TaskList("Tasks1", 0);
		tasks1.addTask(new Task("Task1", "Task1Description", true, false));
		tasks1.addTask(new Task("Task2", "Task2Description", true, true));
		taskLists.add(tasks1);
		TaskList tasks2 = new TaskList("Tasks2", 0);
		tasks2.addTask(new Task("Task3", "Task3Description", false, false));
		tasks2.addTask(new Task("Task4", "Task4Description", false, true));
		tasks2.addTask(new Task("Task5", "Task5Description", true, false));
		taskLists.add(tasks2);
		assertEquals(3, taskLists.size());
		
		try {
			NotebookWriter.writeNotebookFile(new File("test-files/actual_out.txt"), "Notebook", taskLists);
		} catch(Exception e) {
			fail("Unexpected error");
		}
		
		checkFiles("test-files/actual_out.txt", "test-files/expected_out.txt");
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> NotebookWriter.writeNotebookFile(new File("test-file/an_invalid_file"), "NotebookTest", taskLists));
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
