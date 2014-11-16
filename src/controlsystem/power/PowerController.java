package controlsystem.power;
import simulator.util.Util;
import controlsystem.model.Cell;
import controlsystem.model.Constants;

public class PowerController {
	private static float CurrentCharge = Constants.BATTERY_CAPACITY;
	
	public static void ReduceCharge(Cell initPosition, Cell finalPosition)
	{
		CurrentCharge = CurrentCharge - ((initPosition.getSurfaceType().getCode() + finalPosition.getSurfaceType().getCode())/2);
	}
	
	/**
	 * Validates if there is enough power to move to a next position counting the 
	 * power needed to go back to the station.
	 * @param path
	 * @param finalPoint
	 * @return
	 */
	public static boolean ValidateChargeToMove(float distanceToHome,Cell currentCell, Cell nextCell)
	{		
		float distanceToNext = (currentCell.getSurfaceType().getCode() + nextCell.getSurfaceType().getCode())/2;
		Util.botLog("Distance to the next cell before movement: "+distanceToNext);
		if((distanceToNext + distanceToHome) >= CurrentCharge )
			return false;
		else
		{
			return true;
		}
	}
	
	public static float GetCurrentCharge()
	{
		return CurrentCharge;
	}
	
	public static void ChargeBattery()
	{
		CurrentCharge = Constants.BATTERY_CAPACITY;		
	}	

}
