package simulator.main;
import simulator.model.Cell;
import simulator.model.Home;
import simulator.model.HomeModel;
import simulator.GUI.GUIWindow;

public class Main {

	public static void main(String[] args) 
	{	
		// Local variable
		int swValue;

		while(true)
		{
			// Display menu graphics
			System.out.println("============================");
			System.out.println("| Vacuum System Simulator  |");
			System.out.println("============================");
			System.out.println("| Options:                 |");
			System.out.println("|        1. Init  GUI      |");
			System.out.println("|        2. Dump Floor Plan|");
			System.out.println("|        3. Exit           |");
			System.out.println("============================");
			swValue = Keyin.inInt(" Select option: ");

			// Switch construct
			switch (swValue) {
			case 1:
				System.out.println("Option 1 selected");
				initSimulatorGUI();
				break;
			case 2:
				System.out.println("Option 2 selected");
				HomeModel.PrintMap();
				break;
			case 3:
				System.out.println("Exit selected");
				break;
			default:
				System.out.println("Invalid selection");
				break; // This break is not really necessary
			}

		}

	}

	public static void initSimulatorGUI()
	{

		GUIWindow newWindow = new GUIWindow();		
		HomeModel.Load();

		System.out.println("Home Plan Loaded!");
		System.out.println("Floors Number: "+HomeModel.loadedHome.getFloors().size());
		for(int i=0; i < HomeModel.loadedHome.getFloors().size(); i++)
		{
			System.out.println("Floor "+HomeModel.loadedHome.getFloors().get(i).getFloor());
			for(int j = 0; j < HomeModel.loadedHome.getFloors().get(i).getCells().size(); j++)
			{
				Cell currentCell = HomeModel.loadedHome.getFloors().get(i).getCells().get(j);
				System.out.println("xs "+currentCell.getXs());
				System.out.println("ys "+currentCell.getYs());
				System.out.println("cs "+currentCell.getCs());
				System.out.println("ps "+currentCell.getPs());
				System.out.println("ds "+currentCell.getDs());
			}
		}

	}

	static class Keyin {

		//*******************************
		//   support methods
		//*******************************
		//Method to display the user's prompt string
		public static void printPrompt(String prompt) {
			System.out.print(prompt + " ");
			System.out.flush();
		}

		//Method to make sure no data is available in the
		//input stream
		public static void inputFlush() {
			int dummy;
			int bAvail;

			try {
				while ((System.in.available()) != 0)
					dummy = System.in.read();
			} catch (java.io.IOException e) {
				System.out.println("Input error");
			}
		}

		//********************************
		//  data input methods for
		//string, int, char, and double
		//********************************
		public static String inString(String prompt) {
			inputFlush();
			printPrompt(prompt);
			return inString();
		}

		public static String inString() {
			int aChar;
			String s = "";
			boolean finished = false;

			while (!finished) {
				try {
					aChar = System.in.read();
					if (aChar < 0 || (char) aChar == '\n')
						finished = true;
					else if ((char) aChar != '\r')
						s = s + (char) aChar; // Enter into string
				}

				catch (java.io.IOException e) {
					System.out.println("Input error");
					finished = true;
				}
			}
			return s;
		}

		public static int inInt(String prompt) {
			while (true) {
				inputFlush();
				printPrompt(prompt);
				try {
					return Integer.valueOf(inString().trim()).intValue();
				}

				catch (NumberFormatException e) {
					System.out.println("Invalid input. Not an integer");
				}
			}
		}

		public static char inChar(String prompt) {
			int aChar = 0;

			inputFlush();
			printPrompt(prompt);

			try {
				aChar = System.in.read();
			}

			catch (java.io.IOException e) {
				System.out.println("Input error");
			}
			inputFlush();
			return (char) aChar;
		}

		public static double inDouble(String prompt) {
			while (true) {
				inputFlush();
				printPrompt(prompt);
				try {
					return Double.valueOf(inString().trim()).doubleValue();
				}

				catch (NumberFormatException e) {
					System.out
					.println("Invalid input. Not a floating point number");
				}
			}
		}
	}
}


