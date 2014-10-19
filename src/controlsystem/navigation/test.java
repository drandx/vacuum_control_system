package controlsystem.navigation;

import simulator.model.Cell;
import simulator.model.HomeModel;

public class test {

	public static void main(String[] args) {
	/*HomeModel.Load();
		MapHistory map = new MapHistory();
		for(int i=0; i < HomeModel.loadedHome.getFloors().size(); i++)
		{
			for(int j = 0; j < HomeModel.loadedHome.getFloors().get(i).getCells().size(); j++)
			{
				Cell currentCell = HomeModel.loadedHome.getFloors().get(i).getCells().get(j);
				
				coordPair pair = new coordPair();
				pair.setX(currentCell.getXs());
				pair.setY(currentCell.getYs());
				
				map.addHistory(pair);
				System.out.println(map.getDistToHome());
			}
		}*/
		
	coordPair p1 = new coordPair(0,0);
	coordPair p2 = new coordPair(0,1);
	coordPair p3 = new coordPair(1,1);
	coordPair p4 = new coordPair(2,1);
	coordPair p5 = new coordPair(1,2);
	coordPair p6 = new coordPair(2,2);
	
	MapHistory map = new MapHistory();
	map.addHistory(p1);
	map.addHistory(p2);
	System.out.println("distance to charging station " + map.getDistToHome());
	map.addHistory(p3);
	System.out.println("distance to charging station " + map.getDistToHome());
	map.addHistory(p4);
	System.out.println("distance to charging station " + map.getDistToHome());
	map.addHistory(p5);
	System.out.println("distance to charging station " + map.getDistToHome());
	map.addHistory(p6);
	System.out.println("distance to charging station " + map.getDistToHome());
	
	}


}
