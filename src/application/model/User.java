package application.model;

public class User {
	private String name;
	private String eMail;
	private String password;
	
	public User(String n, String e, String p) {
		name = n;
		eMail = e;
		password = p;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEMail() {
		return eMail;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User by the name of: " + name + " and using the email address: " + eMail
				+ " hashed pwd: " + password;
	}
}
