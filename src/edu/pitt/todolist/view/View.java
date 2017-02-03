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

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private JList<Vector<String>> todoList;
	private JTextField textField;
	private Model model;
	private Vector<String> listModel;
	private JPanel listPanel;
	private JLabel label;
	private JPanel panel;
	
	public View(Model model) {
		this.model = model;
		window = new JFrame("ToDoList");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

	    listPanel = new JPanel();
	    /*resetListPanel();
	}
	
	   public void resetListPanel(){
	   */
	    listModel = new Vector<String>();
	    todoList = new JList(listModel);
	    todoList.setPreferredSize(new Dimension(200, 200));
	    /*
	    todoList.setMaximumSize(new Dimension(480, 500));
	    todoList.setMinimumSize(new Dimension(480, 400));
	    */
	    for(ListItem l : model.getList()){
	    	listModel.add(l.getDescription());
	    }
	    
	    listPanel.add(todoList);
	    panel.add(listPanel);

		label = new JLabel("Please enter item here:");
		panel.add(label);
		textField = new JTextField("list item");
		panel.add(textField);

		addButton = new JButton();
		addButton.setText("Add");
		panel.add(addButton);
	    
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		panel.add(deleteButton);
	    
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
