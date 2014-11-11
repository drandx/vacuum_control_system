package controlsystem.power;
import controlsystem.model.Cell;
import controlsystem.model.Constants;

public class PowerController {
	private static float CurrentCharge = Constants.BATTERY_CAPACITY;
	
	public static void ReduceCharge(Cell initPosition, Cell finalPosition)
	{
		CurrentCharge = CurrentCharge - ((initPosition.getSurfaceType().getCode() + finalPosition.getSurfaceType().getCode())/2);
		System.out.println("Current Charge: "+CurrentCharge);
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
		if(((distanceToNext + distanceToHome)/2) <= CurrentCharge )
			return true;
		else
		{
			return false;
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
