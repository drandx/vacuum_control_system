package controlsystem.navigation;

import java.util.ArrayDeque;

public class PathCalculator {

    private ArrayDeque<coordPair> m_knownPath;
    private ArrayDeque<coordPair> m_pathHome;
    
    private coordPair m_chargerLoc;

    public PathCalculator( ArrayDeque<coordPair> pathHistory, coordPair homeIn ){ 
         m_knownPath = pathHistory;
         m_chargerLoc = homeIn;
    }
    
    // calculates the shortest path to the charging station, using BreathFirstSearch
    public ArrayDeque<coordPair> getPathHome(){
    	ArrayDeque<coordPair> pathHome = new ArrayDeque<coordPair>();
    	
        // TODO... use Breadth-First-Search
    	
    	return pathHome;
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    private boolean isCellKnown( coordPair cell ){
        boolean cellIsKnown = false;
        
        // because we don't care about efficiency, yet.
        for( coordPair coord : m_knownPath ){
            if( cell.getX() == coord.getX() && cell.getY() == coord.getY() ){
                cellIsKnown = true;
                break;
            }
        }
        return cellIsKnown;
    }
}