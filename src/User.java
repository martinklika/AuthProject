// Program to define the User class

public class User {

	// Instance variables
	private String username;
	private String password;
	private String name;
	
	// Default constructor
	public User() {
	}
	
	// Constructor (all attributes)
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	// Get and Set methods
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return ("username: " + username + ", password: ***, name: " + name);
	}//toString()	
}
