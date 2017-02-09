package edu.pitt.todolist.model;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	
	public User(String firstName, String lastName, int id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		
	}
	
	public User(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		// need to add id here somewhere
		
	}
	
	public int getID(){
		return this.id; // make sure doesn't return null
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	
	
	
	
	
	
}// end user class
