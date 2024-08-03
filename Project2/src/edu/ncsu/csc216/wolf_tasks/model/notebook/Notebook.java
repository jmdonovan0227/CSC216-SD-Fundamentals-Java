package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Notebook class is responsible for creating new Notebooks and keeping track of 
 * tasklists, the active task list (which one is the current task list being used), the notebook's name,
 * and a boolean that is true or false based on if the notebook has been changed or not
 * @author Jake Donovan
 *
 */
public class Notebook {
	/** The Notebook's name */
	private String notebookName;
	/** A boolean which is true if the Notebook has been edited, otherwise is false */
	private boolean isChanged;
	/** A list of Tasks that utilizes SortedList class functionality to correctly sort TaskLists */
	private ISortedList<TaskList> taskLists;
	/** An instance of the ActiveTaskList connected to current Notebook (Notebook that is being edited) */
	private ActiveTaskList activeTaskList;
	/** An instance of the current TaskList being used with Notebook in WolfTasksGUI */
	private AbstractTaskList currentTaskList;
	
	/**
	 * Constructs a Notebook with passed Notebook name
	 * @param noteBookName a Notebook's name
	 */
	public Notebook(String noteBookName) {
		setNotebookName(noteBookName);
		this.taskLists = new SortedList<TaskList>();
		activeTaskList = new ActiveTaskList();
		currentTaskList = activeTaskList;
		this.isChanged = true;
	}
	
	/**
	 * Saves a Notebook to passed file name if the file is valid
	 * @param notebookFile a passed file name where a Notebook will be saved
	 */
	public void saveNotebook(File notebookFile) {
		try {
			NotebookWriter.writeNotebookFile(notebookFile, notebookName, taskLists);
			isChanged = false;
		} catch(Exception e) {
			//
		}
	}
	
	/**
	 * Returns Notebook name
	 * @return notebookName the Notebook's name
	 */
	public String getNotebookName() {
		return notebookName;
	}
	
