package edu.pitt.todolist.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import java.sql.*;
import java.net.URL;

public class Model {
	private Vector<ListItem> todoList;
	private Vector<User> userList;
	private HashMap<User, Vector<ListItem>> userTodo;
	
	private Connection connection;
	private Statement statement = null;
	private ResultSet rs = null;
	

	public Model() {
		this.todoList = new Vector<ListItem>();
		this.userList = new Vector<User>();
		this.userTodo = new HashMap<User, Vector<ListItem>>();
		
		//Start sql connection
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			connection = DriverManager.getConnection("jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/ato14is1017","ato14is1017","ato14@pitt.edu");
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connect db error");
			e.printStackTrace();
		}

		/* choose table to use*/
		try {
			statement.executeUpdate("USE ato14is1017;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("table choose error");
			e.printStackTrace();
		}
		loadDbList();

	}//End Constructor

	
	
	
	public void loadDbList(){
		loadTodoList();
		loadUserList();
		loadJunctions();
	}
	
	
	
	/* This function loads the todoList table of items into a vector of ListItems*/
	public void loadTodoList(){
		
		this.todoList.clear();
		/*  String to get all list items from database*/
		 String getAll = "SELECT * FROM todoList;";
		 
		try {
			rs = statement.executeQuery(getAll);
			while(rs.next()){
				this.todoList.add(new ListItem(rs.getString("itemDescription"), rs.getTimestamp("time_stamp"), rs.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("load db error");
			e.printStackTrace();
		}
		
	}// end loadTodoList
	

	
	/* This function loads the user table of items into a vector of Users*/
	public void loadUserList(){
		
		this.userList.clear();
		
		/*  String to get all list items from database*/
		 String getAllUsers = "SELECT * FROM user;";
		 
		try {
			rs = statement.executeQuery(getAllUsers);
			while(rs.next()){
				this.userList.add(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("load user db error");
			e.printStackTrace();
		}	
	}// end loadUserList
	
	
	
	
	/* This function loads the user_todo table of items into a HashMap <userID, todoID>  */
	public void loadJunctions(){
		
		this.userTodo.clear();
		 /*Get all list items owner is tied to and recreate junction table as a hash map*/
		for (User user : this.userList){
			/*  String to get all list items from database for user*/
			 String getUserItems = "SELECT t.* FROM user u " 
								+"JOIN user_todo ut ON u.id = ut.userId "
								+"JOIN todoList t ON ut.todoId = t.id "
								+"WHERE u.id = "+ user.getID() + ";";
		
			 try {
				rs = statement.executeQuery(getUserItems);
				Vector<ListItem> userItems = new Vector<ListItem>();
				while(rs.next()){
					//add list item to user's list vector
					userItems.add(new ListItem(rs.getString("itemDescription"), rs.getTimestamp("time_stamp"), rs.getInt("id")));
				}
				this.userTodo.put(user, userItems);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("load user_Todo db error");
					e.printStackTrace();
				}

		}//end for loop
		
	}// end loadJunctions
	
	
	
	
	
	public void addListItem(String description) {
		
		/*  String to insert item into database  - works*/
		 String insertListItem = "INSERT INTO todoList (itemDescription)"+ 
				 "VALUES ('"+ description + "');";
			try {
				statement.executeUpdate(insertListItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("add list item error");
				e.printStackTrace();
			}
	}// end addListItem
	
	
	
	
	/*This method deletes the selected list items to be deleted from the database and then reloads the current todo list*/
	public void deleteListItem(String description) {
		Vector<ListItem> itemsToDelete = new Vector<ListItem>();
		
		for (ListItem item : todoList) {
			if (item.getDescription().equals(description)) {
				itemsToDelete.add(item);
			}
		}
		
		for (ListItem item : itemsToDelete) {
			//todoList.remove(item);
			/*  String to delete item from database*/
			String deleteListItem = "DELETE FROM todoList WHERE itemDescription = '"+ item.getDescription()+ "';";
			try {
				int backInt = statement.executeUpdate(deleteListItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("delete list itemerror");
				e.printStackTrace();
			}
		}
	}// end deleteListItem

	
	
	/* this function returns the to do list in a vector*/
	public Vector<ListItem> getList() {
		return todoList;
	}


}// End Model Class
