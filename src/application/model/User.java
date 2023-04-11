package application.model;
import java.util.ArrayList;

public class User {
	private int id;
	private String name;
	private String eMail;
	private String password;
	private ArrayList<Project> projectList = new ArrayList<>();
	
	public User(int id, String n, String e, String p) {
		this.id = id;
		name = n;
		eMail = e;
		password = p;
	}
	
	// Add project to user
	public void addProject(Project project) {
		if (!projectList.contains(project)) {
			projectList.add(project);
		}
	}
	
	// Remove project from user
	public void removeProject(Project project) {
		if (projectList.contains(project)) {
			projectList.add(project);
		}
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getEMail() {
		return eMail;
	}
	
	public String getPassword() {
		return password;
	}

	public ArrayList<Project> getProjectList() {
		return projectList;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setProjectList(ArrayList<Project> projectList) {
		this.projectList = projectList;
	}
	
	@Override
	public String toString() {
		return "User by the name of: " + name + " and using the email address: " + eMail
				+ " hashed pwd: " + password;
	}
}