	/**
	 * Sets the Notebook's name
	 * @param notebookName the Notebook's name
	 * @throws IllegalArgumentException if the notebook name is null an empty string or equal to active tasks name
	 */
	private void setNotebookName(String notebookName) {
		if(notebookName == null || "".equals(notebookName) || ActiveTaskList.ACTIVE_TASKS_NAME.equals(notebookName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.notebookName = notebookName;
	}
	
	/**
	 * Returns true if Notebook has been changed otherwise will return false 
	 * @return isChanged a boolean which will return true if the Notebook has been edited otherwise will return false
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Sets boolean that is related to the current status of the Notebook meaning whether it has been edited or not
	 * @param isChanged a boolean that is true if the Notebook has been edited, otherwise will be false
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
	 * Adds TaskList by passing a given TaskList object
	 * @param taskList the TaskList that will be created within current Notebook (if valid)
	 * @throws IllegalArgumentException if the passed taskList has the same name as a Active Tasks list or is a duplicate tasklist
	 */
	public void addTaskList(TaskList taskList) {
		if(taskList.getTaskListName().equals(ActiveTaskList.ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		boolean duplicate = false;
		
		for(int i = 0; i < taskLists.size(); i++) {
			if(taskLists.get(i).getTaskListName().equals(taskList.getTaskListName())) {
				duplicate = true;
				break;
			}
		}
		
		if(duplicate) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		else {
			taskLists.add(taskList);
			currentTaskList = taskList;
			isChanged = true;
			
			if(taskList.getTasks().size() != 0) {
				getActiveTaskList();
			}
		}
	}
	
	/**
	 * Gets all TaskLists within current Notebook as a String array
	 * @return String[] all TaskLists within current Notebook as String array
	 */
	public String[] getTaskListsNames() {
		String[] taskListNames = new String[taskLists.size() + 1];
		
		taskListNames[0] = "Active Tasks";
		
		if(taskLists.size() > 0) {
			for(int i = 0; i < taskLists.size(); i++) {
				taskListNames[i + 1] = taskLists.get(i).getTaskListName();
			}
		}
		
		return taskListNames;
	}
	
	/**
	 * Gets Active Tasks within current Notebook
	 */
	private void getActiveTaskList() {
		activeTaskList.clearTasks();
		TaskList tasklist = null;
		for(int i = 0; i < taskLists.size(); i++) {
			tasklist = taskLists.get(i);
			for(int j = 0; j < tasklist.getTasks().size(); j++) {
				if(tasklist.getTask(j).isActive()) {
					activeTaskList.addTask(tasklist.getTask(j));
				}
			}
		}
	}
	
	/**
	 * Sets current TaskList with a passed TaskList name as a String
	 * @param taskListName a TaskList's name
	 */
	public void setCurrentTaskList(String taskListName) {
		boolean found = false;
		for(int i = 0; i < taskLists.size(); i++) {
			if(taskLists.get(i).getTaskListName().equals(taskListName)) {
				currentTaskList = taskLists.get(i);
				found = true;
				break;
			}
		}
		
		if(!found) {
			currentTaskList = activeTaskList;
		}
	}
	
	/**
	 * Returns the current TaskList
	 * @return currentTaskList the current TaskList
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}
	
	/**
	 * Edits a TaskList by passing a String with the name of a TaskList within current Notebook
	 * @param taskListName a TaskList's name
	 * @throws IllegalArgumentException if the task trying to be edited is of the type ActiveTaskList of the passed name is the same
	 * as the ActiveTaskList
	 */
	public void editTaskList(String taskListName) {
		if(currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		
		if(ActiveTaskList.ACTIVE_TASKS_NAME.equals(taskListName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		// Check for duplicate tasklist name
		boolean duplicate = false;
		
		for(int i = 0; i < taskLists.size(); i++) {
			if(taskLists.get(i).getTaskListName().equals(taskListName)) {
				duplicate = true;
				break;
			}
		}
		
		if(duplicate) { // Duplicate tasklist name found
			throw new IllegalArgumentException("Invalid name.");
		}
		
		else { // Edit current task
			TaskList edit = null;
			for(int i = 0; i < taskLists.size(); i++) {
				if(taskLists.get(i) == currentTaskList) {
					edit = taskLists.remove(i);
					edit.setTaskListName(taskListName);
					taskLists.add(edit);
					isChanged = true;
					break;
				}
			}
		}
	}
	
	/**
	 * Removes a TaskList
	 * @throws IllegalArgumentException if the current task list is an instance of the ActiveTaskList
	 */
	public void removeTaskList() {
		if(currentTaskList instanceof ActiveTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}
		
		else {
			for(int i = 0; i < taskLists.size(); i++) {
				if(currentTaskList == taskLists.get(i)) {
					taskLists.remove(i);
					currentTaskList = activeTaskList;
					isChanged = true;
					break;
				}
			}
		}
	}
	
	/**
	 * Adds a Task to current TaskList by passing a Task object
	 * @param t a Task to be added to current TaskList (if valid)
	 */
	public void addTask(Task t) {
		if(!(currentTaskList instanceof TaskList)) {
			return;
		}
		
		else {
			currentTaskList.addTask(t);
			
			if(t.isActive()) {
				getActiveTaskList();
			}
			
			isChanged = true;
		}
	}
	
	/**
	 * Edits a Task in the current TaskList by passing given parameters
	 * @param idx a Task's index
	 * @param taskName a Task's name
	 * @param taskDescription a Task's description
	 * @param recurring a Task's recurring status as a boolean
	 * @param active a Task's active status as a boolean
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if(!(currentTaskList instanceof TaskList)) {
			return;
		}
		
		else {
			if(currentTaskList.getTask(idx) != null) {
				currentTaskList.getTask(idx).setTaskName(taskName);
				currentTaskList.getTask(idx).setTaskDescription(taskDescription);
				currentTaskList.getTask(idx).setRecurring(recurring);
				currentTaskList.getTask(idx).setActive(active);
				getActiveTaskList();
				isChanged = true;
			}
		}
	}
}
