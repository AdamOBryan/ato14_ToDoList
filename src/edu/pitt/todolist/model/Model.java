package edu.pitt.todolist.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import java.sql.*;
import java.net.URL;

public class Model {
	private Vector<ListItem> todoList;
	private Vector<User> userList;
	private HashMap<User, ListItem> userTodo;
	
	private Connection connection;
	private Statement statement = null;
	private ResultSet rs = null;
	
	
	/* used to add table
	 String buildTable = "CREATE TABLE todoList("
	 		+ "itemId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
	 		+ "itemDescription VARCHAR(100) NOT NULL);";
	 */
	
	
	public Model() {
		this.todoList = new Vector<ListItem>();
		this.userList = new Vector<User>();
		this.userTodo = new HashMap<User, ListItem>();
		
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
			System.out.println("executing");
			while(rs.next()){
				System.out.println(todoList.toString());
				this.todoList.add(new ListItem(rs.getString("itemDescription"), rs.getTimestamp("time_stamp"), rs.getInt("itemId")));
				for( ListItem l : todoList){
					System.out.println(l.getDescription());
					
				}
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
		 String getAll = "SELECT * FROM user;";
		 
		try {
			rs = statement.executeQuery(getAll);
			System.out.println("executing user");
			while(rs.next()){
				System.out.println(userList.toString());
				this.userList.add(new User(rs.getString("firstName"), rs.getString("lastName")));
				for( User l : userList){
					System.out.println(l.getDescription());
					
				}
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
		
		/*  String to get all list items from database*/
		 String getAll = "SELECT * FROM todoList;";
		 
		try {
			rs = statement.executeQuery(getAll);
			System.out.println("executing");
			while(rs.next()){
				System.out.println(userTodo.toString());
				
				int userID = rs.getInt("userId");
				int todoID = rs.getInt("todoId");
				
				this.userTodo.put(rs.getInt("userId"), rs.getInt("todoId"));
				for( ListItem l : userTodo){
					System.out.println(l.getDescription());
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("load user_Todo db error");
			e.printStackTrace();
		}
		
	}// end loadTodoList
	
	
	
	
	
	public void addListItem(String description) {
		
		/*  String to insert item into database  - works*/
		 String insertListItem = "INSERT INTO todoList (itemDescription)"+ 
				 "VALUES ('"+ description + "');";
		 System.out.println(insertListItem);
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
