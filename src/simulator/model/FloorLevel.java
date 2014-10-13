package simulator.model;

import java.util.LinkedList;

public class FloorLevel
{
	int floor;
	LinkedList<Cell> Cells;
	
	public FloorLevel() {
		super();
		this.Cells = new LinkedList<>();
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public LinkedList<Cell> getCells() {
		return Cells;
	}
	
	public void setCells(LinkedList<Cell> cells) {
		Cells = cells;
	}
	
}