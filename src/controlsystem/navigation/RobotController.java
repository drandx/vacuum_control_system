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
	 
	public RobotController() {
		this.sensor = new SensorController();
		this.mapHistory = new MapHistory();
		this.currentLocation = sensor.getCellDetail(new coordPair(0, 0));
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
				// TODO: May have to do things with PowerController to use our PathCalculator and determine if it can move or head home...?
				// And then, that's where a flag is raised to say, 'hey, cleaning was interrupted and should be resumed'
				// a similar thing should be added to the 'dirt bag' controller thing
				System.out.println("Distance to go home: "+this.mapHistory.getDistToHome());
				System.out.println("Charge before movement: "+PowerController.GetCurrentCharge());
				if(PowerController.ValidateChargeToMove(this.mapHistory.getDistToHome(),this.currentLocation, nextCell))
				{
					//TODO - and probably how much power is remaining or something...
					String logString = "Bot is moving from (" + currentLocation.getPosition().getX() + "," + currentLocation.getPosition().getY() + ") - to (" + nextCell.getPosition().getX() + "," + nextCell.getPosition().getY() + ").";
					Util.botLog(logString);
					
					PowerController.ReduceCharge(this.currentLocation, nextCell);
					//Changes the current location. "this.sensor.getCell(adjCell.getPosition())" gets the adjacent cells
					this.currentLocation = nextCell;
					this.mapHistory.addHistory(nextCell);
					moved = true;
					break;
				}
				else
				{
					Util.botLog("Robot needs to go home");
				}
				
			}
		
		}		
		return moved;
	}
}