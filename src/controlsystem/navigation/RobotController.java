package controlsystem.navigation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import simulator.util.Util;
import controlsystem.cleaning.CleanSurface;
import controlsystem.cleaning.DirtBag;
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
	 * TODO: I'm realizing that this is probably useless now that I've made goHome() autonomous.
	 * 
	 * Takes any collection denoting a path and sends the robot to travel down it.
	 * TODO: This is mostly for pathing home, so it assumes you already know it has enough
	 * overall charge to make it to its destination.
	 * (It does NOT check if it has enough charge to move)
	 * (it does NOT clean on this path)
	 * (It also assumes that the path is valid and doesn't involve punching through walls)
	 * 
	 * @param path	- the robot will travel from the first element to the last.
	 */
	public void followGivenPath( Collection<Cell> path ) {
		
		for( Cell coord : path ) {
			
			PowerController.ReduceCharge( currentLocation, coord);
			logMovement( currentLocation, coord );
			setCurrentLocation( coord );
		}
	}
	
	/**
	 * Sends the robot home.  Assumes the mapHistory has been kept up-to-date and such!
	 */
	private void goHome() {
		
		Util.botLog( "\nRobot is heading home!");
		
		HashMap<Cell, Cell> pathHome = mapHistory.getPathToHome();
		
		Cell homeStation = new Cell( 0, 0 );  //TODO: Shouldn't this be a member variable?
		
		while( !currentLocation.equals( homeStation ) ){
			Cell nextCell = pathHome.get( currentLocation );
			
			PowerController.ReduceCharge( currentLocation, nextCell );
			logMovement( currentLocation, nextCell );
			setCurrentLocation( nextCell );
		}
		Util.botLog("\nRobot is home!!");
	}
	
	/**
	 * Sends the robot from home to the last cell it was at before returning.  Assumes the mapHistory has been kept up-to-date and such!
	 */
	private void returnToLast() {
		
		Util.botLog( "\nRobot is resuming its task!");
		
		ArrayList<Cell> pathHome = new ArrayList<Cell>();
		
		HashMap<Cell, Cell> hashHome = mapHistory.getPathToHome();
		
		Cell homeStation = new Cell( 0, 0 );  //TODO: Shouldn't this be a member variable?
		
		Cell dummyCurrentLocation = this.mapHistory.getLastCell();;
		
		//re-builds the path home into an array
		while( !dummyCurrentLocation.equals( homeStation ) ){
			Cell nextCell = hashHome.get( dummyCurrentLocation );
			
			pathHome.add( nextCell );
		}
		
		//removes the 'home' location, since we're already there.
		pathHome.remove( pathHome.size() - 1 );
		
		//reverses it so it's traversed from home to the last cell visited
		Collections.reverse( pathHome );
		
		for( Cell loc : pathHome ) {
			
			PowerController.ReduceCharge( currentLocation, loc );
			logMovement( currentLocation, loc );
			setCurrentLocation( loc );
			
		}
		
	}
	
	/**
	 * Logs the movement of the robot
	 * @param currentCell
	 * @param nextCell
	 */
	private void logMovement( Cell currentCell, Cell nextCell ){
		
		String logString = "Bot is moving from (" + currentCell.getPosition().getX() + "," + currentCell.getPosition().getY() + ") - to (" + nextCell.getPosition().getX() + "," + nextCell.getPosition().getY() + ").";
		Util.botLog(logString);		
		
	}
	
	/**
	 * Returns false if there is not enough battery or there are no available cells to move to.
	 * @return
	 */
	public boolean Run()
	{
		//TODO - need to add a call to cleaning every time, might need to change validate charge to move a bit
		boolean moved = false;
		for(Cell adjCell : currentLocation.getAdjacentCells())
		{			
			if(((adjCell.getState() == CellState.OPEN) || (adjCell.getState() == CellState.UNKNOWN)) && (!this.mapHistory.containsCell(adjCell)))
			{
				Cell nextCell = this.sensor.getCellDetail(adjCell.getPosition());
				Util.botLog("Charge before movement: "+PowerController.GetCurrentCharge());
				Util.botLog("Distance to go home before movment: "+this.mapHistory.getDistToHome());
				boolean cleanedSurface = CleanSurface.cleanCell(this.currentLocation);
				if((cleanedSurface) &&  (PowerController.ValidateChargeToMove(this.mapHistory.getDistToHome(),this.currentLocation, nextCell)))
				{
					logMovement( currentLocation, nextCell );	
					PowerController.ReduceCharge(this.currentLocation, nextCell);					
					this.currentLocation = nextCell;
					this.mapHistory.addHistory(nextCell);
					moved = true;
					break;
				}
				else
				{
					Util.botLog("\nRobot needs to go home");
					System.out.println("Last visited cell: "+this.mapHistory.getLastCell().toString());
					//this.RobotToHome();
					this.goHome();
					PowerController.ChargeBattery();
					DirtBag.resetDirtBag();
					Util.botLog("Resuming from home to "+this.mapHistory.getLastCell().toString());
					this.currentLocation = this.mapHistory.getLastCell();
				}
				
			}
		
		}		
		return moved;
	}
}