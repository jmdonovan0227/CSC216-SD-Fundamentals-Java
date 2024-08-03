/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the ActiveTaskList class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class ActiveTaskListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList#setTaskListName(java.lang.String)}.
	 */
	@Test
	void testSetTaskListName() {
		ActiveTaskList list = new ActiveTaskList();
		list.setTaskListName("Active Tasks");
		assertEquals("Active Tasks", list.getTaskListName());
		Exception e = assertThrows(IllegalArgumentException.class, () -> list.setTaskListName("A new list"));
		assertEquals("The Active Tasks list may not be edited.", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList#addTask(edu.ncsu.csc216.wolf_tasks.model.tasks.Task)}.
	 */
	@Test
	void testAddTask() {
		ActiveTaskList list = new ActiveTaskList();
		Task task = new Task("A task", "Description", false, true);
		list.addTask(task);
		assertEquals(1, list.getTasks().size());
		Task task2 = new Task("A task", "Description", false, false);
		Exception e = assertThrows(IllegalArgumentException.class, () -> list.addTask(task2));
		assertEquals("Cannot add task to Active Tasks.", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList#getTasksAsArray()}.
	 */
	@Test
	void testGetTasksAsArray() {
		ActiveTaskList list = new ActiveTaskList();
		Task task = new Task("A task", "Description", false, true);
		list.addTask(task);
		assertEquals(1, list.getTasks().size());
		assertEquals("Active Tasks", list.getTasksAsArray()[0][0]);
		assertEquals("A task", list.getTasksAsArray()[0][1]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList#ActiveTaskList()}.
	 */
	@Test
	void testActiveTaskList() {
		ActiveTaskList list = new ActiveTaskList();
		list.addTask(new Task("Task 1", "Task 1 Description", false, true));
		list.addTask(new Task("Task 2", "Task 2 Description", true, true));
		list.addTask(new Task("Task 3", "Task 3 Description", true, true));
		list.addTask(new Task("Task 4", "Task 4 Description", true, true));
		list.addTask(new Task("Task 5", "Task 5 Description", false, true));
		Task task = new Task("Task 6", "description", true, true);
		Task task2 = new Task("Task 7", "desc", true, true);
		list.addTask(task);
		list.addTask(task2);
		task.completeTask();
		task2.completeTask();
		list.getCompletedCount();
		//assertEquals(1, list.getCompletedCount());
		//list.completeTask(list.getTask(4));
		
		
		ActiveTaskList two = new ActiveTaskList();
		Task three = new Task("Task 3", "Task 3 Description", true, true);
		Task five = new Task("Task 5", "Task 5 Description", false, true);
		two.addTask(new Task("Task 1", "Task 1 Description", false, true));
		two.addTask(new Task("Task 2", "Task 2 Description", true, true));
		two.addTask(three);
		two.addTask(new Task("Task 4", "Task 4 Description", true, true));
		two.addTask(five);
		two.completeTask(five);
		two.completeTask(three);
		assertEquals("Task 4", two.getTasksAsArray()[2][1]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList#clearTasks()}.
	 */
	@Test
	void testClearTasks() {
		ActiveTaskList list = new ActiveTaskList();
		Task task = new Task("A task", "Description", false, true);
		list.addTask(task);
		assertEquals(1, list.getTasks().size());
		assertEquals("Active Tasks", list.getTasksAsArray()[0][0]);
		assertEquals("A task", list.getTasksAsArray()[0][1]);
		list.completeTask(task);
		assertEquals(1, list.getCompletedCount());
		Task test = new Task("Another task", "Another Description", false, true);
		list.addTask(test);
		assertEquals(1, list.getTasks().size());
		list.clearTasks();
		assertEquals(0, list.getTasks().size());
	}

}
