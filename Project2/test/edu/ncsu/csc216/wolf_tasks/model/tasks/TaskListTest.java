/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the TaskList class to make sure everything functions as required
 * @author Jake Donovan
 *
 */
class TaskListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList#getTasksAsArray()}.
	 */
	@Test
	void testGetTasksAsArray() {
		TaskList taskList = new TaskList("Test", 0);
		taskList.addTask(new Task("A task", "description", false, false));
		assertEquals(1, taskList.getTasks().size());
		assertEquals("1", taskList.getTasksAsArray()[0][0]);
		assertEquals("A task", taskList.getTasksAsArray()[0][1]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList#TaskList(java.lang.String, int)}.
	 */
	@Test
	void testTaskList() {
		TaskList t = new TaskList("A name", 3);
		Task one = new Task("Task 1", "Task 1 Description", false, true);
		Task two = new Task("Task 2", "Task 2 Description", true, true);
		Task three = new Task("Task 3", "Task 3 Description", true, true);
		Task five = new Task("Task 5", "Task 5 Description", false, true);
		Task four = new Task("Task 4", "Task 4 Description", true, true);
		t.addTask(one);
		t.addTask(two);
		t.addTask(three);
		t.addTask(four);
		t.addTask(five);
		one.completeTask();
		two.completeTask();
		//t.completeTask(three);
		//t.completeTask(five);
		assertEquals(5, t.getCompletedCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList#compareTo(edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList)}.
	 */
	@Test
	void testCompareTo() {
		TaskList b = new TaskList("B", 0);
		TaskList a = new TaskList("A", 0);
		assertEquals(-1, a.compareTo(b));
	}

}
