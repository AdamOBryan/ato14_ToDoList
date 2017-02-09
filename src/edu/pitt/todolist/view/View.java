package edu.pitt.todolist.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.TextField;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.model.User;

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private JList<Vector<String>> todoList;
	private JList<Vector<User>> userList;
	private JTextField textField;
	private Model model;
	private Model userModel;
	private Vector<String> listModel;
	private Vector<String> userListModel;
	private JPanel listPanel;
	private JPanel userListPanel;
	private JLabel label;
	private JPanel panel;
	
	public View(Model model) {
		this.model = model;
		window = new JFrame("ToDoList");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

	    /*
	    resetListPanel();
	}
	  public void resetListPanel(){
	  */
		
		listPanel = new JPanel();
		userListPanel = new JPanel();
	   
		//build todo list
	    listModel = new Vector<String>();
	    todoList = new JList(listModel);
	    todoList.setPreferredSize(new Dimension(200, 200));

	    //add items to todo list
	    for(ListItem l : model.getList()){
	    	listModel.add(l.getDescription());
	    }
	    
		//build user list
	    userListModel = new Vector<String>();
	    userList = new JList(userListModel);
	    todoList.setPreferredSize(new Dimension(200, 200));

	    //add items to todo list
	    for(User u : model.getUserList()){
	    	userListModel.add(u.getName());
	    }
	    
	    
	    //start build panel
	    
	    //add list items
	    listPanel.add(todoList);
	    panel.add(listPanel);

	    //add label
		label = new JLabel("Please enter item here:");
		panel.add(label);
		textField = new JTextField("list item");
		panel.add(textField);

		
		// add add button
		addButton = new JButton();
		addButton.setText("Add");
		panel.add(addButton);
	    
		//add delete button
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		panel.add(deleteButton);
		
		 userListPanel.add(userList);
		 panel.add(userListPanel);
		
		
	    // end build panel
		window.add(panel);
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JList getTodoList() {
		return todoList;
	}

	public JTextField getInput() {
		return textField;
	}

	public void addToList(String description) {
		model.addListItem(description);
	}
	
	public Vector<String> getListModel(){
		 return this.listModel;
	}

	public void removeFromList(Vector<String> selectedItems) {
		for(String description : selectedItems){
			model.deleteListItem(description);
		}
		
	}
	
	public void resetListPanel(){
		
		listPanel.repaint();
		listPanel.validate();
	}
}//endView Class
