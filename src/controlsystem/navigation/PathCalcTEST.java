package controlsystem.navigation;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import controlsystem.model.Cell;
import controlsystem.model.SurfaceType;

public class PathCalcTEST extends TestCase {
	
	public PathCalcTEST(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testPath() {
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
		Cell c8 = new Cell(2,3);
		c8.setSurfaceType(SurfaceType.LOW_PILE_FLOOR);
		
		MapHistory map = new MapHistory();
		
		map.addHistory(c1);
		map.addHistory(c2);
		Cell cell = map.getPathToHome().get(c2);
		Assert.assertTrue(cell.getPosition().getX() == 0);
		Assert.assertTrue(cell.getPosition().getY() == 0);
		map.addHistory(c3);
		cell = map.getPathToHome().get(c3);
		Assert.assertTrue(cell.getPosition().getX() == 0);
		Assert.assertTrue(cell.getPosition().getY() == 1);
		map.addHistory(c4);
		cell = map.getPathToHome().get(c4);
		Assert.assertTrue(cell.getPosition().getX() == 0);
		Assert.assertTrue(cell.getPosition().getY() == 2);
		map.addHistory(c5);
		cell = map.getPathToHome().get(c5);
		Assert.assertTrue(cell.getPosition().getX() == 0);
		Assert.assertTrue(cell.getPosition().getY() == 1);
		map.addHistory(c6);
		cell = map.getPathToHome().get(c6);
		Assert.assertTrue(cell.getPosition().getX() == 1);
		Assert.assertTrue(cell.getPosition().getY() == 1);
		map.addHistory(c7);
		cell = map.getPathToHome().get(c7);
		Assert.assertTrue(cell.getPosition().getX() == 2);
		Assert.assertTrue(cell.getPosition().getY() == 1);
		map.addHistory(c8);
		cell = map.getPathToHome().get(c8);
		Assert.assertTrue(cell.getPosition().getX() == 2);
		Assert.assertTrue(cell.getPosition().getY() == 2);
	
	}

}
