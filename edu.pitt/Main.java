import view.*;
import model.*;
import controller.*;

public class Main {

	public static void main(String[] args) {
		View frame = new View();
		Model model = new Model();
		Controller controller = new Controller(frame, model);

	}

}
