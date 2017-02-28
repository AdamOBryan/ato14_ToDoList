package edu.pitt.todolist.model;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import java.sql.*;


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
		System.out.println(userTodo.toString());
	}// end loadJunctions
	
	
	
	
	
	public void addListItem(String description, DefaultMutableTreeNode parent) {
		int parentId = 0;
		int newItemId = 0;
		
		String parentDescription = (String) parent.getUserObject();
		for(User currentUsers : userList){
			if (currentUsers.getName() == parentDescription){
				System.out.println(currentUsers.getName());
				parentId = currentUsers.getId();
				System.out.println(currentUsers.getId());
			}
		}
		if(parentId == 0){
			for(ListItem currentItem : todoList){
				if (currentItem.getDescription() == parentDescription){
					parentId = currentItem.getId();
				}
			}
		}
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
			
			/*  get new user id*/
			 String getId = "SELECT id FROM todoList WHERE itemDescription = '"+description+"';";
				try {
					ResultSet rs2;
					rs2 = statement.executeQuery(getId);
					if(rs2.next()){
					newItemId = rs2.getInt("id");
					System.out.println(newItemId);

					System.out.println(parentId);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("add list item error");
					e.printStackTrace();
				}
			
			/*  String to insert connection to junction table  - works*/
			 String insertJunction = "INSERT INTO user_todo (userId, todoId)"+ 
					 "VALUES ('"+ parentId +", "+ newItemId + "');";
				try {
					statement.executeUpdate(insertListItem);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("add list item error");
					e.printStackTrace();
				}
	}// end addListItem
	
	public void addListItem(String description, int userId) {
		
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
			
			try {
				String itemId = "SELECT id FROM todoList WHERE itemDescription="+ description+ "');";
				rs = statement.executeQuery(itemId);	
				int iId = rs.getInt(itemId);
				
				String insertJunction = "INSERT INTO user_todo (userId, itemId)"+
						 "VALUES ('"+userId+","+iId+ "');";
				statement.executeUpdate(insertJunction);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("parent id error item error");
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
				System.out.println("delete list item error");
				e.printStackTrace();
			}
		}
	}// end deleteListItem

	public void addUser(String fName, String lName) {
		
		/*  String to insert item into database  - works*/
		 String insertListItem = "INSERT INTO user (firstName, lastName)"+ 
				 "VALUES ('"+ fName +", " +  lName + "');";
			try {
				statement.executeUpdate(insertListItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("add list item error");
				e.printStackTrace();
			}
	}// end addListItem
	
	/* this function returns the to do list in a vector*/
	public Vector<ListItem> getList() {
		return todoList;
	}
	
	/* this function returns the user list in a vector*/
	public Vector<User> getUserList() {
		return userList;
	}
	
	
	public HashMap<User, Vector<ListItem>> getUserTodoMap(){
		return userTodo;
	}

}// End Model Class
