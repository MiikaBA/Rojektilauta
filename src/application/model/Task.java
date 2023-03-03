package application.model;

public class Task {
	private String name;
	// 0 = not started, 1 = in progress, 2 = done
	private int progress;
	
	public Task() {
		this.progress = 0;
	}
	
	public Task(String name) {
		this.name = name;
		this.progress = 0;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getProgress() {
		return progress;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	
	
}
