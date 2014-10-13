package simulator.model;
import java.util.LinkedList;

public class SimulatorDomainModel {
	
	public class Home
	{
		LinkedList<FloorLevel> Floors;
	}
	
	public class FloorLevel
	{
		int floor;
		LinkedList<Cell> Cells;
	}
	
	public class Cell
	{
		String xs;
		String ys;
		String ss;
		String ps;
		String ds;
		String cs;
	}
	
}


