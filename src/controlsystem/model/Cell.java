package controlsystem.model;
import java.util.ArrayList;
import java.util.List;

import controlsystem.navigation.coordPair;

public class Cell
{
	coordPair Position;
	int DirtUnits;
	int SurfaceType;
	Boolean isStation;
	CellState State;
	List<Cell> AdjacentCells;
	
	public Cell()
	{
		this.AdjacentCells = new ArrayList<Cell>();
		this.DirtUnits = -1;
		this.SurfaceType = -1;
		this.isStation = false;
		this.Position = new coordPair();
	}
	
	public Cell(coordPair position, int dirtUnits, int surfaceType,Boolean isStation) {
		Position = position;
		DirtUnits = dirtUnits;
		SurfaceType = surfaceType;
		this.isStation = isStation;
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
	
	public int getSurfaceType() {
		return SurfaceType;
	}
	
	public void setSurfaceType(int surfaceType) {
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

