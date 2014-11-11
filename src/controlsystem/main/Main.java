package controlsystem.main;
import controlsystem.navigation.*;
public class Main {

	public static void main(String[] args) {
		RobotController robotController = new RobotController();
		while (true)
		{
			robotController.Run();
		}
	}

}
