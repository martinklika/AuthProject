import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Program to define the UserAccess class

public class UserAccess {

	// Instance variables
	private Connection connection;

	// Default constructor
	public UserAccess() {
		try {
			this.connection = connectToDB();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//Constructor

	// Get and Set methods
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// Utility methods
	// Method to create a connection to the database
	private Connection connectToDB() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost/AuthSystemProject";

		// Database credentials
		final String USER = "root";
		final String PASS = "ch@ngeme1";

		// Declare connection
		Connection conn = null;

		//STEP 2: Register JDBC driver
		Class.forName(JDBC_DRIVER).newInstance();

		//STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);

		System.out.println("Connected to database");

		return conn;
	}//connectToDB()

	// Method to save a user into a database
	public void saveUser(User user) throws SQLException {

		// Retrieve attributes
		String username = user.getUsername();
		String password = user.getPassword();
		String name = user.getName();
		
		// Execute a query
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "INSERT INTO User (username, name, password) VALUES ("
					+ "\"" + username + "\", "
					+ "\"" + name + "\", "
					+ "\"" + password + "\");";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}//try-catch

	}//saveUser()

	// Method to return User or null if username is wrong or passwords do not match
	public User getUser(String inputUsername, String inputPassword) throws SQLException {

		// Declare user and its attributes
		User user = null;
		String name = null;
		String password = null;
		
		// Execute a query
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "SELECT name, password FROM User WHERE username = \"" 
					+ inputUsername + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			// If username does not exist rs is empty, keep user null
			if (!rs.next() ) {
			    System.out.println("debuggin - no result returned, creating null User");
			    System.out.println("Your username or password is incorrect!!!");
			}//if
			// Else check password, if correct create new User and return
			else {
				// Extract data from result set
				rs.first();
				
				//Retrieve by column name
				name = rs.getString("name");
				password = rs.getString("password");
				
				if (password.equals(inputPassword)) {
					user = new User(inputUsername, password, name);
				}//if
				else {
					System.out.println("Your username or password is incorrect!!!");
				}//else
			}//else
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}//try-catch

		System.out.println(user);
		// Return user
		return user;
	}//getUser()

	// Method to return User or null if username is wrong or passwords do not match
	public ArrayList<User> getAllUsers() throws SQLException {

		// Declare user and its attributes
		ArrayList<User> userList = new ArrayList<User>();
		String username = null;
		String password = null;
		String name = null;
		
		// Execute a query
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "SELECT username, name, password FROM User;";
			ResultSet rs = stmt.executeQuery(sql);

			// If username does not exist rs is empty, keep user null
			while (rs.next()) {
				username = rs.getString("username");
				password = rs.getString("password");
				name = rs.getString("name");
				userList.add(new User(username, password, name));
			}//while
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}//try-catch

		// Debugging printing
		for (User user : userList) {
			System.out.println(user);
		}
		
		// Return list of users
		return userList;
	}//getUser()
	
}//class
