package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeleteItemButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteItemButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e){
		//Returns the last path element of the selection.
	    DefaultMutableTreeNode selectedItem = (DefaultMutableTreeNode) controller.getView().getTodoTree().getLastSelectedPathComponent();
	    DefaultMutableTreeNode selectedItemParent = (DefaultMutableTreeNode) selectedItem.getParent();

	    selectedItemParent.remove(selectedItem);
	    
	    
		controller.getModel().deleteListItem(selectedItem);
		controller.getView().getTodoTree().updateUI();
    }
}
