package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class AddUserButtonListener implements ActionListener {
	Controller controller;
	
	public AddUserButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {

	    
		String newUserFirst = controller.getView().getNewUserFirst();
		String newUserLast = controller.getView().getNewUserLast();
		controller.getModel().addUser(newUserFirst, newUserLast);
		
		
		DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(newUserFirst+" "+ newUserLast);
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) controller.getView().getTodoTree().getModel().getRoot();
		rootNode.add(newUserNode);
    }
}
