package simulator.model;

import simulator.util.HomeXmlReader;
import simulator.util.Util;

public class HomeModel {
	
	public static Home loadedHome;
	
	public static void Load()
	{
		
		loadedHome = HomeXmlReader.ReadXML(Util.GetProjectPath()+"homeTest1.xml");
	}
	
}
