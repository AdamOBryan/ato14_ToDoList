package controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton implements ActionListener{
	
	private Controller controller;
	
	public DeleteButton(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e){
		controller.getModel().deleteListItem(controller.getView().getDeleteButton()); 
		//refresh view somehow}
	

}// end main DeleteButton class