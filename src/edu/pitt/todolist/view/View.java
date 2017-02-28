package edu.pitt.todolist.view;

import edu.pitt.todolist.controller.AddItemButtonListener;
import edu.pitt.todolist.controller.AddUserButtonListener;
import edu.pitt.todolist.controller.DeleteItemButtonListener;
import edu.pitt.todolist.controller.DeleteUserButtonListener;
import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.User;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;



public class View {
	private Model model;
	private HashMap<User, Vector<ListItem>> userTodo;
	
	// container variables
	private JFrame window;
	private JPanel mainContainer;
	private JPanel treePanel;
	private JPanel actionPanel;	
	
	// tree variables
	private JTree todoTree;
	
	// add/delete item variables
	private JLabel addItemlabel;
	private JTextField newItemTextField;
	private JButton addItemButton;
	private JLabel deleteItemlabel;
	private JButton deleteItemButton;
	
	// add/delete user variables
	private JLabel addUserlabel;
	private JTextField newUserFirstName;
	private JTextField newUserLastName;
	private JButton addUserButton;
	private JLabel deleteUserlabel;
	private JButton deleteUserButton;
	

	/*
	 * View public constructor: takes the parent model in as parameter
	 */
	public View(Model model) {
		this.model = model;
		this.userTodo = new HashMap<User, Vector<ListItem>>(this.model.getUserTodoMap());
		
		// get user and list item data from model
		
		// initialize  container components
		window = new JFrame("ToDoList");
		mainContainer = new JPanel();
	

		


/*
 * * START TREE PANNEL
 */
		// initialize tree panel
		treePanel = new JPanel();
		
		// initialize tree
		todoTree = new JTree();
		todoTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// create todoList root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Todo List Root");
        
    	// create a set of the contents in hash map to iterate through
    	Set <HashMap.Entry<User, Vector<ListItem>>> entrySet = userTodo.entrySet(); 
     
    	
    	
    	// iterate through hash map of users and their to do items 
    	// create as node and add to it parent for each of these objects
    	
    	for (HashMap.Entry<User, Vector<ListItem>> entry : entrySet) {
    		// create user node
    		String userName = entry.getKey().getName();
    		DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userName);
    		
    		// get users attached to do list items
    		Vector<ListItem> userItems = entry.getValue();
    		
    		//create the user's list item nodes
    		for(ListItem item : userItems){
    			String description = item.getDescription();
                DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(description);
                // add to do item to user node
                userNode.add(itemNode);
    		}

	        //add the user node to the root node
	        root.add(userNode);
	        
    	}// end iterating through hash map and populating the tree
    	
    	
    	
        //create the tree by passing in the root node
        todoTree = new JTree(root);
        
        //hide root node
        todoTree.setRootVisible(false);
        
        // add JTree to tree panel
        treePanel.add(todoTree);
        
		
/*
 * * END TREE PANNEL
 */

		
		
		

/*
 * * START ACTIONS PANNEL
 */		
		
		// Initialize Panel
		actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(11, 1));
		
		
		/* 
		 * Add NEW ITEM Area 
		 */
		
		// Add Item Label
		addItemlabel = new JLabel("Please enter item here:");
		actionPanel.add(addItemlabel);
		
		// Add Item Text Field
		newItemTextField = new JTextField("list item to add");
		actionPanel.add(newItemTextField);

		// Add Item Button
		addItemButton = new JButton();
		addItemButton.setText("Add Item");
		actionPanel.add(addItemButton);
	    
		
		
	    /* 
	     * DELETE ITEM Area 
	     */
		
		// Delete Item Label
		deleteItemlabel = new JLabel("Please select which item you want to delete from the Todo Item Tree to the left.");
		actionPanel.add(deleteItemlabel);
		
		// Delete Item Button
		deleteItemButton = new JButton();
		deleteItemButton.setText("Delete Item");
		actionPanel.add(deleteItemButton);
		
		
		
		/*  NEW User Area    */
		
		// Add User Label
		addUserlabel = new JLabel("Please Enter New User's First and Last Name: ");
		actionPanel.add(addUserlabel);
		
		// Add User First Name Text Field
		newUserFirstName = new JTextField("first name");
		actionPanel.add(newUserFirstName);
		
		// Add User Last Name Text Field
		newUserLastName = new JTextField("last name");
		actionPanel.add(newUserLastName);
		
		// Add User Button
		addUserButton = new JButton();
		addUserButton.setText("Add User");
		actionPanel.add(addUserButton);
		    
			
			
	    /* 
	     * DELETE User Area 
	     */
		
		// Delete User Warning Label
		deleteUserlabel = new JLabel("Please select which USER you want to delete from the Todo Item Tree to the left.");
		actionPanel.add(deleteUserlabel);
		
		// Delete User Button
		deleteUserButton = new JButton();
		deleteUserButton.setText("Delete User");
		actionPanel.add(deleteUserButton);
		
	/*
	 * * END ACTIONS PANNEL
	 */		
		
		
	   
		// set layout
		mainContainer.setLayout(new GridLayout(1,2));
		// treePanel is on the right
		mainContainer.add(treePanel);
		// action panel is on the left
		mainContainer.add(actionPanel);
		
		
		window.add(mainContainer);
		window.setSize(800, 800);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.pack();
		window.setVisible(true);
	}


	
	public JButton getAddItemButton() {
		return addItemButton;
	}
	public JButton getDeleteItemButton() {
		return deleteItemButton;
	}
	public JButton getAddUserButton() {
		return addUserButton;
	}
	public JButton getDeleteUserButton() {
		return deleteUserButton;
	}

	public JTree getTodoTree(){
		return todoTree;
	}


	public String getNewItemInput(){
		
		return newItemTextField.getText();
	}

	public String getNewUserFirst(){
		return newUserFirstName.getText();
	}
	
	public String getNewUserLast(){
		return newUserLastName.getText();
	}	

}//endView Class
