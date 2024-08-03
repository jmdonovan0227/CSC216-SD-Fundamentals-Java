/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests the Notebook class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class NotebookTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#Notebook(java.lang.String)}.
	 */
	@Test
	void testNotebook() {
		Notebook notebook = new Notebook("Notebook");
		assertEquals(ActiveTaskList.ACTIVE_TASKS_NAME, notebook.getCurrentTaskList().getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#saveNotebook(java.io.File)}.
	 */
	@Test
	void testSaveNotebook() {
		Notebook notebook = new Notebook("Notebook");
		assertTrue(notebook.isChanged());
		notebook.saveNotebook(new File("test-files/test.txt"));
		assertFalse(notebook.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#getNotebookName()}.
	 */
	@Test
	void testGetNotebookName() {
		Notebook notebook = new Notebook("Notebook");
		assertEquals("Notebook", notebook.getNotebookName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#isChanged()}.
	 */
	@Test
	void testIsChanged() {
		Notebook notebook = new Notebook("Notebook");
		assertTrue(notebook.isChanged());
		notebook.addTaskList(new TaskList("TaskList", 0));
		notebook.saveNotebook(new File("test-files/test.txt"));
		assertFalse(notebook.isChanged());
		notebook.addTask(new Task("Task", "Description", false, false));
		assertTrue(notebook.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#setChanged(boolean)}.
	 */
	@Test
	void testSetChanged() {
		Notebook notebook = new Notebook("Notebook");
		notebook.setChanged(false);
		assertFalse(notebook.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#addTaskList(edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList)}.
	 */
	@Test
	void testAddTaskList() {
		Notebook notebook = new Notebook("Notebook");
		Exception e = assertThrows(IllegalArgumentException.class, () -> notebook.addTaskList(new TaskList("Active Tasks", 0)));
		assertEquals("Invalid name.", e.getMessage());
		notebook.addTaskList(new TaskList("Test", 0));
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> notebook.addTaskList(new TaskList("Test", 0)));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#getTaskListsNames()}.
	 */
	@Test
	void testGetTaskListsNames() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("Test", 0));
		assertEquals("Active Tasks", notebook.getTaskListsNames()[0]);
		assertEquals("Test", notebook.getTaskListsNames()[1]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#setCurrentTaskList(java.lang.String)}.
	 */
	@Test
	void testSetCurrentTaskList() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("Test", 0));
		notebook.addTaskList(new TaskList("Another test", 0));
		assertEquals("Another test", notebook.getCurrentTaskList().getTaskListName());
		notebook.setCurrentTaskList("Test");
		assertEquals("Test", notebook.getCurrentTaskList().getTaskListName());
		notebook.setCurrentTaskList("Doesn't exist");
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#getCurrentTaskList()}.
	 */
	@Test
	void testGetCurrentTaskList() {
		Notebook notebook = new Notebook("Notebook");
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		notebook.addTaskList(new TaskList("A new tasklist", 0));
		assertEquals("A new tasklist", notebook.getCurrentTaskList().getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#editTaskList(java.lang.String)}.
	 */
	@Test
	void testEditTaskList() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("Test", 0));
		assertEquals("Test", notebook.getCurrentTaskList().getTaskListName());
		notebook.editTaskList("New Name");
		assertEquals("New Name", notebook.getCurrentTaskList().getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#removeTaskList()}.
	 */
	@Test
	void testRemoveTaskList() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("Test", 0));
		assertEquals("Test", notebook.getCurrentTaskList().getTaskListName());
		notebook.removeTaskList();
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#addTask(edu.ncsu.csc216.wolf_tasks.model.tasks.Task)}.
	 */
	@Test
	void testAddTask() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("Test", 0));
		assertEquals("Test", notebook.getCurrentTaskList().getTaskListName());
		notebook.removeTaskList();
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		notebook.addTaskList(new TaskList("Bob", 0));
		notebook.addTask(new Task("A task", "description", false, false));
		assertEquals(1, notebook.getCurrentTaskList().getTasks().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook#editTask(int, java.lang.String, java.lang.String, boolean, boolean)}.
	 */
	@Test
	void testEditTask() {
		Notebook notebook = new Notebook("Notebook");
		notebook.addTaskList(new TaskList("A list", 0));
		notebook.addTask(new Task("A task", "description", true, true));
		assertEquals("A task", notebook.getCurrentTaskList().getTask(0).getTaskName());
		notebook.editTask(0, "Bobby", "A new description", false, false);
		assertEquals("Bobby", notebook.getCurrentTaskList().getTask(0).getTaskName());
	}

}
