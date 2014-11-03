package controlsystem.cleaning;

import java.util.LinkedList;

import controlsystem.model.Cell;
import controlsystem.power.PowerController;
import controlsystem.navigation.MapHistory;
import controlsystem.navigation.MovementController;
import controlsystem.navigation.PathCalculator;

public class CleanSurface {
	
	Cell currentLocation;
	Cell home = new Cell(0,0);
	MovementController mover = new MovementController();

	public void clean(Cell current, LinkedList<Cell>pathToStation) {
		this.currentLocation = current;
		if(DirtBag.getCurrentDirt() == 50) {
			mover.MoveToNextPosition(); //TODO input pathToStation
		} else
			while(current.getDirtUnits() > 0) {
				if(PowerController.ValidateChargeToMove(pathToStation, home)) { //need path to station still
					DirtBag.addDirt();
					currentLocation.setDirtUnits(currentLocation.getDirtUnits() - 1);
				}else {
					mover.MoveToNextPosition(); //TODO input pathToStatio
				}
			}
	}
}
