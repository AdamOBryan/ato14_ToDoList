package controller;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton implements ActionListener {
	
	private Controller controller;
	
	public AddButton(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e){
		controller.getModel().addListItem(controller.getView().getUserInput()); // need to add get user input(String)
		//refresh view somehow
		
	}
	

	public Controller getController() {
		return controller;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}// end main AddButton class
