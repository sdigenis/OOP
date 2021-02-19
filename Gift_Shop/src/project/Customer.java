package project;
//import java.util.*;

public class Customer {

	private String username;
	private String fullName;
	private String email;
	
	public Customer(String username, String fullName, String email) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
