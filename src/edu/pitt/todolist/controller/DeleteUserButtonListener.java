package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeleteUserButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteUserButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Returns the last path element of the selection.
	    DefaultMutableTreeNode selectedItem = (DefaultMutableTreeNode) controller.getView().getTodoTree().getLastSelectedPathComponent();
	    String userName = (String)selectedItem.getUserObject();
	    DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) controller.getView().getTodoTree().getModel().getRoot();
		rootNode.remove(selectedItem);
		
		controller.getModel().deleteUser(userName);
		controller.getView().getTodoTree().updateUI();
    }
}
