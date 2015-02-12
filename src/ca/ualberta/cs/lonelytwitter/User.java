package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

// public class User extends Object --> exactly the same as line below. The extends is implied.
// Adding abstract means I can only create instances of sub-classes (ie. author or reader)
public abstract class User implements UserLike {
	
	protected String username; // attribute

	
	public String getUsername() // method
	{
	
		return username;
	}

	// all objects that are users, will have this method. But I have no idea what it's going to do, you'll
	// have to ask the specific sub-class
	
	public abstract void setUsername(String x) throws IOException; // method


	public User(String username) // constructor
	{

		super();
		this.username = username;
	}
	
	public User() // constructor without argument
	{

		super();
		this.username = "anonymous";
	}
	
	
	

}
