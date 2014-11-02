package controlsystem.cleaning;

import controlsystem.model.Cell;
import controlsystem.power.PowerController;
import controlsystem.navigation.PathCalculator;

public class CleanSurface {
	
	Cell currentLocation;
	PathCalculator mapHome;
	
	public CleanSurface () {
		
	}

	public void clean(Cell current) {
		this.currentLocation = current;
		if(DirtBag.getCurrentDirt() == 50) {
			//return to charing station using Pathfinder path
		} else
			while(true) { //while detects dirt
				//if has power to move
					PowerController.ReduceCharge(currentLocation, currentLocation);
				//else
					//return to charing station
			}
	}
}
