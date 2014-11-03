package controlsystem.navigation;

import java.util.Comparator;
import controlsystem.model.Cell;

public class surfaceComparator implements Comparator<Cell> {

	@Override
	public int compare(Cell c1, Cell c2) {
		System.out.println("code = " + c1.getSurfaceType().getCode());
    	System.out.println("code = " + c2.getSurfaceType().getCode());
        return (c1.getSurfaceType().getCode() - c2.getSurfaceType().getCode());
	}

}
