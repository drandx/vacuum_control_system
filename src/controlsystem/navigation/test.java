package controlsystem.navigation;

import controlsystem.model.Cell;
import controlsystem.model.SurfaceType;

public class test {

	public static void main(String[] args) {
		
	Cell c1 = new Cell(0,0);
	c1.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c2 = new Cell(0,1);
	c2.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c3 = new Cell(0,2);
	c3.setSurfaceType(SurfaceType.HIGH_PILE_FLOOR);
	Cell c4 = new Cell(1,2);
	c4.setSurfaceType(SurfaceType.LOW_PILE_FLOOR);
	Cell c5 = new Cell(1,1);
	c5.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c6 = new Cell(2,1);
	c6.setSurfaceType(SurfaceType.BARE_FLOOR);
	Cell c7 = new Cell(2,2);
	c7.setSurfaceType(SurfaceType.BARE_FLOOR);
	
	MapHistory map = new MapHistory();
	System.out.println("added point (" + Integer.toString(c1.getPosition().getX()) + "," + Integer.toString(c1.getPosition().getY()) + ")");
	map.addHistory(c1);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c2.getPosition().getX()) + "," + Integer.toString(c2.getPosition().getY()) + ")");
	map.addHistory(c2);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c3.getPosition().getX()) + "," + Integer.toString(c3.getPosition().getY()) + ")");
	map.addHistory(c3);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c4.getPosition().getX()) + "," + Integer.toString(c4.getPosition().getY()) + ")");
	map.addHistory(c4);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c5.getPosition().getX()) + "," + Integer.toString(c5.getPosition().getY()) + ")");
	map.addHistory(c5);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c6.getPosition().getX()) + "," + Integer.toString(c6.getPosition().getY()) + ")");
	map.addHistory(c6);
	System.out.println("distance to charging station " + map.getDistToHome());
	System.out.println("\n");
	System.out.println("added point (" + Integer.toString(c7.getPosition().getX()) + "," + Integer.toString(c7.getPosition().getY()) + ")");
	map.addHistory(c7);
	System.out.println("distance to charging station " + map.getDistToHome());
	
	}


}
