package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * Creates a TaskList given a Task List's name, and number of completed Tasks. This child class will pass information to
 * AbstractTaskList to create TaskList.
 * @author Jake Donovan
 *
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {
	/**
	 * Creates a TaskList by passing given parameters to AbstractTaskList super class
	 * @param taskListName a Task List's name
	 * @param completedCount a Task List's number of completed Tasks
	 */
	public TaskList(String taskListName, int completedCount) {
		super(taskListName, completedCount);
	}
	
	/**
	 * Gets Tasks within TaskList as a 2D String array
	 * @return String[][] a 2D String array containing all Tasks within a TaskList
	 */
	@Override
	public String[][] getTasksAsArray() {
		String[][] tasksAsArray = new String[this.getTasks().size()][2];
		
		for(int i = 0; i < this.getTasks().size(); i++) {
			tasksAsArray[i][0] = Integer.toString(i + 1); // priority
			tasksAsArray[i][1] = this.getTasks().get(i).getTaskName();
		}
		
		return tasksAsArray;
	}
	
	/**
	 * Compares the names of TaskLists to determine positioning of TaskLists within a list of TaskLists (Will be seen in the GUI)
	 * @param taskList a TaskList which will be analyzed by it's name to determine it's positioning within list of TaskLists
	 * @return Integer a value representing whether passed TaskList is placed before (-1), after(1), or it doesn't matter (0) when comparing to other TaskList's names
	 */
	public int compareTo(TaskList taskList) {
		if(this.getTaskListName().compareTo(taskList.getTaskListName()) > 0) {
			return 1;
		}
		
		else if(this.getTaskListName().compareTo(taskList.getTaskListName()) < 0) {
			return -1;
		}
		
		else {
			return 0;
		}
	}
}
