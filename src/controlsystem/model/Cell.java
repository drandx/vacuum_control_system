package controlsystem.model;
import java.util.ArrayList;
import java.util.List;

import controlsystem.navigation.coordPair;

public class Cell
{
	coordPair Position;
	int DirtUnits;
	SurfaceType SurfaceType;
	Boolean isStation;
	CellState State;
	List<Cell> AdjacentCells;
	
	public Cell() {
		this.AdjacentCells = new ArrayList<Cell>(4);
		this.DirtUnits = -1;		
		this.isStation = false;
		this.Position = new coordPair( 0, 0 );
	}
	
	public Cell( int x, int y ) {
		this.DirtUnits = -1;		
		this.isStation = false;
		this.Position = new coordPair( x, y );
		this.AdjacentCells = new ArrayList<Cell>(4);
	}
	
	public Cell( coordPair coords ) {
		this.DirtUnits = -1;		
		this.isStation = false;
		this.Position = coords;
		this.AdjacentCells = new ArrayList<Cell>(4);
	}
	
	public Cell getRightCell() {
		return AdjacentCells.get(0);
	}
	
	public Cell getLeftCell() {
		return AdjacentCells.get(1);
	}
	
	public Cell getTopCell() {
		return AdjacentCells.get(2);
	}
	
	public Cell getBottomCell()	{
		return AdjacentCells.get(3);
	}
	
	public coordPair getPosition() {
		return Position;
	}
	
	public void setPosition(coordPair position) {
		Position = position;
	}
	
	public int getDirtUnits() {
		return DirtUnits;
	}
	
	public void setDirtUnits(int dirtUnits) {
		DirtUnits = dirtUnits;
	}
	
	public SurfaceType getSurfaceType() {
		return SurfaceType;
	}
	
	public void setSurfaceType(SurfaceType surfaceType) {
		SurfaceType = surfaceType;
	}
	
	public Boolean getIsStation() {
		return isStation;
	}
	
	public void setIsStation(Boolean isStation) {
		this.isStation = isStation;
	}

	public CellState getState() {
		return State;
	}

	public void setState(CellState state) {
		State = state;
	}

	public List<Cell> getAdjacentCells() {
		return AdjacentCells;
	}

	public void setAdjacentCells(List<Cell> newAdjacentCells) {
		AdjacentCells = newAdjacentCells;
	}
	
	public List<Cell> getDummyAdjacentCells() {
		List<Cell> neighbors = new ArrayList<Cell>();
    	
    	// add X + 1
		coordPair temp = new coordPair( Position.getX() + 1, Position.getY() );
    	neighbors.add( new Cell(temp) );
    	
    	// add X - 1
    	temp = new coordPair( Position.getX() - 1, Position.getY() );
    	neighbors.add( new Cell(temp) );
    	
    	//add Y + 1
    	temp = new coordPair( Position.getX(), Position.getY() + 1 );
    	neighbors.add( new Cell(temp) );
    	
    	//add Y - 1
    	temp = new coordPair( Position.getX(), Position.getY() - 1 );
    	neighbors.add( new Cell(temp) );
		
    	return neighbors;
	}
	
}

