package controlsystem.main;
import controlsystem.navigation.*;
public class Main {

	public static void main(String[] args) {
		MovementController movementController = new MovementController();
		while (movementController.MoveToNextPosition())
		{
			System.out.println("Moved");
		}
	}

}
