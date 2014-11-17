package simulator.model;
import simulator.util.HomeXmlReader;
import simulator.util.Util;

public class HomeModel {
	
	public static Home loadedHome;
	
	public static void Load()
	{
		
		loadedHome = HomeXmlReader.ReadXML(Util.GetProjectPath()+"homeTest1.xml");
	}
	
	public static void PrintMap()
	{
		HomeModel.Load();
		int floorCont = 1;
		for(FloorLevel floor : HomeModel.loadedHome.getFloors())
		{
			System.out.println("Floor number: "+ floorCont);
			for(simulator.model.Cell cell : floor.getCells() )
			{
					System.out.println(cell.toString());
			}
			floorCont++;
		}
	}
	
}
