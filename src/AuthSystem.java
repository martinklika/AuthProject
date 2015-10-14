import com.mysql.jdbc.*;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		/*
		System.out.print("Please enter a text: ");
		String input = keyboard.nextLine();
		System.out.print("Your message is: " + input);
		*/
		
		UserAccess ua = new UserAccess();
		
		try {
			ua.getUser("beta", "b");
		} catch (SQLException e) {
			e.printStackTrace();
		}//try-catch
		
		try {
			ua.saveUser(new User("gamma", "g", "Gamma"));
		} catch (SQLException e) {
			// This whole try should probably be in a method and return true or false
			// if it throws an exception
			e.printStackTrace();
		}//try-catch
		
		try {
			ua.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	}

}
