package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * This class is responsible for reading files and being able to retrieve all Tasks, TaskLists, and any other linked information
 * from Notebooks from a file
 * @author Jake Donovan
 *
 */
public class NotebookReader {
	/**
	 * Constructs a Notebook Reader object
	 */
	public NotebookReader() {
		//
	}
	
	/**
	 * Reads from a file and returns a Notebook using passed file name
	 * @param fileName a passed file that will be read to determine if there is valid Notebook or Notebooks within File
	 * @throws IllegalArgumentException if the file is unable to be read
	 * @return Notebook a Notebook object containing Tasks, TaskLists, and any other related information
	 */
	public static Notebook readNotebookFile(File fileName) {
		Scanner scanFileContents = null;
		String fileContents = new String();
		Notebook notebook = null;
		
		try {
			scanFileContents = new Scanner(new FileInputStream(fileName));
			
			while(scanFileContents.hasNextLine()) {
				String fileInfo = scanFileContents.nextLine();
				fileInfo = fileInfo.concat("\n");
				fileContents += fileInfo;
			}
		} catch(FileNotFoundException e) {
			//
		}
		
		Scanner scanFile = null;
		
		try {
			scanFile = new Scanner(fileContents);
			String notebookName = scanFile.nextLine();
			
			if(notebookName.contains("!")) {
				String name = notebookName.replace("!", "");
				name = name.trim();
				notebook = new Notebook(name);
			}
			
			else {
				scanFile.close();
				throw new IllegalArgumentException(); // Invalid notebook
			}
			
			scanFile.useDelimiter("\\r?\\n?[#]");
			
			while(scanFile.hasNext()) {
				TaskList taskList = processTaskList(scanFile.next());
				
				if(taskList != null) {
					notebook.addTaskList(taskList);
				}
			}
			
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		scanFile.close();
		return notebook;
	}
	
	/**
	 * Helper method that helps retrieve TaskLists from a Notebook within a file
	 * @param taskList a passed TasskList name from a file
	 * @return TaskList a constructed TaskList object from a file (read from file)
	 */
	private static TaskList processTaskList(String taskList) {
		Scanner scan = new Scanner(taskList);
		String taskListName = "";
		String completedCountString = "";
		int completedCount = 0;
		TaskList tList = null;
		String test = scan.nextLine().trim();
		
		if(test.contains(",")) {
			Scanner scanTest = new Scanner(test);
			scanTest.useDelimiter(",");
			taskListName = scanTest.next().trim();
			
			boolean isInt = false;
			
			try { // Check if first value is an int to make sure there isn't an invalid TaskList with only completed count
				Integer.valueOf(taskListName);
				isInt = true;
			} catch(Exception e) {
				//
			}
			
			if(!isInt) {
				completedCountString = scanTest.next().trim();
				completedCount = Integer.valueOf(completedCountString);
				scanTest.close();
			}
			
			else {
				scan.close();
				scanTest.close();
				return null;
			}
		}
		
		else {
			taskListName = test;
		}
		
		if(!"".equals(taskListName) && taskListName != null && completedCount >= 0) {
			tList = new TaskList(taskListName, completedCount);
		}
		
		else {
			scan.close();
			return null;
		}
		
		scan.useDelimiter("\\r?\\n?[*]");
		
		Task task = null;
		
		while(scan.hasNext()) {
			task = processTask(tList, scan.next());
			
			if(task != null) {
				tList.addTask(task);
			}
		}
		
		scan.close();
		
		return tList;
	}
	
	/**
	 * Helper method which helps process each individual Task and returns and adds to TaskList then TaskList to Notebook
	 * @param taskList a passed TaskList that a Task is contained within
	 * @param taskInfo a Task object that we will extract (its info as Strings) and return a constructed Task object
	 * @return Task a constructed Task object read from file
	 */
	private static Task processTask(AbstractTaskList taskList, String taskInfo) {
		Scanner scanTask = new Scanner(taskInfo);
		Task task = null;
		String taskName = "";
		boolean recurring = false;
		boolean active = false;
		String test = scanTask.nextLine().trim();
		
		if(!test.contains(",")) {
			taskName = test;
		}
		
		else {
		
		Scanner scanTaskName = new Scanner(test);
		scanTaskName.useDelimiter(",");
		int checkIdx = 0;
		
		while(scanTaskName.hasNext()) {
			String check = "";
			check = scanTaskName.next().trim();
			if("recurring".equals(check) && checkIdx != 0) {
				recurring = true;
			}
			
			else if("active".equals(check) && checkIdx != 0) {
				active = true;
			}
			
			else if(active && "recurring".equals(check)) {
				scanTask.close();
				scanTaskName.close();
				return null;
			}
			
			else if(checkIdx == 0 && ("active".equals(check) || "recurring".equals(check))) {
				scanTask.close();
				scanTaskName.close();
				return null;
			}
			
			else {
				taskName = check;
			}
			
			checkIdx++;
		}
		
		scanTaskName.close();
		}
		
		
		scanTask.useDelimiter("\n");
		String description = "";
		
		while(scanTask.hasNext()) {
			description += scanTask.next().trim().concat("\n");
		}
		
		scanTask.close();
		
		if(!"".equals(taskName)) {
			task = new Task(taskName, description, recurring, active);
	    }
		
		return task;
	}
}