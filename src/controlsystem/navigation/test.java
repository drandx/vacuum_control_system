package controlsystem.navigation;

import controlsystem.model.Cell;
import controlsystem.model.SurfaceType;

public class test {

	public static void main(String[] args) {
		
	Cell c1 = new Cell(0,0);
	c1.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c2 = new Cell(0,1);
	c2.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c3 = new Cell(1,1);
	c3.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c4 = new Cell(2,1);
	c4.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c5 = new Cell(1,2);
	c5.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c6 = new Cell(2,2);
	c6.setSurfaceType(SurfaceType.BARE_FLOOR);
	
	MapHistory map = new MapHistory();
	System.out.println("added point (" + Integer.toString(c1.getPosition().getX()) + "," + Integer.toString(c1.getPosition().getY()) + ")");
	map.addHistory(c1);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(p2.getX()) + "," + Integer.toString(p2.getY()) + ")");
	map.addHistory(c2);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(p3.getX()) + "," + Integer.toString(p3.getY()) + ")");
	map.addHistory(c3);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(p4.getX()) + "," + Integer.toString(p4.getY()) + ")");
	map.addHistory(c4);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(p5.getX()) + "," + Integer.toString(p5.getY()) + ")");
	map.addHistory(c5);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(p6.getX()) + "," + Integer.toString(p6.getY()) + ")");
	map.addHistory(c6);
	System.out.println("distance to charging station " + map.getDistToHome());
	
	}


}
