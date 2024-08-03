package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * This Task class contains information about each individual task within WolfTasks program,
 * which includes the task's name, description, whether the task is recurring or not, and if the task is active.
 * Also, this class contains an ISwapList of AbstractTaskLists and this class implements the Cloneable interface as well.
 * @author Jake Donovan
 *
 */
public class Task implements Cloneable {
	/** A task's name */
	private String taskName;
	/** A task's description */
	private String taskDescription;
	/** A boolean which is true or false based on if a Task is recurring or not */
	private boolean recurring;
	/** A boolean which is true or false based on if a Task is active or not */
	private boolean active;
	/** A SwapList of AbstractTaskLists which utilize the SwapList class functionality to be able to move AbstractTaskLists within the GUI*/
	private ISwapList<AbstractTaskList> taskLists;
	
	/**
	 * Constructs a Task with given parameters
	 * @param taskName a Task's name
	 * @param taskDetails a Task's details
	 * @param recurring a boolean which is true if a Task is recurring or false otherwise
	 * @param active a boolean which is true if a Task is active or false otherwise
	 */
	public Task(String taskName, String taskDetails, boolean recurring, boolean active) {
		this.taskLists = new SwapList<AbstractTaskList>();
		setTaskName(taskName);
		setTaskDescription(taskDetails);
		setRecurring(recurring);
		setActive(active);
	}
	
	/**
	 * Returns Task's name
	 * @return taskName the Task's name
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Sets the Task's name
	 * @param taskName a Task's name
	 * @throws IllegalArgumentException if the task name is null or an empty string
	 */
	public void setTaskName(String taskName) {
		if(taskName == null || "".equals(taskName)) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		
		this.taskName = taskName;
	}
	
	/**
	 * Returns a Task's description
	 * @return taskDescription a Task's description
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * Sets a Task's description
	 * @param taskDescription a Task's description
	 * @throws IllegalArgumentException if the task description is null
	 */
	public void setTaskDescription(String taskDescription) {
		if(taskDescription == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		
		this.taskDescription = taskDescription;
	}
	
	/**
	 * Returns true if a true is a Task is recurring otherwise this method will return false
	 * @return recurring true if the Task is recurring otherwise this will return false
	 */
	public boolean isRecurring() {
		return recurring;
	}
	
	/**
	 * Sets a Task's recurring status as boolean
	 * @param recurring a Task's recurring status
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	
	/**
	 * Returns true if a Task is active otherwise this will return false
	 * @return active returns true if a Task is active otherwise this return as false
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets a Task's active status (true or false)
	 * @param active a Task's active status as a boolean (true or false depending on if the Task is active or not)
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets a Task's list name
	 * @return String a Task's list name
	 */
	public String getTaskListName() {
		String returnString = "";
		if(taskLists == null || taskLists.size() == 0) {
			return returnString;
		}
			
		else {
			returnString = taskLists.get(0).getTaskListName();
		}
		
		return returnString;
	}
	
	/**
	 * Adds a task to end of AbstractTaskList if the Task is not already registered within
	 * task list
	 * @param taskList the task list where a Task will be added if valid
	 * @throws IllegalArgumentException if the task list is null
	 */
	public void addTaskList(AbstractTaskList taskList) {
		if(taskList == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		
		boolean duplicate = false;
		
		for(int i = 0; i < taskLists.size(); i++) {
			if(taskLists.get(i) == taskList) {
				duplicate = true;
				break;
			}
		}
		
		if(duplicate) {
			return; // A duplicate was found do not add to list just return
		}
		
		else {
			boolean registered = false; // Check if Task is already registered
			
			for(int i = 0; i < taskLists.size(); i++) {
				if(taskLists.get(i).getTaskListName().equals(taskList.getTaskListName())) {
					registered = true;
				}
			}
			
			if(!registered) {
				//taskList.addTask(this);
				taskLists.add(taskList);
			}
		}
	}
	
	/**
	 * Completes a Task and notifies taskLists by sharing information with current task instance through
	 * TaskList.completeTask(Task)
	 */
	public void completeTask() {
		
		if(this.isRecurring()) {
			//Task clone = null;
			try {
				for(int i = 0; i < taskLists.size(); i++) {
					taskLists.get(i).addTask((Task)this.clone());
				}
			} catch (CloneNotSupportedException e) {
				//
			}
		}
		
		Task taskR = null;
		
		for(int i = 0; i < taskLists.size(); i++) {
			for(int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
				if(taskLists.get(i).getTask(j).getTaskName().equals(this.getTaskName())) {
					taskR = taskLists.get(i).getTask(j);
					taskLists.get(i).completeTask(taskR);
					break;
					//taskLists.get(i).removeTask(j);
				}
			}
		}
	}
	
	/**
	 * Returns a copy of Task
	 * @throws CloneNotSupportedException if an AbstractTaskList is registered with Task, thus it cannot be cloned
	 * @return Object a cloned Task object
	 */
	public Object clone() throws CloneNotSupportedException {
		taskLists.size();
		if(taskLists == null || taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		
		return new Task(getTaskName(), getTaskDescription(), isRecurring(), isActive());
	}
	
	/**
	 * Returns a String representation of of Task for printing to a file
	 * @return String the String representation of Task class
	 */
	public String toString() {
		if(isRecurring() && isActive()) {
			return "* " + getTaskName() + "," + "recurring" + "," + "active" + "\n" + getTaskDescription();
		}
		
		else if(isRecurring() && !isActive()) {
			return "* " + getTaskName() + "," + "recurring" + "\n" + getTaskDescription();
		}
		
		else if(isActive() && !isRecurring()) {
			return "* " + getTaskName() + "," + "active" + "\n" + getTaskDescription();
		}
		
		return "* " + getTaskName() + "\n" + getTaskDescription();
	}
}
