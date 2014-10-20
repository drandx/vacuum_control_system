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
	
	
	public Cell()
	{
		this.AdjacentCells = new ArrayList<Cell>(4);
		this.DirtUnits = -1;		
		this.isStation = false;
		this.Position = new coordPair();
	}
	
	
	public Cell getLeftCell()
	{
		return AdjacentCells.get(1);
	}
	
	
	public Cell getRightCell()
	{
		return AdjacentCells.get(0);
	}
	
	public Cell getTopCell()
	{
		return AdjacentCells.get(2);
	}
	
	public Cell getBottomCell()
	{
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

	public void setAdjacentCells(List<Cell> adjacentCells) {
		AdjacentCells = adjacentCells;
	}
	
	
}

