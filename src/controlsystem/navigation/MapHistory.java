package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.HashMap;

import controlsystem.model.Cell;

public class MapHistory {
	
    private ArrayDeque<Cell> historyMap;
    private int distToHome;
    private HashMap<Cell,Integer> minDist;
    private HashMap<Cell,Cell> pathHome;
    private PathCalculator path;
    

    //History Constructor
    public MapHistory() {
        historyMap = new ArrayDeque<Cell>();
    }
    
    public void addHistory(Cell history) {
        historyMap.add(history);
        
        //DistToHome is calculated using Dijkstra every time we add a history, this is the min amt of battery life
        //needed to get home
        
        path = new PathCalculator();
        path.getPathHome(historyMap);
        minDist = path.getDist();
        pathHome = path.getPath();
        distToHome = minDist.get(historyMap.peekLast());
        
    } 
    
    public Cell getLastCell() {
    	return historyMap.peekLast();
    }
    
    public ArrayDeque<Cell> getHistoryMap(){
        return historyMap;
    }
    
    public int getDistToHome() {
        return distToHome;
    }
    
    public HashMap<Cell,Cell> getPathToHome() {
    	return pathHome;
    }
}