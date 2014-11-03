package controlsystem.model;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import controlsystem.navigation.coordPair;

public class Cell
{
	coordPair Position;
	int DirtUnits;
	SurfaceType SurfaceType;
	Boolean isStation;
	Boolean marked;
	CellState State;
	List<coordPair> AdjacentCells;
	/*
	 * Due to issues with infinite loops, the list of AdjacentCells are, in fact, coordPairs.
	 * The retrieval methods, however, will spit out Cell objects.
	 */
	
	
	public Cell() {
		this.AdjacentCells = new ArrayList<coordPair>(4);
		this.DirtUnits = -1;		
		this.isStation = false;
		this.marked = false;
		this.Position = new coordPair( 0, 0 );
	}
	
	public Cell( int x, int y ) {
		this.DirtUnits = -1;		
		this.isStation = false;
		this.marked = false;
		this.Position = new coordPair( x, y );
		this.AdjacentCells = populateAdjacentCells();
	}
	
	public Cell( coordPair coords ) {
		this.DirtUnits = -1;		
		this.isStation = false;
		this.marked = false;
		this.Position = coords;
		this.AdjacentCells = populateAdjacentCells();
	}
	
	public Boolean getMarked() {
		return this.marked;
	}
	
	public void setMarked(Boolean b) {
		this.marked = b;
	}
	
	public Cell getRightCell() {
		return new Cell( AdjacentCells.get(0) );
	}
	
	public Cell getLeftCell() {
		return new Cell( AdjacentCells.get(1) );
	}
	
	public Cell getTopCell() {
		return new Cell( AdjacentCells.get(2) );
	}
	
	public Cell getBottomCell()	{
		return new Cell( AdjacentCells.get(3) );
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
		
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		for( coordPair coord : AdjacentCells ){
			
			cells.add( new Cell( coord ) );
		}
		
		return cells;
	}

	public void setAdjacentCells(List<Cell> newAdjacentCells) {
		//AdjacentCells = newAdjacentCells;
		
		AdjacentCells.clear();
		
		for( Cell cell : newAdjacentCells ) {
			AdjacentCells.add( cell.getPosition() );
		}
	}
	
	private List<coordPair> populateAdjacentCells() {
		List<coordPair> neighbors = new ArrayList<coordPair>();
    	
    	// add X + 1
		coordPair temp = new coordPair( Position.getX() + 1, Position.getY() );
    	neighbors.add( temp );
    	
    	// add X - 1
    	temp = new coordPair( Position.getX() - 1, Position.getY() );
    	neighbors.add( temp );
    	
    	//add Y + 1
    	temp = new coordPair( Position.getX(), Position.getY() + 1 );
    	neighbors.add( temp );
    	
    	//add Y - 1
    	temp = new coordPair( Position.getX(), Position.getY() - 1 );
    	neighbors.add( temp );
		
    	return neighbors;
	}
	
}

