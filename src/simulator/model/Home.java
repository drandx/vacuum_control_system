package simulator.model;

import java.util.LinkedList;

public class Home
{
	LinkedList<FloorLevel> Floors;
	
	public Home() {
		super();
		Floors = new LinkedList<FloorLevel>();
	}

	public Home(LinkedList<FloorLevel> floors) {
		super();
		Floors = floors;
	}

	public LinkedList<FloorLevel> getFloors() {
		return Floors;
	}

	public void setFloors(LinkedList<FloorLevel> floors) {
		Floors = floors;
	}
	
}
