/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;

/**
 * Tests the functionality of the Task class to make sure everything functions as required
 * @author Jake Donovan
 *
 */
class TaskTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#Task(java.lang.String, java.lang.String, boolean, boolean)}.
	 */
	@Test
	void testTask() {
		Task task = new Task("Name", "Description", false, false);
		assertEquals("Name", task.getTaskName());
		assertEquals("Description", task.getTaskDescription());
		assertFalse(task.isActive());
		assertFalse(task.isRecurring());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#getTaskName()}.
	 */
	@Test
	void testGetTaskName() {
		Task task = new Task("A name", "A description", false, false);
		assertEquals("A name", task.getTaskName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#setTaskName(java.lang.String)}.
	 */
	@Test
	void testSetTaskName() {
		Task task = new Task("A name", "A description", false, false);
		assertEquals("A name", task.getTaskName());
		task.setTaskName("Something else");
		assertEquals("Something else", task.getTaskName());
		Exception e = assertThrows(IllegalArgumentException.class, () -> task.setTaskName(null));
		assertEquals("Incomplete task information.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> task.setTaskName(""));
		assertEquals("Incomplete task information.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#getTaskDescription()}.
	 */
	@Test
	void testGetTaskDescription() {
		Task task = new Task("A new task", "old description", false, false);
		assertEquals("old description", task.getTaskDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#setTaskDescription(java.lang.String)}.
	 */
	@Test
	void testSetTaskDescription() {
		Task task = new Task("A new task", "old description", false, false);
		assertEquals("old description", task.getTaskDescription());
		task.setTaskDescription("new description");
		assertEquals("new description", task.getTaskDescription());
		Exception e = assertThrows(IllegalArgumentException.class, () -> task.setTaskDescription(null));
		assertEquals("Incomplete task information.", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#isRecurring()}.
	 */
	@Test
	void testIsRecurring() {
		Task task = new Task("A task", "description", false, false);
		assertFalse(task.isRecurring());
		Task task2 = new Task("Another task", "description", true, false);
		assertTrue(task2.isRecurring());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#setRecurring(boolean)}.
	 */
	@Test
	void testSetRecurring() {
		Task task = new Task("A task", "description", false, false);
		assertFalse(task.isRecurring());
		task.setRecurring(true);
		assertTrue(task.isRecurring());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#isActive()}.
	 */
	@Test
	void testIsActive() {
		Task task = new Task("A task", "description", false, false);
		assertFalse(task.isActive());
		Task task2 = new Task("Another task", "another description", true, true);
		assertTrue(task2.isRecurring());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#setActive(boolean)}.
	 */
	@Test
	void testSetActive() {
		Task task = new Task("A task", "description", false, false);
		assertFalse(task.isActive());
		task.setActive(true);
		assertTrue(task.isActive());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#getTaskListName()}.
	 */
	@Test
	void testGetTaskListName() {
		Notebook notebook = new Notebook("Notebook");
		TaskList tasklist = new TaskList("List", 0);
		notebook.addTaskList(tasklist);
		Task task = new Task("Task", "Description", false, false);
		task.addTaskList(tasklist);
		assertEquals("List", task.getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#addTaskList(edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList)}.
	 */
	@Test
	void testAddTaskList() {
		Task task = new Task("A task", "description", true, false);
		task.addTaskList(new TaskList("A new list", 0));
		assertEquals("A new list", task.getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#completeTask()}.
	 */
	@Test
	void testCompleteTask() {
		TaskList tasklist = new TaskList("List", 0);
		Task task = new Task("Task", "Description", false, false);
		tasklist.addTask(task);
		assertEquals("List", task.getTaskListName());
		task.completeTask();
		assertEquals(1, tasklist.getCompletedCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#clone()}.
	 */
	@Test
	void testClone() {
		Task task = new Task("A task", "A description", false, false);
		task.addTaskList(new TaskList("A list", 0));
		try {
			 if(task == (Task) task.clone()) {
				 // Wasn't sure how to test this exactly
				 assertEquals("A list", task.getTaskListName());
			 }
		} catch (CloneNotSupportedException e) {
			fail("If we got here something went wrong");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.Task#toString()}.
	 */
	@Test
	void testToString() {
		Task task = new Task("A task", "description", false, false);
		assertEquals("* A task" + "\ndescription", task.toString());
	}

}
