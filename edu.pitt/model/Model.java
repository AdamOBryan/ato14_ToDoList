package model;

import java.util.Vector;

public class Model {
	
	private Vector<ListItem> todoList; 
	
	
	
	public Model(){
		todoList = new Vector<ListItem>();
	}// end Model constructor
	
	
	
	public void addListItem(String newItem){
		todoList.add(new ListItem(newItem));
	}// end addListItem
	
	
	
	public void deleteListItem(String itemToDelete){
		for(ListItem l : todoList){
			if (l.getDescription() == itemToDelete)
				todoList.remove(l);//returns true if removed
		}
		
	}// end deleteListItem
	
	
	
	public Vector<ListItem> getList(){
		Vector<ListItem> listToSend = new Vector<ListItem>();
		
		for(ListItem l : todoList)
			listToSend.add(l);
		
		return listToSend;
	
	}// end getList

	
	
}//end main model class
