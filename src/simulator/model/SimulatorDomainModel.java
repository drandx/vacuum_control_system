package simulator.model;
import java.util.LinkedList;

public class SimulatorDomainModel {
	
	public class Home
	{
		LinkedList<FloorLevel> Floors;
		
		public Home() {
			super();
			Floors = new LinkedList<SimulatorDomainModel.FloorLevel>();
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
	
	public class FloorLevel
	{
		int floor;
		LinkedList<Cell> Cells;
		
		public FloorLevel(int floor, LinkedList<Cell> cells) {
			super();
			this.floor = floor;
			Cells = cells;
		}
		public FloorLevel() {
			super();
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
	
	public class Cell
	{
		String xs;
		String ys;
		String ss;
		String ps;
		String ds;
		String cs;
		
		public Cell(String xs, String ys, String ss, String ps, String ds,
				String cs) {
			super();
			this.xs = xs;
			this.ys = ys;
			this.ss = ss;
			this.ps = ps;
			this.ds = ds;
			this.cs = cs;
		}
		public String getXs() {
			return xs;
		}
		public void setXs(String xs) {
			this.xs = xs;
		}
		public String getYs() {
			return ys;
		}
		public void setYs(String ys) {
			this.ys = ys;
		}
		public String getSs() {
			return ss;
		}
		public void setSs(String ss) {
			this.ss = ss;
		}
		public String getPs() {
			return ps;
		}
		public void setPs(String ps) {
			this.ps = ps;
		}
		public String getDs() {
			return ds;
		}
		public void setDs(String ds) {
			this.ds = ds;
		}
		public String getCs() {
			return cs;
		}
		public void setCs(String cs) {
			this.cs = cs;
		}
		
	}
	
}


