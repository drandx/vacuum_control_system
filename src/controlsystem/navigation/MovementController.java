package controlsystem.navigation;

import controlsystem.model.Cell;
import controlsystem.sensorsystem.*;

public class MovementController {	
	 Cell currentLocation;
	 
	public MovementController() {
		SensorController sensor = new SensorController();
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
		
		//TODO-Validate what position to move according to each cell state.
		
		
	}

}
