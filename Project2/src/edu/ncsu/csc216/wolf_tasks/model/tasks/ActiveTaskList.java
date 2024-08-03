package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * This class holds all active Tasks in currently created Notebook within GUI (Notebook being edited currently)
 * @author Jake Donovan
 *
 */
public class ActiveTaskList extends AbstractTaskList {
	/** A String constant representing the name of ActiveTaskList */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * Constructs the ActiveTaskList by passing it's name and 0 which represents that a new ActiveTaskList does not currently have completed Tasks
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}
	
	/**
	 * Overrides AbstractTaskList class addTask method to check that a Task is active before adding to ActiveTaskList
	 * @param t a Task to be added to ActiveTaskList (if valid)
	 * @throws IllegalArgumentException if the Task that is trying to be added to ActiveTaskList is not an active task
	 */
	public void addTask(Task t) {
		if(!t.isActive()) {
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
		}
		
		//t.addTaskList(new TaskList(super.getTaskListName(), super.getCompletedCount()));
		super.addTask(t);
	}
	
	/**
	 * Sets ActiveTaskList's name which can only be Active Tasks
	 * @param taskListName the ActiveTaskList's name
	 * @throws IllegalArgumentException if the passed name is not "Active Tasks"
	 */
	public void setTaskListName(String taskListName) {
		if(ACTIVE_TASKS_NAME.equals(taskListName)) {
			super.setTaskListName(taskListName);
		}
		
		else {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
	}
	
	/**
	 * Gets Tasks within ActiveTaskList as a 2D String array
	 * @return String[][] gets Tasks within ActiveTaskList as a 2D String array
	 */
	@Override
	public String[][] getTasksAsArray() {
		String[][] activeTasksAsArray = new String[this.getTasks().size()][2];
		for(int i = 0; i < this.getTasks().size(); i++) {
			activeTasksAsArray[i][0] = this.getTasks().get(i).getTaskListName();
			activeTasksAsArray[i][1] = this.getTasks().get(i).getTaskName();
		}
		
		return activeTasksAsArray;
	}
	
	/**
	 * Clears all Tasks within ActiveTaskList
	 */
	public void clearTasks() {
		for(int i = super.getTasks().size() - 1; i >= 0; i--) {
			super.removeTask(i);
		}
	}
}
