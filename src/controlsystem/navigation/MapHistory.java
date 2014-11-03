package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.HashMap;

import controlsystem.model.Cell;

public class MapHistory {
    
    //private ArrayDeque<coordPair> historyMap;
    private ArrayDeque<Cell> historyMap;
    private int distToHome;
    private int[][] test;
    private PathCalculator path;
    

    //History Constructor
    public MapHistory() {
        historyMap = new ArrayDeque<Cell>();
    }
    
    public void addHistory(Cell history) {
        historyMap.add(history);
        
        //Closest Distance to charging station is just the total sum of the X and Y coordinates its currently on.
        //This will be the min battery it needs to get to charging station
        
        //I think this distToHome should be calculated using BFS every time we add a history?
        //distToHome should just be the size of the shortest path array that we obtain from doing BFS
        //distToHome = Math.abs(history.getX()) + Math.abs(history.getY());
        
        path = new PathCalculator();
        path.getPathHome(historyMap);
        test = path.getDist();
        //for (Cell c : test.keySet()) {
        	//System.out.println("Cell " + Integer.toString(c.getPosition().getX()) + " " + Integer.toString(c.getPosition().getY()) + " " + test.get(c));
        //}
        System.out.println("last cell = " + Integer.toString(historyMap.peekLast().getPosition().getX()) + " " + Integer.toString(historyMap.peekLast().getPosition().getY()));
        //distToHome = path.getDist().get(historyMap.peekLast());
        distToHome = test[historyMap.peekLast().getPosition().getX()][historyMap.peekLast().getPosition().getY()];
        
    } 
    
    public ArrayDeque<Cell> getHistoryMap(){
        return historyMap;
    }
    
    public int getDistToHome() {
        return distToHome;
    }
}