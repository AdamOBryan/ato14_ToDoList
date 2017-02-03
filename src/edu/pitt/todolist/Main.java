package edu.pitt.todolist;

import edu.pitt.todolist.controller.Controller;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		View frame = new View(model);
		Controller controller = new Controller(frame, model);
	}
}
