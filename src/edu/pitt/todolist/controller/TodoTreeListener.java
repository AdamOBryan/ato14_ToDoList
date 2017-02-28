package edu.pitt.todolist.controller;


import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TodoTreeListener implements TreeSelectionListener {
	Controller controller;
	 String nodeInfo;
	
	public TodoTreeListener(Controller controller) {
		this.controller = controller;
	}
	

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//Returns the last path element of the selection.
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                      controller.getView().getTodoTree().getLastSelectedPathComponent();

		    if (node == null)
		    //Nothing is selected.     
		    return;

		    
		    nodeInfo = (String) node.getUserObject();
		    /*
		    if (node.isLeaf()) {
		        BookInfo book = (BookInfo)nodeInfo;
		        displayURL(book.bookURL);
		    } else {
		        displayURL(helpURL); 
		    }
		*/
	}
}