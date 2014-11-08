package controlsystem.cleaning;

import java.util.HashMap;
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
		if(DirtBag.getCurrentBag() == 50) {
			System.out.println("Bag is full of dirt, moving to charging station");
			mover.MoveToNextPosition(); //TODO input pathToStation? May need to rewrite this to take the current cell as input
		} else
			while(current.getDirtUnits() > 0) {
				if(PowerController.ValidateChargeToMove(pathToStation, home)) { //need path to station still, can this be a hashmap?
					DirtBag.addDirt();
					currentLocation.setDirtUnits(currentLocation.getDirtUnits() - 1);
				}else {
					mover.MoveToNextPosition(); //TODO input pathToStation? May need to rewrite this to take the current cell as input
				}
			}
	}
}
