package controlsystem.sensorssystem;

import controlsystem.model.Cell;
import controlsystem.navigation.coordPair;
import simulator.model.*;

public class SimulatorStrategy implements SensorStrategy{

	@Override
	public Cell getCell(coordPair position) {
		HomeModel.Load();
		
		for(FloorLevel floor : HomeModel.loadedHome.getFloors())
		{
			/*for(Cell )
			{
			
			}*/
			
		}
		
		return null;
	}
	

}
