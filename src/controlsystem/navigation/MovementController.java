package controlsystem.navigation;

import java.util.LinkedList;

import controlsystem.model.Cell;
import controlsystem.model.CellState;
import controlsystem.model.SurfaceType;
import controlsystem.power.PowerController;
import controlsystem.sensorsystem.*;

public class MovementController {	
	 Cell currentLocation;
	 SensorController sensor;
	 
	public MovementController() {
		this.sensor = new SensorController();
		this.currentLocation = sensor.getCellDetail(new coordPair(0, 0));
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
	public boolean MoveToNextPosition()
	{
		
		//TODO - Get path to the station and Remove dummy path to go back to the station
		LinkedList<Cell> pathToStation = new LinkedList<Cell>();
		for (int i = 0; i < 7; i++) {
			Cell dummyCell = new Cell();
			dummyCell.setPosition(new coordPair(i, 0));
			SurfaceType dummySurfaceType = ( (i & 1) == 0 ) ? SurfaceType.BARE_FLOOR : SurfaceType.HIGH_PILE_FLOOR;
			dummyCell.setSurfaceType(dummySurfaceType);
			dummyCell.setDirtUnits(i + 1);
			dummyCell.setState(CellState.OPEN);
			pathToStation.add(dummyCell);
		}
		boolean moved = false;
		for(Cell adjCell : currentLocation.getAdjacentCells())
		{
			if(((adjCell.getState() == CellState.OPEN) || (adjCell.getState() == CellState.UNKNOWN)))
			{
				Cell fullCell = this.sensor.getCellDetail(adjCell.getPosition());
				// TODO: May have to do things with PowerController to use our PathCalculator and determine if it can move or head home...?
				// And then, that's where a flag is raised to say, 'hey, cleaning was interrupted and should be resumed'
				// a similar thing should be added to the 'dirt bag' controller thing
				if(PowerController.ValidateChargeToMove(pathToStation, fullCell))
				{
					//TODO-Log the movement - from what cell to what cell
					PowerController.ReduceCharge(this.currentLocation, fullCell);
					//Changes the current location. "this.sensor.getCell(adjCell.getPosition())" gets the adjacent cells
					this.currentLocation = fullCell;
					moved = true;
					break;
				}
				
			}
		
		}
		
		return moved;
	}
	
	
}
