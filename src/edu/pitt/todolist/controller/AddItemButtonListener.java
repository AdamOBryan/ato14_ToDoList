package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class AddItemButtonListener implements ActionListener {
	Controller controller;
	
	public AddItemButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Returns the last path element of the selection.
	    DefaultMutableTreeNode selectedItem = (DefaultMutableTreeNode) controller.getView().getTodoTree().getLastSelectedPathComponent();
	    System.out.println((String) selectedItem.getUserObject());
	    
		String newItemText = controller.getView().getNewItemInput();
		controller.getModel().addListItem(newItemText, selectedItem);
		selectedItem.add(new DefaultMutableTreeNode(newItemText));
		  controller.getView().getTodoTree().updateUI();

    }
}
