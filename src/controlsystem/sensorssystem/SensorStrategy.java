package controlsystem.sensorssystem;

import controlsystem.model.Cell;
import controlsystem.navigation.coordPair;

public interface SensorStrategy {
	
	public Cell getCell(coordPair position);

}
