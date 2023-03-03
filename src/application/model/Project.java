package application.model;
import java.util.ArrayList;

public class Project {
	private String name;
	private ArrayList<Task> taskList = new ArrayList<Task>();
	
	public Project() {}
	
	public Project(String name) {
		this.name = name;
	}
	
	// Add task
	public void addTask(Task task) {
		if (!taskList.contains(task)) {
			taskList.add(task);
		}
	}
	
	// Remove task
	public void removeTask(Task task) {
		if (taskList.contains(task)) {
			taskList.remove(task);
		}
	}

	// Getters
	public String getName() {
		return name;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
}
