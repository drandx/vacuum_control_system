package controlsystem.navigation;

import simulator.util.Util;
import controlsystem.model.Cell;
import controlsystem.model.CellState;
import controlsystem.power.PowerController;
import controlsystem.sensorsystem.SensorController;

public class RobotController {	
	 Cell currentLocation;
	 SensorController sensor;
	 MapHistory mapHistory;
	 
	 
	public void RobotToHome()
	{
		this.currentLocation = sensor.getCellDetail(new coordPair(0, 0));
		PowerController.ChargeBattery();
		//TODO: Reset Dirt Bag		
	}
	 
	public RobotController() {
		this.sensor = new SensorController();
		this.mapHistory = new MapHistory();
		this.RobotToHome();
		this.mapHistory.addHistory(this.currentLocation);
	}

	public Cell getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Cell currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	/**
	 * Returns false if there is not enough battery or there are no available cells to move to.
	 * @return
	 */
	public boolean Run()
	{
		boolean moved = false;
		for(Cell adjCell : currentLocation.getAdjacentCells())
		{			
			if(((adjCell.getState() == CellState.OPEN) || (adjCell.getState() == CellState.UNKNOWN)) && (!this.mapHistory.containsCell(adjCell)))
			{
				Cell nextCell = this.sensor.getCellDetail(adjCell.getPosition());
				Util.botLog("Distance to go home: "+this.mapHistory.getDistToHome());
				Util.botLog("Charge before movement: "+PowerController.GetCurrentCharge());
				if(PowerController.ValidateChargeToMove(this.mapHistory.getDistToHome(),this.currentLocation, nextCell))
				{
					String logString = "Bot is moving from (" + currentLocation.getPosition().getX() + "," + currentLocation.getPosition().getY() + ") - to (" + nextCell.getPosition().getX() + "," + nextCell.getPosition().getY() + ").";
					Util.botLog(logString);					
					
					PowerController.ReduceCharge(this.currentLocation, nextCell);					
					this.currentLocation = nextCell;
					this.mapHistory.addHistory(nextCell);
					moved = true;
					break;
				}
				else
				{
					Util.botLog("Robot needs to go home");
					System.out.println("Last visited cell: "+this.mapHistory.getLastCell().toString());
					this.RobotToHome();
					Util.botLog("Resuming from home to "+this.mapHistory.getLastCell().toString());
					this.currentLocation = this.mapHistory.getLastCell();
				}
				
			}
		
		}		
		return moved;
	}
}