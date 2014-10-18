package controlsystem.navigation;

import simulator.model.Cell;
import simulator.model.HomeModel;

public class test {

	public static void main(String[] args) {
		HomeModel.Load();
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
		}
	}


}
