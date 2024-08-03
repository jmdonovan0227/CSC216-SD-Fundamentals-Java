package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * An abstract class at the top of hierarchy for task lists. The AbstractTaskList knows
 * a task list's name, ISwapList of Tasks, and the number of completed tasks
 * @author Jake Donovan
 *
 */
public abstract class AbstractTaskList {
	/** A Task list's name*/
	private String taskListName;
	/** A Task list's number of completed Tasks*/
	private int completedCount;
	/** A list of Tasks that can be moved within GUI to give priority to Tasks through SwapList class functionality */
	private ISwapList<Task> tasks;
	
	/**
	 * Constructs an AbstractTaskList with given parameters
	 * @param taskListName a Task List's name
	 * @param completedCount a Task List's number of completed Tasks
	 */
	public AbstractTaskList(String taskListName, int completedCount) {
		this.tasks = new SwapList<Task>();
		
		if(completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		
		this.completedCount = completedCount;
		setTaskListName(taskListName);
	}
	
	/**
	 * Returns a Task List's name
	 * @return taskListName a Task List's name
	 */
	public String getTaskListName() {
		return taskListName;
	}
	
	/**
	 * Sets a Task List's name with passed parameter
	 * @param taskListName a Task List's name
	 * @throws IllegalArgumentException if the task list name is null or an empty string
	 */
	public void setTaskListName(String taskListName) {
		if(taskListName == null || "".equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.taskListName = taskListName;
	}
	
	/**
	 * Returns ISwapList of Tasks
	 * @return tasks a list of Tasks that can be moved (prioritize) through GUI using SwapList class functionality
	 */
	public ISwapList<Task> getTasks(){
		return tasks;
	}
	
	/**
	 * Returns number of completed Tasks
	 * @return completedCount the number of completed Tasks
	 */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	 * Adds Task to end of list
	 * @param t a Task to be added to list
	 */
	public void addTask(Task t) {
		tasks.add(t);
		//AbstractTaskList list = new TaskList(taskListName, completedCount);
		t.addTaskList(this);
	}
	
	/**
	 * Removes a Task from the list using a passed index and returns the removed Task object
	 * @param idx the index of the Task to be removed
	 * @return Task a Task that has been removed from list
	 */
	public Task removeTask(int idx) {
		Task task = tasks.get(idx);
		
		try {
			tasks.remove(idx);
		} catch(Exception e) {
			//
		}
		
		return task;
	}
	
	/**
	 * Gets a Task within list with a passed index
	 * @param idx the index of Task to be retrieved from list
	 * @return Task a Task at a given index to be retrieved from list
	 */
	public Task getTask(int idx) {
		return tasks.get(idx);
	}
	
	/**
	 * Finds the given Task and removes it. The completed count is incremented.
	 * @param t the Task that will be completed
	 */
	public void completeTask(Task t) {
		int indexRemove = 0;
		boolean found = false;
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getTaskName().equals(t.getTaskName())){
				indexRemove = i;
				found = true;
				break;
			}
		}
		
		if(found) {
			for(int i = 0; i < tasks.size(); i++) {
				if(i == indexRemove) {
					tasks.remove(i);
					completedCount++;
					break;
				}
			}
		}
	}
	
	/**
	 * An abstract method that returns a 2D String array of Tasks as defined in subclasses
	 * @return String[][] a 2D String array representation of Tasks within list, which is implemented by subclasses (aka child classes)
	 */
	public abstract String[][] getTasksAsArray();
}
