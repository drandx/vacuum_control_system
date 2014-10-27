package controlsystem.power;

import java.util.LinkedList;

import controlsystem.model.Cell;

public class PowerController {
	private static float CurrentCharge = 50;
	
	public static void ReduceCharge(Cell initPosition, Cell finalPosition)
	{
		CurrentCharge = CurrentCharge - ((initPosition.getSurfaceType().getCode() + finalPosition.getSurfaceType().getCode())/2);
		System.out.println("Current Carhge: "+CurrentCharge);
	}
	
	
	/**
	 * Validates if there is enough power to move to a next position counting the 
	 * power needed to go back to the station.
	 * @param path
	 * @param finalPoint
	 * @return
	 */
	public static boolean ValidateChargeToMove(LinkedList<Cell>pathToStation, Cell finalPoint)
	{		
		float pathAverage = 0;
		int i = 1;
		for (i = 0; i < pathToStation.size(); i++) 
		{
			pathAverage = pathAverage + pathToStation.get(i).getSurfaceType().getCode();
		}
		pathAverage = (pathAverage + finalPoint.getSurfaceType().getCode()) / (pathToStation.size() + 1);
		
		if(pathAverage <= CurrentCharge)
			return true;
		else
			return false;
	}
	

}
