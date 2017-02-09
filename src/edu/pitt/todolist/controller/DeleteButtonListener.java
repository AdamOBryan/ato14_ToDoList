package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;

import edu.pitt.todolist.model.ListItem;

public class DeleteButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		int[] selectedItems =  controller.getView().getTodoList().getSelectedIndices();
		//System.out.println(selectedItems.toString());
		//ListModel model = list.getModel();

		// Get all the selected items using the indices
	    // for (int i = 0; i < selectedItems.length; i++) {
		for (int i = selectedItems.length-1; i >=0; i--) {
	    	if(selectedItems[i]>=0)
	    		controller.getModel().deleteListItem((String) controller.getView().getListModel().remove(selectedItems[i]));
	    }


		//reset view
	    /**/
	    controller.getModel().loadDbList();
		controller.getView().resetListPanel();
    }
}
