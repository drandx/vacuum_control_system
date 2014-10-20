package simulator.main;
import simulator.model.Cell;
import simulator.model.HomeModel;

import simulator.GUI.GUIWindow;

public class Main {

	public static void main(String[] args) 
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
		
		newWindow.moveVacuum();
		
	}

}
