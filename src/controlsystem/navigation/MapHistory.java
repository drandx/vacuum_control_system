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
		
		//In here we will need to update distToHome every time we add a new History Point so
		//we know how many movements it will take to get to the base station
	}
	
	public int getDistToHome() {
		return distToHome;
	}
}
