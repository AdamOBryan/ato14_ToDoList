package view;
import java.awt.*;
import java.util.Vector;
import model.*;

import javax.swing.*;
import controller.*;

public class View {


	JFrame mainFrame;
	JList<ListItem>currentTodoList;
	JTextField userText;
	JButton jAddButton;
	JButton jDeleteButton;
 
	
	/* Constructor to prepare the UI components */
	 public View(){
		 setup();
	 }
	 
	 
	 
	 private void setup(){

		 // create main window
		mainFrame = new JFrame("Todo List");
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setBounds(200, 200, 500, 500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// create content area
		
		// create list
		currentTodoList = new JList<ListItem>(new Vector());
		mainFrame.getContentPane().add(currentTodoList);
		
		// create add new entry panel

		// user input text
		userText = new JTextField("");
		mainFrame.getContentPane().add(userText);
		
		// add button
		jAddButton = new JButton("Add this to the list");
		mainFrame.getContentPane().add(jAddButton);
	

		// delete button
		jDeleteButton = new JButton("Delete this from the list");
		mainFrame.getContentPane().add(jDeleteButton);


		//Display the window
		mainFrame.pack();
		mainFrame.setVisible(true);
 

   }// end constructor

	 public JTextField getUserInput(){
		 return userText;
	 }
	
	 public JButton getAddButton(){
		 return jAddButton;
	 }
	 public JButton getDeleteButton(){
		 return jDeleteButton;
	 }
	
	 public void refreshTheList(){}
	
}// END MAIN VIEW CLASS
