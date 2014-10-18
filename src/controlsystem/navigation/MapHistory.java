package controlsystem.navigation;

import java.util.ArrayDeque;

public class MapHistory {
	
	private ArrayDeque<coordPair> historyMap;
	private int distToHome;

	//History Constructor
	public MapHistory() {
		historyMap = new ArrayDeque<coordPair>();
	}
	
	public void addHistory(coordPair history) {
		historyMap.add(history);
		
		//Closest Distance to charing station is just the total sum of the X and Y coordinates its currently on.
		//This will be the min battery it needs to get to charging station
		distToHome = Math.abs(history.getX()) + Math.abs(history.getY());
		
	}
	
	public int getDistToHome() {
		return distToHome;
	}
}
