package controlsystem.navigation;

import controlsystem.model.Cell;
import controlsystem.model.CellState;
import controlsystem.sensorsystem.*;

public class MovementController {	
	 Cell currentLocation;
	 SensorController sensor;
	 
	public MovementController() {
		this.sensor = new SensorController();
		this.currentLocation = sensor.getCell(new coordPair(0, 0));
	}

	public Cell getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Cell currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public void MoveToNextPosition()
	{
		//TODO-Verify with the power management system if there is enough energy to perform an action
		//TODO-Invoke the tracking path system
		
		//TODO-Log the movement - from what cell to what cell
		
		for(Cell adjCell : currentLocation.getAdjacentCells())
		{
			if(adjCell.getState() == CellState.OPEN)
			{
				//Change the current location and with this.sensor.getCell(adjCell.getPosition()) gets the adjacent cells
				this.currentLocation = this.sensor.getCell(adjCell.getPosition());
				break;
			}
		
		}
	}

}
