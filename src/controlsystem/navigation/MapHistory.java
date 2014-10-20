package controlsystem.navigation;

import java.util.ArrayDeque;

public class MapHistory {
    
    private ArrayDeque<coordPair> historyMap;
    private int distToHome;
    private PathCalculator path;
    

    //History Constructor
    public MapHistory() {
        historyMap = new ArrayDeque<coordPair>();
    }
    
    public void addHistory(coordPair history) {
        historyMap.add(history);
        
        //Closest Distance to charging station is just the total sum of the X and Y coordinates its currently on.
        //This will be the min battery it needs to get to charging station
        
        //I think this distToHome should be calculated using BFS every time we add a history?
        //distToHome should just be the size of the shortest path array that we obtain from doing BFS
        //distToHome = Math.abs(history.getX()) + Math.abs(history.getY());
        
        path = new PathCalculator();
        
        distToHome = path.getPathHome(historyMap).size();
        
    }
    
    public ArrayDeque<coordPair> getHistoryMap(){
        return historyMap;
    }
    
    public int getDistToHome() {
        return distToHome;
    }
}