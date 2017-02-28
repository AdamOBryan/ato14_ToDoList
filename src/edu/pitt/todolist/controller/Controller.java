package edu.pitt.todolist.controller;

import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Controller {
	private AddItemButtonListener addItemButtonListener;
	private DeleteItemButtonListener deleteItemButtonListener;
	private AddUserButtonListener addUserButtonListener;
	private DeleteUserButtonListener deleteUserButtonListener;
	private TodoTreeListener todoTreeListener;
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		view.getAddItemButton().addActionListener(new AddItemButtonListener(this));
		view.getDeleteItemButton().addActionListener(new DeleteItemButtonListener(this));
		view.getAddUserButton().addActionListener(new AddUserButtonListener(this));
		view.getDeleteUserButton().addActionListener(new DeleteUserButtonListener(this));
		view.getTodoTree().addTreeSelectionListener(new TodoTreeListener(this));
	}
	
	public AddItemButtonListener getAddItemButtonListener() {
		return addItemButtonListener;
	}

	public DeleteItemButtonListener getDeleteItemButtonListener() {
		return deleteItemButtonListener;
	}
	
	public AddUserButtonListener getAddUserButtonListener() {
		return addUserButtonListener;
	}

	public DeleteUserButtonListener getDeleteUserButtonListener() {
		return deleteUserButtonListener;
	}
	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
}
