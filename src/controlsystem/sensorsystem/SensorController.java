package controlsystem.sensorsystem;
import controlsystem.model.*;

public class SensorController {
	SensorStrategy sensorSystem = new SimulatorStrategy();
	
	public Cell getCellDetail(controlsystem.navigation.coordPair position)
	{
		return sensorSystem.getCell(position);
		
	}
	
}
