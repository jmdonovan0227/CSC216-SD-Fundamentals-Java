/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the AbstractTaskList class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class AbstractTaskListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#AbstractTaskList(java.lang.String, int)}.
	 */
	@Test
	void testAbstractTaskList() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TaskList("Test", -1));
		assertEquals("Invalid completed count.", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#getTaskListName()}.
	 */
	@Test
	void testGetTaskListName() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		assertEquals("Test", list.getTaskListName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#setTaskListName(java.lang.String)}.
	 */
	@Test
	void testSetTaskListName() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		Exception e = assertThrows(IllegalArgumentException.class, () -> new TaskList("", 0));
		assertEquals("Invalid name.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new TaskList(null, 0));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#getTasks()}.
	 */
	@Test
	void testGetTasks() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		list.addTask(new Task("A task", "description", false, false));
		assertEquals(1, list.getTasks().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#getCompletedCount()}.
	 */
	@Test
	void testGetCompletedCount() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		assertEquals(0, list.getCompletedCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#addTask(edu.ncsu.csc216.wolf_tasks.model.tasks.Task)}.
	 */
	@Test
	void testAddTask() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		list.addTask(new Task("A task", "description", false, false));
		assertEquals(1, list.getTasks().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#removeTask(int)}.
	 */
	@Test
	void testRemoveTask() {
		AbstractTaskList list = new TaskList("Test", 0);
		assertEquals(0, list.getTasks().size());
		list.addTask(new Task("A task", "description", false, false));
		assertEquals(1, list.getTasks().size());
		list.removeTask(0);
		assertEquals(0, list.getTasks().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#getTask(int)}.
	 */
	@Test
	void testGetTask() {
		Task task = new Task("A task", "description", true, false);
		TaskList taskList = new TaskList("A list", 0);
		taskList.addTask(task);
		assertEquals("A task", taskList.getTask(0).getTaskName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#completeTask(edu.ncsu.csc216.wolf_tasks.model.tasks.Task)}.
	 */
	@Test
	void testCompleteTask() {
		TaskList taskList = new TaskList("A new list", 0);
		assertEquals(0, taskList.getCompletedCount());
		taskList.addTask(new Task("A task", "description", false, false));
		taskList.getTask(0).completeTask();
		assertEquals(1, taskList.getCompletedCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList#getTasksAsArray()}.
	 */
	@Test
	void testGetTasksAsArray() {
		TaskList taskList = new TaskList("A task list", 0);
		taskList.addTask(new Task("A task", "description", false, true));
		assertEquals("1", taskList.getTasksAsArray()[0][0]);
		assertEquals("A task", taskList.getTasksAsArray()[0][1]);
	}
}
