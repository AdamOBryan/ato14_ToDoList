package controller;
import view.*;
import model.*;
import java.awt.event.ActionListener;

public class Controller {
	
	private View view;
	private Model model;
	private AddButton addButton;
	private DeleteButton deleteButton;

	
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;
		addButton = new AddButton(this);
		deleteButton = new DeleteButton(this);
		view.getAddButton().addActionListener(addButton);
		view.getDeleteButton().addActionListener(deleteButton);
		//this.view.getClass().addActionListener(deleteButton);
	}
	
	public View getView(){
		return view;
	}
	
	public Model getModel(){
		return model;
	}
	
	public AddButton getAddButton(){
		return addButton;
	}
	
	public DeleteButton getDeleteButton(){
		return deleteButton;
	}
	
	
	
	
	
	
	
	
}// end main Controller class
