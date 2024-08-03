package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * Retrieves all created TaskLists and added Task created within GUI and writes to a file
 * @author Jake Donovan
 *
 */
public class NotebookWriter {
	/**
	 * Constructs a Notebook Writer Object
	 */
	public NotebookWriter() {
		//
	}
	
	/**
	 * Writes (or saves) to a File by passing a file name, notebook name, and all task lists
	 * @param fileName a File's name
	 * @param notebookName A Notebook's name
	 * @param taskLists All TaskLists
	 * @throws IllegalArgumentException if the file is unable to be saved
	 */
	public static void writeNotebookFile(File fileName, String notebookName, ISortedList<TaskList> taskLists) {
		PrintStream fileWriter = null;
		
		try {
			fileWriter = new PrintStream(fileName);
			
			if(fileWriter != null) {
				fileWriter.println("! " + notebookName);
				
				for(int i = 0; i < taskLists.size(); i++) {
					fileWriter.println("# " + taskLists.get(i).getTaskListName() + "," + taskLists.get(i).getCompletedCount());
					for(int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
						fileWriter.println(taskLists.get(i).getTask(j).toString());
					}
				}
				
				fileWriter.close();
			}
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
