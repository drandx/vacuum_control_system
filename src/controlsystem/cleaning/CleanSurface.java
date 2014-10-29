package controlsystem.cleaning;

import controlsystem.model.SurfaceType;

public class CleanSurface {
	
	private SurfaceType surface;
	
	public CleanSurface () {
		
	}

	public void clean(SurfaceType  type) {
		this.surface = type;
		//check if bag is full if not add one to bag, if full return to charing station
		if(surface.getCode() == 1) {
			//subtract 1 from battery
		}
		else if(surface.getCode() == 2) {
			//subtract 2 from battery
		}
		else {
			//subtract 3 from battery
		}
	}
}
