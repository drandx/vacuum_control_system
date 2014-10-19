package controlsystem.navigation;

import java.util.ArrayDeque;

public class PathCalculator {
	
    //private ArrayDeque<coordPair> m_pathHome; probably unneeded.
    
    private coordPair m_chargerLoc;
    
    public PathCalculator( coordPair homeIn ){
         //m_knownPath = pathHistory;
         m_chargerLoc = homeIn;
    }
    
    // calculates the shortest path to the charging station, using BreathFirstSearch
    // TODO - this is called when the battery is 'critical'
    public ArrayDeque<coordPair> getPathHome( ArrayDeque<coordPair> knownCells ){
    	coordPair root = knownCells.pop();
    	
    	return BFS( knownCells, root );
    }
    
    //Breadth-first-search
    private ArrayDeque<coordPair> BFS( ArrayDeque<coordPair> graph, coordPair node ){
    	ArrayDeque<coordPair> pathHome = new ArrayDeque<coordPair>();
    	
    	//TODO - implement BFS...
    	
    	return pathHome;
    }
    
    // This is essentially the 'getChildrenOfNode' method in BFS
    private ArrayDeque<coordPair> getNeighboringCells( ArrayDeque<coordPair> knownCells, coordPair cell ){
    	ArrayDeque<coordPair> neighbors = new ArrayDeque<coordPair>();
    	
    	// add X + 1
    	coordPair temp = new coordPair( cell.getX() + 1, cell.getY() );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	// add X - 1
    	temp = new coordPair( cell.getX() - 1, cell.getY() );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	//add Y + 1
    	temp = new coordPair( cell.getX(), cell.getY() + 1 );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	//add Y - 1
    	temp = new coordPair( cell.getX(), cell.getY() - 1 );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	return neighbors; //this means you'll probably have to do an empty check in the BFS
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    private boolean isCellKnown( ArrayDeque<coordPair> knownCells, coordPair cell ){
        boolean cellIsKnown = false;
        
        // because we don't care about efficiency, yet.
        for( coordPair coord : knownCells ){
            if( cellsAreEqual( cell, coord ) ){
                cellIsKnown = true;
                break;
            }
        }
        return cellIsKnown;
    }
    
    // are two cells equal?
    private boolean cellsAreEqual( coordPair cell1, coordPair cell2 ){
    	return ( cell1.getX() == cell2.getX() ) && ( cell1.getY() == cell2.getY() );
    }
}