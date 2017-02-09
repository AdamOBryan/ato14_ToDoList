package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		String desc = controller.getView().getInput().getText();
		int selectedItem =  controller.getView().getTodoList().getSelectedIndex();
		
		controller.getView().getListModel().add(desc);
		//controller.getModel().addListItem(desc, selectedItem);
		//reset view
		/**/
	    controller.getModel().loadDbList();
		controller.getView().resetListPanel();
    }
}
